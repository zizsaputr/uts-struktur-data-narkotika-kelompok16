
package strukturdata;

import model.Putusan;
import audit.AuditService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PutusanManager {

    private StrukturDataUtama<Putusan> dataPutusan;
    private AuditService audit;
    private Scanner input;

    public PutusanManager(Scanner input) {
        this.input = input;
        this.dataPutusan = new StrukturDataUtama<>();
        this.audit = new AuditService();

        muatDataAwal();
    }

    // Mengimpor data CSV ketika aplikasi dimulai
    private void muatDataAwal() {
        try {
            CSVImporter importer = new CSVImporter();

            CSVImporter.ImportResult hasil =
                    importer.importCSV("data/putusan-50.csv", dataPutusan);

            System.out.println("\n===== IMPOR DATA AWAL =====");
            System.out.println("Data berhasil: " + hasil.getBerhasil());
            System.out.println("Data gagal   : " + hasil.getGagal());
            System.out.println("Total data   : " + dataPutusan.size());

            audit.catatAktivitas(
                    "IMPOR",
                    "Mengimpor " + hasil.getBerhasil() + " data putusan"
            );

        } catch (IOException e) {
            System.out.println("Gagal mengimpor CSV: " + e.getMessage());
            System.out.println("Aplikasi tetap dapat digunakan.");
        }
    }

    // Menyimpan seluruh data Linked List ke file CSV
    private void simpanDataKeCSV() {
        try {
            CSVExporter exporter = new CSVExporter();

            exporter.exportCSV(
                    "data/putusan-50.csv",
                    dataPutusan
            );

            System.out.println("Data berhasil disimpan ke CSV.");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan data CSV: " + e.getMessage());
        }
    }

    // Menambahkan data putusan
    public void tambahPutusan(Putusan putusan) {
        if (cariIndeks(putusan.getCaseId()) != -1) {
            System.out.println("Case ID sudah digunakan.");
            return;
        }

        dataPutusan.add(putusan);

        System.out.println("Data putusan berhasil ditambahkan.");

        audit.catatAktivitas(
                "TAMBAH",
                "Menambahkan putusan " + putusan.getCaseId()
        );

        simpanDataKeCSV();
    }

    // Menampilkan seluruh data
    public void tampilkanPutusan() {
        System.out.println("\n===== DAFTAR DATA PUTUSAN =====");

        if (dataPutusan.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        for (int i = 0; i < dataPutusan.size(); i++) {
            System.out.println("\nData ke-" + (i + 1));
            System.out.println(dataPutusan.get(i));
        }

        System.out.println("\nTotal data: " + dataPutusan.size());
    }

    // Menghitung jumlah data
    public int jumlahPutusan() {
        return dataPutusan.size();
    }

    // Mencari indeks berdasarkan Case ID
    private int cariIndeks(String caseId) {
        for (int i = 0; i < dataPutusan.size(); i++) {
            if (dataPutusan.get(i).getCaseId()
                    .equalsIgnoreCase(caseId)) {
                return i;
            }
        }

        return -1;
    }

    // Fitur pencarian
    private void cariPutusan() {
        System.out.println("\n===== PENCARIAN PUTUSAN =====");

        System.out.print("Masukkan Case ID: ");
        String caseId = input.nextLine().trim();

        int indeks = cariIndeks(caseId);

        if (indeks == -1) {
            System.out.println("Data putusan tidak ditemukan.");

            audit.catatAktivitas(
                    "CARI",
                    "Case ID tidak ditemukan: " + caseId
            );
        } else {
            System.out.println("Data putusan ditemukan:");
            System.out.println(dataPutusan.get(indeks));

            audit.catatAktivitas(
                    "CARI",
                    "Mencari putusan " + caseId
            );
        }
    }

    // Fitur penghapusan
    private void hapusPutusan() {
        System.out.println("\n===== HAPUS DATA PUTUSAN =====");

        System.out.print("Masukkan Case ID: ");
        String caseId = input.nextLine().trim();

        int indeks = cariIndeks(caseId);

        if (indeks == -1) {
            System.out.println("Data tidak ditemukan.");
            return;
        }

        Putusan dihapus = dataPutusan.remove(indeks);

        System.out.println("Data berhasil dihapus:");
        System.out.println(dihapus);

        audit.catatAktivitas(
                "HAPUS",
                "Menghapus putusan " + caseId
        );

        simpanDataKeCSV();
    }


    // Menu ekspor hasil pencarian dan laporan
    private void menuEkspor() {
        System.out.println("\n===== EKSPOR DATA DAN LAPORAN =====");
        System.out.println("1. Ekspor hasil pencarian");
        System.out.println("2. Ekspor laporan seluruh putusan");
        System.out.print("Pilih jenis ekspor: ");

        int pilihan = bacaAngka();

        List<Putusan> daftar = new ArrayList<>();
        String judul;

        if (pilihan == 1) {
            System.out.print("Masukkan Case ID: ");
            String caseId = input.nextLine().trim();

            int indeks = cariIndeks(caseId);

            if (indeks == -1) {
                System.out.println("Data putusan tidak ditemukan.");
                return;
            }

            daftar.add(dataPutusan.get(indeks));
            judul = "HASIL PENCARIAN PUTUSAN";

        } else if (pilihan == 2) {
            for (int i = 0; i < dataPutusan.size(); i++) {
                daftar.add(dataPutusan.get(i));
            }

            judul = "LAPORAN SELURUH PUTUSAN";

        } else {
            System.out.println("Pilihan tidak tersedia.");
            return;
        }

        System.out.println("\nFormat ekspor:");
        System.out.println("1. CSV");
        System.out.println("2. TXT");
        System.out.print("Pilih format: ");

        int format = bacaAngka();

        if (format != 1 && format != 2) {
            System.out.println("Format tidak tersedia.");
            return;
        }

        System.out.print("Masukkan lokasi dan nama file: ");
        String lokasi = input.nextLine().trim();

        if (lokasi.isEmpty()) {
            System.out.println("Lokasi file tidak boleh kosong.");
            return;
        }

        String ekstensi = format == 1 ? ".csv" : ".txt";

        if (!lokasi.toLowerCase().endsWith(ekstensi)) {
            lokasi += ekstensi;
        }

        try {
            CSVExporter exporter = new CSVExporter();

            if (format == 1) {
                exporter.exportCSV(lokasi, daftar);
            } else {
                exporter.exportTXT(lokasi, daftar, judul);
            }

            System.out.println("\nEkspor berhasil!");
            System.out.println("Lokasi file: " + lokasi);
            System.out.println("Jumlah data: " + daftar.size());

            audit.catatAktivitas(
                    "EKSPOR",
                    judul + " ke " + lokasi +
                    ". Jumlah data: " + daftar.size()
            );

        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Tidak memiliki izin menyimpan file: " +
                    e.getMessage());
        }
    }
    // Filter putusan berdasarkan beberapa kriteria
    private void filterPutusan() {
        System.out.println("\n===== FILTER DATA PUTUSAN =====");
        System.out.println("Kosongkan input jika kriteria tidak digunakan.");

        System.out.print("Jenis narkotika: ");
        String jenis = input.nextLine().trim();

        System.out.print("Berat minimum (gram): ");
        String beratMinInput = input.nextLine().trim();

        System.out.print("Berat maksimum (gram): ");
        String beratMaxInput = input.nextLine().trim();

        System.out.print("Pasal terbukti: ");
        String pasal = input.nextLine().trim();

        System.out.print("Vonis minimum (bulan): ");
        String vonisMinInput = input.nextLine().trim();

        System.out.print("Vonis maksimum (bulan): ");
        String vonisMaxInput = input.nextLine().trim();

        double beratMin = 0;
        double beratMax = Double.MAX_VALUE;
        int vonisMin = 0;
        int vonisMax = Integer.MAX_VALUE;

        try {
            if (!beratMinInput.isEmpty()) {
                beratMin = Double.parseDouble(
                        beratMinInput.replace(',', '.')
                );
            }

            if (!beratMaxInput.isEmpty()) {
                beratMax = Double.parseDouble(
                        beratMaxInput.replace(',', '.')
                );
            }

            if (!vonisMinInput.isEmpty()) {
                vonisMin = Integer.parseInt(vonisMinInput);
            }

            if (!vonisMaxInput.isEmpty()) {
                vonisMax = Integer.parseInt(vonisMaxInput);
            }

            if (!Double.isFinite(beratMin) ||
                    !Double.isFinite(beratMax)) {
                System.out.println("Berat harus berupa angka yang valid.");
                return;
            }

            if (beratMin < 0 || beratMax < 0 ||
                    vonisMin < 0 || vonisMax < 0) {
                System.out.println("Nilai filter tidak boleh negatif.");
                return;
            }

            if (beratMin > beratMax || vonisMin > vonisMax) {
                System.out.println("Rentang minimum tidak boleh melebihi maksimum.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid.");
            return;
        }

        int jumlah = 0;

        System.out.println("\n===== HASIL FILTER PUTUSAN =====");

        for (int i = 0; i < dataPutusan.size(); i++) {
            Putusan p = dataPutusan.get(i);
            boolean cocok = true;

            if (!jenis.isEmpty()) {
                cocok = p.getJenisNarkotika() != null &&
                        p.getJenisNarkotika().toLowerCase()
                                .contains(jenis.toLowerCase());
            }

            if (cocok && !beratMinInput.isEmpty()) {
                cocok = p.getBeratBarangBuktiGram() >= beratMin;
            }

            if (cocok && !beratMaxInput.isEmpty()) {
                cocok = p.getBeratBarangBuktiGram() <= beratMax;
            }

            if (cocok && !pasal.isEmpty()) {
                cocok = p.getPasalTerbukti() != null &&
                        p.getPasalTerbukti().toLowerCase()
                                .contains(pasal.toLowerCase());
            }

            if (cocok && !vonisMinInput.isEmpty()) {
                cocok = p.getVonisBulan() >= vonisMin;
            }

            if (cocok && !vonisMaxInput.isEmpty()) {
                cocok = p.getVonisBulan() <= vonisMax;
            }

            if (cocok) {
                jumlah++;
                System.out.println("\nData ke-" + jumlah);
                System.out.println(p);
            }
        }

        System.out.println("\nJumlah data sesuai filter: " + jumlah);
        System.out.println(
                "Invariant struktur utama: " +
                dataPutusan.validateInvariant()
        );

        audit.catatAktivitas(
                "FILTER",
                "Melakukan filter gabungan. Jumlah hasil: " + jumlah
        );
    }
    // Memproses data menggunakan Queue FIFO
    private void prosesAntrean() {
        System.out.println("\n===== PROSES ANTREAN PUTUSAN =====");

        if (dataPutusan.isEmpty()) {
            System.out.println("Tidak ada data untuk diproses.");
            return;
        }

        QueueUtama<Putusan> antrean = new QueueUtama<>();

        for (int i = 0; i < dataPutusan.size(); i++) {
            antrean.enqueue(dataPutusan.get(i));
        }

        System.out.println("Jumlah antrean: " + antrean.size());

        while (!antrean.isEmpty()) {
            Putusan p = antrean.dequeue();

            System.out.println("\nMemproses Case ID: " + p.getCaseId());
            System.out.println("Nomor perkara: " + p.getNomorPerkara());
        }

        System.out.println("\nSeluruh antrean berhasil diproses.");
        System.out.println("Data utama tetap tersimpan.");

        audit.catatAktivitas(
                "QUEUE",
                "Memproses antrean " + dataPutusan.size() + " putusan"
        );
    }

    // Menampilkan riwayat Audit
    private void tampilkanAudit() {
        System.out.println("\n===== RIWAYAT AKTIVITAS =====");

        audit.tampilkanSemuaRiwayat();

        System.out.println(
                "Total aktivitas: " + audit.getJumlahAktivitas()
        );
    }

    // Membaca input angka dengan aman
    private int bacaAngka() {
        while (!input.hasNextInt()) {
            System.out.println("Masukkan harus berupa angka!");
            input.nextLine();
            System.out.print("Masukkan pilihan: ");
        }

        int angka = input.nextInt();
        input.nextLine();

        return angka;
    }

    private double bacaBerat() {
        while (true) {
            try {
                System.out.print("Berat barang bukti (gram): ");
                double nilai = Double.parseDouble(
                        input.nextLine().trim().replace(',', '.')
                );

                if (Double.isFinite(nilai) && nilai >= 0) {
                    return nilai;
                }

                System.out.println("Berat tidak boleh negatif.");

            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }

    private int bacaBulan(String keterangan) {
        while (true) {
            try {
                System.out.print(keterangan);
                int nilai = Integer.parseInt(input.nextLine().trim());

                if (nilai >= 0) {
                    return nilai;
                }

                System.out.println("Nilai tidak boleh negatif.");

            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }

    private long bacaDenda() {
        while (true) {
            try {
                System.out.print("Denda (rupiah): ");
                long nilai = Long.parseLong(input.nextLine().trim());

                if (nilai >= 0) {
                    return nilai;
                }

                System.out.println("Denda tidak boleh negatif.");

            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }

    private String bacaWajib(String keterangan) {
        while (true) {
            System.out.print(keterangan);
            String nilai = input.nextLine().trim();

            if (!nilai.isEmpty()) {
                return nilai;
            }

            System.out.println("Kolom wajib diisi.");
        }
    }

    private LocalDate bacaTanggal() {
        while (true) {
            System.out.print(
                    "Tanggal putusan (yyyy-MM-dd, kosong jika tidak ada): "
            );

            String tanggal = input.nextLine().trim();

            if (tanggal.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(tanggal);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid.");
            }
        }
    }

    // Input data putusan melalui menu
    private void inputPutusan() {
        System.out.println("\n===== TAMBAH DATA PUTUSAN =====");

        String caseId = bacaWajib("Case ID: ");

        if (cariIndeks(caseId) != -1) {
            System.out.println("Case ID sudah digunakan.");
            return;
        }

        String nomorPerkara = bacaWajib("Nomor perkara: ");
        String pengadilan = bacaWajib("Pengadilan: ");
        LocalDate tanggalPutusan = bacaTanggal();
        String terdakwaKode = bacaWajib("Kode terdakwa: ");
        String jenisNarkotika = bacaWajib("Jenis narkotika: ");

        double berat = bacaBerat();

        System.out.print("Pasal dakwaan: ");
        String pasalDakwaan = input.nextLine();

        System.out.print("Pasal terbukti: ");
        String pasalTerbukti = input.nextLine();

        int tuntutan = bacaBulan("Tuntutan (bulan): ");
        int vonis = bacaBulan("Vonis (bulan): ");
        long denda = bacaDenda();

        System.out.print("Barang bukti: ");
        String barangBukti = input.nextLine();

        System.out.print("Keadaan memberatkan: ");
        String memberatkan = input.nextLine();

        System.out.print("Keadaan meringankan: ");
        String meringankan = input.nextLine();

        System.out.print("Source pages: ");
        String sourcePages = input.nextLine();

        Putusan putusan = new Putusan(
                caseId,
                nomorPerkara,
                pengadilan,
                tanggalPutusan,
                terdakwaKode,
                jenisNarkotika,
                berat,
                pasalDakwaan,
                pasalTerbukti,
                tuntutan,
                vonis,
                denda,
                barangBukti,
                memberatkan,
                meringankan,
                sourcePages
        );

        tambahPutusan(putusan);
    }


    // Menu pengurutan vonis menggunakan Merge Sort
    private void urutkanPutusan() {
        if (dataPutusan.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        System.out.println("\n===== PENGURUTAN VONIS =====");
        System.out.println("1. Ascending (terendah ke tertinggi)");
        System.out.println("2. Descending (tertinggi ke terendah)");
        System.out.print("Pilih urutan: ");

        int pilihan = bacaAngka();

        if (pilihan != 1 && pilihan != 2) {
            System.out.println("Pilihan tidak tersedia.");
            return;
        }

        List<Putusan> daftar = new ArrayList<>();

        for (int i = 0; i < dataPutusan.size(); i++) {
            daftar.add(dataPutusan.get(i));
        }

        mergeSortVonis(daftar, 0, daftar.size() - 1, pilihan == 1);

        System.out.println("\n===== HASIL PENGURUTAN VONIS =====");

        for (int i = 0; i < daftar.size(); i++) {
            Putusan p = daftar.get(i);

            System.out.println(
                    (i + 1) + ". Case ID: " + p.getCaseId()
                    + " | Vonis: " + p.getVonisBulan() + " bulan"
                    + " | Nomor perkara: " + p.getNomorPerkara()
            );
        }

        System.out.println("Total data diurutkan: " + daftar.size());
        System.out.println(
                "Invariant Linked List: " +
                dataPutusan.validateInvariant()
        );

        audit.catatAktivitas(
                "PENGURUTAN",
                "Mengurutkan " + daftar.size() +
                " data berdasarkan vonis secara " +
                (pilihan == 1 ? "ascending" : "descending")
        );
    }

    // Proses utama Merge Sort
    private void mergeSortVonis(
            List<Putusan> daftar,
            int kiri,
            int kanan,
            boolean ascending
    ) {
        if (kiri >= kanan) {
            return;
        }

        int tengah = kiri + (kanan - kiri) / 2;

        mergeSortVonis(daftar, kiri, tengah, ascending);
        mergeSortVonis(daftar, tengah + 1, kanan, ascending);

        gabungkanVonis(daftar, kiri, tengah, kanan, ascending);
    }

    // Menggabungkan dua bagian yang sudah terurut
    private void gabungkanVonis(
            List<Putusan> daftar,
            int kiri,
            int tengah,
            int kanan,
            boolean ascending
    ) {
        List<Putusan> sementara = new ArrayList<>();

        int i = kiri;
        int j = tengah + 1;

        while (i <= tengah && j <= kanan) {
            int vonisKiri = daftar.get(i).getVonisBulan();
            int vonisKanan = daftar.get(j).getVonisBulan();

            boolean ambilKiri = ascending
                    ? vonisKiri <= vonisKanan
                    : vonisKiri >= vonisKanan;

            if (ambilKiri) {
                sementara.add(daftar.get(i));
                i++;
            } else {
                sementara.add(daftar.get(j));
                j++;
            }
        }

        while (i <= tengah) {
            sementara.add(daftar.get(i));
            i++;
        }

        while (j <= kanan) {
            sementara.add(daftar.get(j));
            j++;
        }

        for (int k = 0; k < sementara.size(); k++) {
            daftar.set(kiri + k, sementara.get(k));
        }
    }
    // Menu utama
    private void jalankan() {
        int pilihan;

        do {
            System.out.println("\n======================================");
            System.out.println(" SISTEM PENGELOLAAN PUTUSAN NARKOTIKA");
            System.out.println("======================================");
            System.out.println("1. Tambah data putusan");
            System.out.println("2. Tampilkan seluruh data putusan");
            System.out.println("3. Jumlah data putusan");
            System.out.println("4. Cari putusan");
            System.out.println("5. Hapus data putusan");
            System.out.println("6. Filter data putusan");
            System.out.println("7. Proses antrean Queue");
            System.out.println("8. Riwayat aktivitas Audit");
            System.out.println("9. Keluar");
            System.out.println("10. Ekspor data dan laporan");
            System.out.println("11. Pengurutan vonis");
            System.out.println("======================================");
            System.out.print("Pilih menu: ");

            pilihan = bacaAngka();

            switch (pilihan) {
                case 1:
                    inputPutusan();
                    break;

                case 2:
                    tampilkanPutusan();
                    break;

                case 3:
                    System.out.println(
                            "Jumlah data putusan: " + jumlahPutusan()
                    );
                    System.out.println(
                            "Invariant valid: " +
                                    dataPutusan.validateInvariant()
                    );
                    break;

                case 4:
                    cariPutusan();
                    break;

                case 5:
                    hapusPutusan();
                    break;

                case 6:
                    filterPutusan();
                    break;

                case 7:
                    prosesAntrean();
                    break;

                case 8:
                    tampilkanAudit();
                    break;

                case 9:
                    simpanDataKeCSV();
                    System.out.println("Terima kasih. Program selesai.");
                    break;

                case 10:
                    menuEkspor();
                    break;

                case 11:
                    urutkanPutusan();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 9);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        PutusanManager manager = new PutusanManager(input);
        manager.jalankan();

        input.close();
    }
}
