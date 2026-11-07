
package strukturdata;

public class LinkedListValidationTest {

    public static void main(String[] args) {

        StrukturDataUtama<String> data = new StrukturDataUtama<>();

        // Menambahkan lima data pengujian
        data.add("P001");
        data.add("P002");
        data.add("P003");
        data.add("P004");
        data.add("P005");

        System.out.println("===== PENGUJIAN LINKED LIST =====");

        // Pengujian pencarian
        System.out.println("\n1. PENGUJIAN PENCARIAN");

        System.out.println("P001 ditemukan di indeks: " +
                data.indexOf("P001"));

        System.out.println("P005 ditemukan di indeks: " +
                data.indexOf("P005"));

        System.out.println("P999 tidak ditemukan: " +
                (data.indexOf("P999") == -1));

        // Penghapusan data pertama
        System.out.println("\n2. PENGHAPUSAN DATA PERTAMA");

        System.out.println("Data dihapus: " + data.remove(0));
        System.out.println("Invariant: " + data.validateInvariant());

        // Penghapusan data tengah
        System.out.println("\n3. PENGHAPUSAN DATA TENGAH");

        System.out.println("Data dihapus: " + data.remove(1));
        System.out.println("Invariant: " + data.validateInvariant());

        // Penghapusan data terakhir
        System.out.println("\n4. PENGHAPUSAN DATA TERAKHIR");

        System.out.println("Data dihapus: " +
                data.remove(data.size() - 1));

        System.out.println("Invariant: " + data.validateInvariant());

        // Menampilkan sisa data
        System.out.println("\n5. SISA DATA");

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }

        System.out.println("Jumlah data tersisa: " + data.size());

        if (data.size() == 2
                && data.get(0).equals("P002")
                && data.get(1).equals("P004")
                && data.validateInvariant()
                && data.indexOf("P999") == -1) {

            System.out.println("\nSTATUS: PENGUJIAN BERHASIL");

        } else {
            throw new AssertionError("Pengujian Linked List gagal.");
        }
    }
}
