
package audit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLog {

    private LocalDateTime waktu;
    private String aktivitas;
    private String keterangan;

    // Constructor
    public AuditLog(String aktivitas, String keterangan) {
        this.waktu = LocalDateTime.now();
        this.aktivitas = aktivitas;
        this.keterangan = keterangan;
    }

    // Getter waktu
    public LocalDateTime getWaktu() {
        return waktu;
    }

    // Getter aktivitas
    public String getAktivitas() {
        return aktivitas;
    }

    // Getter keterangan
    public String getKeterangan() {
        return keterangan;
    }

    // Menampilkan riwayat aktivitas
    @Override
    public String toString() {
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return "[" + waktu.format(format) + "] "
                + aktivitas + " - " + keterangan;
    }
}
