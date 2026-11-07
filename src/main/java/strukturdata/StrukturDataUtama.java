
package strukturdata;

/**
 * Implementasi Singly Linked List secara mandiri.
 * @param <T> tipe data yang disimpan
 */
public class StrukturDataUtama<T> {

    // Node untuk menyimpan data dan referensi node berikutnya
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Referensi node pertama dan terakhir
    private Node<T> head;
    private Node<T> tail;

    // Jumlah elemen dalam linked list
    private int size;

    // Constructor
    public StrukturDataUtama() {
        head = null;
        tail = null;
        size = 0;
    }

    // Menambahkan data di bagian akhir
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // Mengambil data berdasarkan indeks
    public T get(int index) {
        checkIndex(index);

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    // Menghapus data berdasarkan indeks
    public T remove(int index) {
        checkIndex(index);

        Node<T> removedNode;

        if (index == 0) {
            removedNode = head;
            head = head.next;

            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> previous = head;

            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }

            removedNode = previous.next;
            previous.next = removedNode.next;

            if (removedNode == tail) {
                tail = previous;
            }
        }

        size--;
        return removedNode.data;
    }

    // Mencari indeks data
    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (java.util.Objects.equals(current.data, data)) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    // Mengembalikan jumlah elemen
    public int size() {
        return size;
    }

    // Memeriksa apakah linked list kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Menghapus seluruh data
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Memeriksa konsistensi struktur linked list
    public boolean validateInvariant() {
        if (size < 0) {
            return false;
        }

        if (size == 0) {
            return head == null && tail == null;
        }

        if (head == null || tail == null || tail.next != null) {
            return false;
        }

        int count = 0;
        Node<T> current = head;
        Node<T> last = null;

        while (current != null) {
            count++;
            last = current;
            current = current.next;
        }

        return count == size && last == tail;
    }

    // Memeriksa indeks
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Indeks tidak valid: " + index
            );
        }
    }
}
