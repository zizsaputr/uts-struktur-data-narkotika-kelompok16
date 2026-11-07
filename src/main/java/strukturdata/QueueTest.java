
package strukturdata;

public class QueueTest {

    public static void main(String[] args) {

        QueueUtama<String> antrean = new QueueUtama<>();

        System.out.println("=== PENGUJIAN QUEUE ===");

        // Menguji enqueue
        antrean.enqueue("Putusan 001");
        antrean.enqueue("Putusan 002");
        antrean.enqueue("Putusan 003");

        System.out.println("Jumlah antrean: " + antrean.size());

        // Menguji peek
        System.out.println("Antrean terdepan: " + antrean.peek());

        // Menguji dequeue
        System.out.println("Data diproses: " + antrean.dequeue());
        System.out.println("Data diproses: " + antrean.dequeue());

        // Menguji size dan isEmpty
        System.out.println("Sisa antrean: " + antrean.size());
        System.out.println("Apakah kosong: " + antrean.isEmpty());

        // Menguji validateInvariant
        System.out.println("Invariant valid: " + antrean.validateInvariant());

        // Menguji dequeue terakhir
        System.out.println("Data diproses: " + antrean.dequeue());

        // Menguji clear
        antrean.clear();

        System.out.println("Jumlah setelah clear: " + antrean.size());
        System.out.println("Apakah kosong setelah clear: " + antrean.isEmpty());
        System.out.println("Invariant setelah clear: " + antrean.validateInvariant());
    }
}
