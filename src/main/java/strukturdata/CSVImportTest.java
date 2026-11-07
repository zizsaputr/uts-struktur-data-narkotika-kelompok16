
package strukturdata;

import model.Putusan;

public class CSVImportTest {

    public static void main(String[] args) throws Exception {

        StrukturDataUtama<Putusan> data =
                new StrukturDataUtama<>();

        CSVImporter importer = new CSVImporter();

        CSVImporter.ImportResult hasil =
                importer.importCSV("data/putusan-50.csv", data);

        System.out.println("===== HASIL IMPOR DATA PUTUSAN =====");
        System.out.println("Data berhasil : " + hasil.getBerhasil());
        System.out.println("Data gagal    : " + hasil.getGagal());
        System.out.println("Total data    : " + data.size());
        System.out.println("Invariant     : " + data.validateInvariant());

        if (!hasil.getDetailError().isEmpty()) {
            System.out.println("\n===== DETAIL ERROR =====");
            System.out.println(hasil.getDetailError());
        }
    }
}
