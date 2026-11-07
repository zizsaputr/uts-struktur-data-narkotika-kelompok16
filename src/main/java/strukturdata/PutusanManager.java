package strukturdata;

import model.Putusan;
import java.time.LocalDate;
import java.util.Scanner;

public class PutusanManager {

    private StrukturDataUtama<Putusan> dataPutusan;

    public PutusanManager() {
        dataPutusan = new StrukturDataUtama<>();
    }

    // Menambahkan data putusan
    public void tambahPutusan(Putusan putusan) {
        dataPutusan.add(putusan);
        System.out.println("Data putusan berhasil ditambahkan.");
    }

    // Menampilkan seluruh data putusan
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

    // Menampilkan jumlah data
    public int jumlahPutusan() {
        return dataPutusan.size();
    }

    // Memeriksa invariant
    public boolean cekInvariant() {
        return dataPutusan.validateInvariant();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        PutusanManager manager = new PutusanManager();

        int pilihan;

        do {
            System.out.println("\n===== MENU DATA PUTUSAN =====");
            System.out.println("1. Tambah data putusan");
            System.out.println("2. Tampilkan data putusan");
            System.out.println("3. Jumlah data putusan");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            while (!input.hasNextInt()) {
                System.out.println("Pilihan harus berupa angka!");
                input.nextLine();
                System.out.print("Pilih menu: ");
            }

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    System.out.println("\n===== TAMBAH DATA PUTUSAN =====");

                    System.out.print("Case ID: ");
                    String caseId = input.nextLine();

                    System.out.print("Nomor perkara: ");
                    String nomorPerkara = input.nextLine();

                    System.out.print("Pengadilan: ");
                    String pengadilan = input.nextLine();

                    System.out.print("Tanggal putusan (yyyy-MM-dd, kosong jika tidak ada): ");
                    String tanggal = input.nextLine();

                    LocalDate tanggalPutusan = tanggal.isBlank()
                            ? null : LocalDate.parse(tanggal);

                    System.out.print("Kode terdakwa: ");
                    String terdakwaKode = input.nextLine();

                    System.out.print("Jenis narkotika: ");
                    String jenisNarkotika = input.nextLine();

                    System.out.print("Berat barang bukti (gram): ");
                    double berat = input.nextDouble();
                    input.nextLine();

                    System.out.print("Pasal dakwaan: ");
                    String pasalDakwaan = input.nextLine();

                    System.out.print("Pasal terbukti: ");
                    String pasalTerbukti = input.nextLine();

                    System.out.print("Tuntutan (bulan): ");
                    int tuntutan = input.nextInt();
                    input.nextLine();

                    System.out.print("Vonis (bulan): ");
                    int vonis = input.nextInt();
                    input.nextLine();

                    System.out.print("Denda (rupiah): ");
                    long denda = input.nextLong();
                    input.nextLine();

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

                    manager.tambahPutusan(putusan);
                    break;

                case 2:
                    manager.tampilkanPutusan();
                    break;

                case 3:
                    System.out.println("Jumlah data putusan: " + manager.jumlahPutusan());
                    System.out.println("Invariant valid: " + manager.cekInvariant());
                    break;

                case 4:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 4);

        input.close();
    }
}