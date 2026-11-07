
package strukturdata;

import model.Putusan;
import java.time.LocalDate;

public class AddThreeRecordsTest {

    public static void main(String[] args) {

        StrukturDataUtama<Putusan> data =
                new StrukturDataUtama<>();

        // Membuat tiga data putusan
        Putusan p1 = new Putusan(
                "P101", "101/Pid.Sus/2026/PN",
                "Pengadilan Negeri Kendari",
                LocalDate.of(2026, 1, 10),
                "T-101", "Sabu", 1.5,
                "Pasal 112", "Pasal 112",
                12, 8, 1000000,
                "Barang bukti sabu",
                "Memberatkan", "Meringankan", "Halaman 1"
        );

        Putusan p2 = new Putusan(
                "P102", "102/Pid.Sus/2026/PN",
                "Pengadilan Negeri Kendari",
                LocalDate.of(2026, 1, 11),
                "T-102", "Ganja", 2.0,
                "Pasal 111", "Pasal 111",
                10, 6, 2000000,
                "Barang bukti ganja",
                "Memberatkan", "Meringankan", "Halaman 2"
        );

        Putusan p3 = new Putusan(
                "P103", "103/Pid.Sus/2026/PN",
                "Pengadilan Negeri Kendari",
                LocalDate.of(2026, 1, 12),
                "T-103", "Sabu", 3.0,
                "Pasal 114", "Pasal 114",
                15, 10, 3000000,
                "Barang bukti sabu",
                "Memberatkan", "Meringankan", "Halaman 3"
        );

        // Menambahkan tiga data ke Linked List
        data.add(p1);
        data.add(p2);
        data.add(p3);

        System.out.println("===== PENGUJIAN PENAMBAHAN TIGA DATA =====");

        System.out.println("Data pertama: " +
                data.get(0).getCaseId());

        System.out.println("Data kedua: " +
                data.get(1).getCaseId());

        System.out.println("Data ketiga: " +
                data.get(2).getCaseId());

        System.out.println("Jumlah data: " + data.size());

        System.out.println("Invariant: " +
                data.validateInvariant());

        if (data.size() == 3
                && data.get(0).getCaseId().equals("P101")
                && data.get(1).getCaseId().equals("P102")
                && data.get(2).getCaseId().equals("P103")
                && data.validateInvariant()) {

            System.out.println(
                    "STATUS: PENGUJIAN BERHASIL"
            );

        } else {
            throw new AssertionError(
                    "Pengujian penambahan tiga data gagal."
            );
        }
    }
}
