
package strukturdata;

import model.Putusan;

import java.time.LocalDate;

public class IntegrasiTest {

    public static void main(String[] args) {

        StrukturDataUtama<Putusan> data = new StrukturDataUtama<>();
        QueueUtama<Putusan> antrean = new QueueUtama<>();

        Putusan p1 = new Putusan(
                "P001", "001/Pid.Sus/2026", "PN Surabaya",
                LocalDate.of(2026, 1, 10), "T001",
                "Sabu", 2.5, "Pasal 112", "Pasal 112",
                60, 48, 100000000L, "Sabu 2.5 gram",
                "Jumlah barang bukti", "Belum pernah dihukum",
                "Halaman 1-10"
        );

        Putusan p2 = new Putusan(
                "P002", "002/Pid.Sus/2026", "PN Surabaya",
                LocalDate.of(2026, 2, 10), "T002",
                "Ganja", 5.0, "Pasal 111", "Pasal 111",
                48, 36, 50000000L, "Ganja 5 gram",
                "Jumlah barang bukti", "Bersikap sopan",
                "Halaman 1-12"
        );

        data.add(p1);
        data.add(p2);

        antrean.enqueue(p1);
        antrean.enqueue(p2);

        System.out.println("=== PENGUJIAN INTEGRASI ===");

        System.out.println("Jumlah data putusan: " + data.size());
        System.out.println("Putusan pertama: " + data.get(0).getCaseId());

        System.out.println("Jumlah antrean: " + antrean.size());
        System.out.println("Antrean pertama: " + antrean.peek().getCaseId());

        System.out.println("Linked List valid: " + data.validateInvariant());
        System.out.println("Queue valid: " + antrean.validateInvariant());
    }
}
