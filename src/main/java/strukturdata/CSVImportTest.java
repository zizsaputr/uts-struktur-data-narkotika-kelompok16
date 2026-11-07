
package strukturdata;

import model.Putusan;

public class CSVImportTest {

    public static void main(String[] args) {

        // Membuat linked list untuk menyimpan data putusan
        StrukturDataUtama<Putusan> dataPutusan =
                new StrukturDataUtama<>();

        // Membuat objek importer
        CSVImporter importer = new CSVImporter();

        try {
            // Membaca dataset CSV
            CSVImporter.ImportResult hasil =
                    importer.importCSV(
                            "data/putusan-50.csv",
                            dataPutusan
                    );

            // Menampilkan hasil impor
            System.out.println("===== HASIL IMPOR DATA PUTUSAN =====");
            System.out.println("Data berhasil : " + hasil.getBerhasil());
            System.out.println("Data gagal    : " + hasil.getGagal());
            System.out.println("Total data    : " + dataPutusan.size());

            // Memeriksa konsistensi linked list
            System.out.println(
                    "Invariant     : " + dataPutusan.validateInvariant()
            );

            // Menampilkan alasan kegagalan jika ada
            if (hasil.getGagal() > 0) {
                System.out.println("\n===== DETAIL DATA GAGAL =====");
                System.out.println(hasil.getDetailError());
            }

            // Menampilkan beberapa data hasil impor
            System.out.println("\n===== CONTOH DATA BERHASIL =====");

            int batas = Math.min(dataPutusan.size(), 3);

            for (int i = 0; i < batas; i++) {
                System.out.println(dataPutusan.get(i));
            }

        } catch (Exception e) {
            System.out.println("Gagal menjalankan impor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
