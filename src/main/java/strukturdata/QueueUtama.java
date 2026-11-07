
package strukturdata;

/**
 * Implementasi Queue menggunakan Singly Linked List.
 * Menggunakan prinsip FIFO (First In, First Out).
 *
 * @param <T> tipe data yang disimpan
 */
public class QueueUtama<T> {

    // Node untuk menyimpan data dan referensi berikutnya
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Referensi elemen paling depan dan belakang
    private Node<T> front;
    private Node<T> rear;

    // Jumlah elemen dalam antrean
    private int size;

    // Constructor
    public QueueUtama() {
        front = null;
        rear = null;
        size = 0;
    }

    // Menambahkan data ke belakang antrean
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    // Mengambil dan menghapus data paling depan
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Antrean kosong.");
        }

        T data = front.data;
        front = front.next;
        size--;

        if (front == null) {
            rear = null;
        }

        return data;
    }

    // Melihat data paling depan tanpa menghapusnya
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Antrean kosong.");
        }

        return front.data;
    }

    // Mencari data tanpa mengubah urutan antrean
    public boolean contains(T data) {
        Node<T> current = front;

        while (current != null) {
            if (java.util.Objects.equals(current.data, data)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // Mengembalikan jumlah elemen
    public int size() {
        return size;
    }

    // Memeriksa apakah antrean kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Menghapus seluruh elemen
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    // Memeriksa konsistensi struktur antrean
    public boolean validateInvariant() {
        if (size < 0) {
            return false;
        }

        if (size == 0) {
            return front == null && rear == null;
        }

        if (front == null || rear == null || rear.next != null) {
            return false;
        }

        int count = 0;
        Node<T> current = front;
        Node<T> last = null;

        while (current != null) {
            count++;
            last = current;
            current = current.next;
        }

        return count == size && last == rear;
    }
}
