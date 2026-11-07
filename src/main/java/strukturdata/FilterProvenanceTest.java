
package strukturdata;

import model.Putusan;

public class FilterProvenanceTest {

    public static void main(String[] args) throws Exception {

        StrukturDataUtama<Putusan> data =
                new StrukturDataUtama<>();

        CSVImporter importer = new CSVImporter();

        CSVImporter.ImportResult hasil =
                importer.importCSV("data/putusan-50.csv", data);

        int jumlahCocok = 0;
        int provenanceTersedia = 0;

        System.out.println("===== PENGUJIAN FILTER GABUNGAN =====");

        for (int i = 0; i < data.size(); i++) {

            Putusan p = data.get(i);

            boolean cocokPengadilan =
                    p.getPengadilan() != null &&
                            p.getPengadilan().equalsIgnoreCase(
                                    "Pengadilan Negeri Kendari");

            boolean cocokNarkotika =
                    p.getJenisNarkotika() != null &&
                            p.getJenisNarkotika().equalsIgnoreCase("Sabu");

            if (cocokPengadilan && cocokNarkotika) {

                jumlahCocok++;

                if (p.getSourcePages() != null &&
                        !p.getSourcePages().trim().isEmpty()) {

                    provenanceTersedia++;
                }
            }
        }

        System.out.println("Data berhasil diimpor: " +
                hasil.getBerhasil());

        System.out.println("Data sesuai filter: " +
                jumlahCocok);

        System.out.println("Data dengan provenance: " +
                provenanceTersedia);

        System.out.println("Invariant: " +
                data.validateInvariant());

        if (hasil.getBerhasil() == 50
                && jumlahCocok == 49
                && provenanceTersedia == 49
                && data.validateInvariant()) {

            System.out.println("STATUS: PENGUJIAN BERHASIL");

        } else {
            System.out.println("STATUS: PENGUJIAN GAGAL");
            throw new AssertionError(
                    "Hasil filter atau provenance tidak sesuai."
            );
        }
    }
}
