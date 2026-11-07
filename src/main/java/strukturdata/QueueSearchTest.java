
package strukturdata;

public class QueueSearchTest {

    public static void main(String[] args) {

        QueueUtama<String> antrean = new QueueUtama<>();

        // Menambahkan tiga data ke dalam antrean
        antrean.enqueue("P001");
        antrean.enqueue("P002");
        antrean.enqueue("P003");

        System.out.println("===== PENGUJIAN PENCARIAN QUEUE =====");
        System.out.println("Jumlah awal: " + antrean.size());

        // Menguji pencarian data
        System.out.println("P001 ditemukan: " + antrean.contains("P001"));
        System.out.println("P003 ditemukan: " + antrean.contains("P003"));
        System.out.println("P999 tidak ditemukan: " + !antrean.contains("P999"));

        System.out.println("\n===== PEMERIKSAAN URUTAN =====");

        // Memastikan pencarian tidak mengubah urutan antrean
        System.out.println("Data terdepan: " + antrean.peek());
        System.out.println("Jumlah setelah pencarian: " + antrean.size());

        boolean urutanBenar =
                antrean.dequeue().equals("P001")
                        && antrean.dequeue().equals("P002")
                        && antrean.dequeue().equals("P003");

        System.out.println("Urutan tetap FIFO: " + urutanBenar);

        if (urutanBenar && antrean.isEmpty() && antrean.validateInvariant()) {
            System.out.println("\nSTATUS: PENGUJIAN BERHASIL");
        } else {
            throw new AssertionError("Pengujian Queue gagal.");
        }
    }
}
