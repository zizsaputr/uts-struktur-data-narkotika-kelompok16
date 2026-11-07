
package audit;

public class AuditTest {

    public static void main(String[] args) {

        // Membuat objek AuditService
        AuditService audit = new AuditService();

        // Mencatat beberapa aktivitas
        audit.catatAktivitas(
                "TAMBAH",
                "Menambahkan putusan P001"
        );

        audit.catatAktivitas(
                "CARI",
                "Mencari putusan P001"
        );

        audit.catatAktivitas(
                "HAPUS",
                "Menghapus putusan P002"
        );

        // Menampilkan seluruh riwayat
        System.out.println();

        audit.tampilkanSemuaRiwayat();

        // Menampilkan jumlah aktivitas
        System.out.println(
                "Total aktivitas: " +
                        audit.getJumlahAktivitas()
        );
    }
}
