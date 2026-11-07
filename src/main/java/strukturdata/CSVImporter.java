
package strukturdata;

import model.Putusan;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CSVImporter {

    private static final String HEADER =
            "caseId,nomorPerkara,pengadilan,tanggalPutusan,terdakwaKode," +
                    "jenisNarkotika,beratBarangBuktiGram,pasalDakwaan,pasalTerbukti," +
                    "tuntutanBulan,vonisBulan,dendaRupiah,barangBukti," +
                    "keadaanMemberatkan,keadaanMeringankan,sourcePages";

    public static class ImportResult {
        private int berhasil;
        private int gagal;
        private final StringBuilder detailError = new StringBuilder();

        public int getBerhasil() {
            return berhasil;
        }

        public int getGagal() {
            return gagal;
        }

        public String getDetailError() {
            return detailError.toString();
        }

        private void tambahBerhasil() {
            berhasil++;
        }

        private void tambahGagal(int nomorBaris, String alasan) {
            gagal++;
            detailError.append("Baris ")
                    .append(nomorBaris)
                    .append(": ")
                    .append(alasan)
                    .append("\n");
        }
    }

    public ImportResult importCSV(
            String filePath,
            StrukturDataUtama<Putusan> dataPutusan
    ) throws IOException {

        ImportResult hasil = new ImportResult();

        try (BufferedReader reader = Files.newBufferedReader(
                Path.of(filePath), StandardCharsets.UTF_8)) {

            String header = reader.readLine();

            if (header == null || !header.replace("\uFEFF", "").equals(HEADER)) {
                throw new IOException("Header CSV tidak sesuai format.");
            }

            String baris;
            int nomorBaris = 1;

            while ((baris = reader.readLine()) != null) {
                nomorBaris++;

                if (baris.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] kolom = parseCSV(baris);

                    if (kolom.length != 16) {
                        throw new IllegalArgumentException(
                                "Jumlah kolom harus 16, ditemukan " + kolom.length
                        );
                    }

                    // Memeriksa kolom wajib
                    wajibDiisi(kolom[0], "caseId");
                    wajibDiisi(kolom[1], "nomorPerkara");
                    wajibDiisi(kolom[2], "pengadilan");
                    wajibDiisi(kolom[4], "terdakwaKode");

                    // Mengolah tanggal, boleh kosong
                    LocalDate tanggal = parseTanggal(kolom[3]);

                    // Mengolah angka, nilai kosong menjadi 0
                    double berat = parseDouble(kolom[6]);
                    int tuntutan = parseInt(kolom[9]);
                    int vonis = parseInt(kolom[10]);
                    long denda = parseLong(kolom[11]);

                    // Membuat objek Putusan
                    Putusan putusan = new Putusan(
                            kolom[0],
                            kolom[1],
                            kolom[2],
                            tanggal,
                            kolom[4],
                            kolom[5],
                            berat,
                            kolom[7],
                            kolom[8],
                            tuntutan,
                            vonis,
                            denda,
                            kolom[12],
                            kolom[13],
                            kolom[14],
                            kolom[15]
                    );

                    // Menambahkan data ke linked list
                    dataPutusan.add(putusan);
                    hasil.tambahBerhasil();

                } catch (Exception e) {
                    hasil.tambahGagal(nomorBaris, e.getMessage());
                }
            }
        }

        return hasil;
    }

    private static void wajibDiisi(String nilai, String namaKolom) {
        if (nilai == null || nilai.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Kolom " + namaKolom + " wajib diisi."
            );
        }
    }

    private static LocalDate parseTanggal(String nilai) {
        if (nilai == null || nilai.trim().isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(nilai.trim());
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(
                        nilai.trim(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                );
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException(
                        "Format tanggal tidak valid: " + nilai
                );
            }
        }
    }

    private static double parseDouble(String nilai) {
        if (nilai == null || nilai.trim().isEmpty()) {
            return 0;
        }

        try {
            return Double.parseDouble(
                    nilai.trim().replace(',', '.')
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Angka berat tidak valid: " + nilai
            );
        }
    }

    private static int parseInt(String nilai) {
        if (nilai == null || nilai.trim().isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(nilai.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Angka bulan tidak valid: " + nilai
            );
        }
    }

    private static long parseLong(String nilai) {
        if (nilai == null || nilai.trim().isEmpty()) {
            return 0;
        }

        try {
            return Long.parseLong(nilai.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Nominal denda tidak valid: " + nilai
            );
        }
    }

    // Membaca CSV dengan memperhatikan tanda kutip
    private static String[] parseCSV(String baris) {
        String[] hasil = new String[16];
        StringBuilder nilai = new StringBuilder();

        int jumlahKolom = 0;
        boolean dalamKutip = false;

        for (int i = 0; i < baris.length(); i++) {
            char karakter = baris.charAt(i);

            if (karakter == '"') {
                if (dalamKutip && i + 1 < baris.length()
                        && baris.charAt(i + 1) == '"') {
                    nilai.append('"');
                    i++;
                } else {
                    dalamKutip = !dalamKutip;
                }
            } else if (karakter == ',' && !dalamKutip) {
                if (jumlahKolom >= 16) {
                    throw new IllegalArgumentException(
                            "Jumlah kolom CSV melebihi 16."
                    );
                }

                hasil[jumlahKolom++] = nilai.toString().trim();
                nilai.setLength(0);
            } else {
                nilai.append(karakter);
            }
        }

        if (dalamKutip) {
            throw new IllegalArgumentException(
                    "Tanda kutip CSV tidak berpasangan."
            );
        }

        if (jumlahKolom >= 16) {
            throw new IllegalArgumentException(
                    "Jumlah kolom CSV melebihi 16."
            );
        }

        hasil[jumlahKolom++] = nilai.toString().trim();

        String[] kolomLengkap = new String[jumlahKolom];
        System.arraycopy(hasil, 0, kolomLengkap, 0, jumlahKolom);

        return kolomLengkap;
    }
}
