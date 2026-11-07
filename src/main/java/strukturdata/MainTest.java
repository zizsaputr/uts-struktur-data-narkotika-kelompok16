package strukturdata;

public class MainTest {

    public static void main(String[] args) {

        StrukturDataUtama<String> data = new StrukturDataUtama<>();

        // Menguji operasi add
        data.add("Narkotika");
        data.add("Psikotropika");
        data.add("Obat Terlarang");

        System.out.println("=== PENGUJIAN STRUKTUR DATA ===");

        // Menguji size dan isEmpty
        System.out.println("Jumlah data: " + data.size());
        System.out.println("Apakah kosong: " + data.isEmpty());

        // Menguji get
        System.out.println("Data indeks 0: " + data.get(0));

        // Menguji pencarian
        System.out.println("Indeks Psikotropika: " + data.indexOf("Psikotropika"));

        // Menguji remove
        System.out.println("Data yang dihapus: " + data.remove(1));
        System.out.println("Jumlah setelah dihapus: " + data.size());

        // Menguji validateInvariant
        System.out.println("Invariant valid: " + data.validateInvariant());

        // Menguji clear
        data.clear();
        System.out.println("Jumlah setelah clear: " + data.size());
        System.out.println("Apakah kosong setelah clear: " + data.isEmpty());
        System.out.println("Invariant setelah clear: " + data.validateInvariant());
    }
}