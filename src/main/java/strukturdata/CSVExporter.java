
package strukturdata;

import model.Putusan;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVExporter {

    private static final String HEADER =
            "caseId,nomorPerkara,pengadilan,tanggalPutusan,terdakwaKode," +
            "jenisNarkotika,beratBarangBuktiGram,pasalDakwaan,pasalTerbukti," +
            "tuntutanBulan,vonisBulan,dendaRupiah,barangBukti," +
            "keadaanMemberatkan,keadaanMeringankan,sourcePages";

    public void exportCSV(
            String filePath,
            StrukturDataUtama<Putusan> dataPutusan
    ) throws IOException {

        Path path = Path.of(filePath);

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardCharsets.UTF_8
        )) {

            writer.write(HEADER);
            writer.newLine();

            for (int i = 0; i < dataPutusan.size(); i++) {

                Putusan p = dataPutusan.get(i);

                List<String> kolom = new ArrayList<>();

                kolom.add(p.getCaseId());
                kolom.add(p.getNomorPerkara());
                kolom.add(p.getPengadilan());
                kolom.add(p.getTanggalPutusan() == null
                        ? "" : p.getTanggalPutusan().toString());
                kolom.add(p.getTerdakwaKode());
                kolom.add(p.getJenisNarkotika());
                kolom.add(String.valueOf(p.getBeratBarangBuktiGram()));
                kolom.add(p.getPasalDakwaan());
                kolom.add(p.getPasalTerbukti());
                kolom.add(String.valueOf(p.getTuntutanBulan()));
                kolom.add(String.valueOf(p.getVonisBulan()));
                kolom.add(String.valueOf(p.getDendaRupiah()));
                kolom.add(p.getBarangBukti());
                kolom.add(p.getKeadaanMemberatkan());
                kolom.add(p.getKeadaanMeringankan());
                kolom.add(p.getSourcePages());

                StringBuilder baris = new StringBuilder();

                for (int j = 0; j < kolom.size(); j++) {
                    if (j > 0) {
                        baris.append(",");
                    }

                    baris.append(escapeCSV(kolom.get(j)));
                }

                writer.write(baris.toString());
                writer.newLine();
            }
        }
    }


    // Mengekspor daftar putusan ke CSV
    public void exportCSV(String filePath, List<Putusan> daftar)
            throws IOException {

        Path path = Path.of(filePath);

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8)) {

            writer.write(HEADER);
            writer.newLine();

            for (Putusan p : daftar) {
                writer.write(toCSVRow(p));
                writer.newLine();
            }
        }
    }

    // Mengekspor laporan putusan ke TXT
    public void exportTXT(
            String filePath,
            List<Putusan> daftar,
            String judul
    ) throws IOException {

        Path path = Path.of(filePath);

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8)) {

            writer.write("===== " + judul + " =====");
            writer.newLine();
            writer.write("Tanggal ekspor: " +
                    java.time.LocalDateTime.now());
            writer.newLine();
            writer.write("Jumlah data: " + daftar.size());
            writer.newLine();
            writer.newLine();

            for (int i = 0; i < daftar.size(); i++) {
                writer.write("DATA KE-" + (i + 1));
                writer.newLine();
                writer.write(daftar.get(i).toString());
                writer.newLine();
                writer.write("----------------------------------------");
                writer.newLine();
            }
        }
    }

    // Mengubah satu putusan menjadi baris CSV
    private String toCSVRow(Putusan p) {
        List<String> kolom = new ArrayList<>();

        kolom.add(p.getCaseId());
        kolom.add(p.getNomorPerkara());
        kolom.add(p.getPengadilan());
        kolom.add(p.getTanggalPutusan() == null
                ? "" : p.getTanggalPutusan().toString());
        kolom.add(p.getTerdakwaKode());
        kolom.add(p.getJenisNarkotika());
        kolom.add(String.valueOf(p.getBeratBarangBuktiGram()));
        kolom.add(p.getPasalDakwaan());
        kolom.add(p.getPasalTerbukti());
        kolom.add(String.valueOf(p.getTuntutanBulan()));
        kolom.add(String.valueOf(p.getVonisBulan()));
        kolom.add(String.valueOf(p.getDendaRupiah()));
        kolom.add(p.getBarangBukti());
        kolom.add(p.getKeadaanMemberatkan());
        kolom.add(p.getKeadaanMeringankan());
        kolom.add(p.getSourcePages());

        StringBuilder baris = new StringBuilder();

        for (int i = 0; i < kolom.size(); i++) {
            if (i > 0) {
                baris.append(",");
            }
            baris.append(escapeCSV(kolom.get(i)));
        }

        return baris.toString();
    }
    private String escapeCSV(String nilai) {

        if (nilai == null) {
            nilai = "";
        }

        // Menjaga setiap data tetap dalam satu baris CSV.
        nilai = nilai.replace("\r", " ").replace("\n", " ");

        // Menggandakan tanda kutip agar sesuai format CSV.
        nilai = nilai.replace("\"", "\"\"");

        return "\"" + nilai + "\"";
    }
}
