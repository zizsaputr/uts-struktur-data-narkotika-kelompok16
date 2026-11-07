
package audit;

public class AuditService {

    private AuditHistory riwayat;

    // Constructor
    public AuditService() {
        riwayat = new AuditHistory();
    }

    // Mencatat aktivitas
    public void catatAktivitas(String aktivitas, String keterangan) {
        AuditLog log = new AuditLog(aktivitas, keterangan);

        riwayat.tambahRiwayat(log);

        System.out.println("Aktivitas berhasil dicatat.");
    }

    // Menampilkan seluruh riwayat
    public void tampilkanSemuaRiwayat() {
        riwayat.tampilkanRiwayat();
    }

    // Mengambil jumlah aktivitas
    public int getJumlahAktivitas() {
        return riwayat.jumlahRiwayat();
    }
}
