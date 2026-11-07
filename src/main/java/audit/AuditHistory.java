
package audit;

public class AuditHistory {

    // Node untuk menyimpan data audit
    private static class Node {
        AuditLog data;
        Node next;

        Node(AuditLog data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Constructor
    public AuditHistory() {
        head = null;
        tail = null;
        size = 0;
    }

    // Menambahkan riwayat aktivitas
    public void tambahRiwayat(AuditLog log) {
        Node newNode = new Node(log);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // Menampilkan seluruh riwayat
    public void tampilkanRiwayat() {
        if (head == null) {
            System.out.println("Belum ada riwayat aktivitas.");
            return;
        }

        Node current = head;
        int nomor = 1;

        System.out.println("===== RIWAYAT AKTIVITAS =====");

        while (current != null) {
            System.out.println(nomor + ". " + current.data);
            current = current.next;
            nomor++;
        }

        System.out.println("=============================");
    }

    // Menghitung jumlah riwayat
    public int jumlahRiwayat() {
        return size;
    }

    // Memeriksa apakah riwayat kosong
    public boolean isEmpty() {
        return head == null;
    }
}
