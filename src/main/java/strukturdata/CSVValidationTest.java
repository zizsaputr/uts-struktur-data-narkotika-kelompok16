
package strukturdata;

import model.Putusan;

public class CSVValidationTest {

    public static void main(String[] args) throws Exception {

        String csv =
                "caseId,nomorPerkara,pengadilan,tanggalPutusan,terdakwaKode,jenisNarkotika,beratBarangBuktiGram,pasalDakwaan,pasalTerbukti,tuntutanBulan,vonisBulan,dendaRupiah,barangBukti,keadaanMemberatkan,keadaanMeringankan,sourcePages\n"
                        + "P001,001/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T001,Sabu,10,Pasal 112,Pasal 112,120,100,100000000,Sabu,Memberatkan,Meringankan,1\n"
                        + ",002/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T002,Sabu,10,Pasal 112,Pasal 112,120,100,100000000,Sabu,M,M,2\n"
                        + "P003,003/Pid.Sus/2026/PN,PN Kendari,2026-99-99,T003,Sabu,10,Pasal 112,Pasal 112,120,100,100000000,Sabu,M,M,3\n"
                        + "P004,004/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T004,Sabu,ABC,Pasal 112,Pasal 112,120,100,100000000,Sabu,M,M,4\n"
                        + "P005,005/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T005,Sabu,-10,Pasal 112,Pasal 112,120,100,100000000,Sabu,M,M,5\n"
                        + "P006,006/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T006,Sabu,10,Pasal 112,Pasal 112,-120,100,100000000,Sabu,M,M,6\n"
                        + "P007,007/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T007,Sabu,10,Pasal 112,Pasal 112,120,-100,100000000,Sabu,M,M,7\n"
                        + "P008,008/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T008,Sabu,10,Pasal 112,Pasal 112,120,100,-100000000,Sabu,M,M,8\n"
                        + "P009,009/Pid.Sus/2026/PN,PN Kendari,2026-01-10,T009,,10,Pasal 112,Pasal 112,120,100,100000000,Sabu,M,M,9\n";

        java.nio.file.Path file = java.nio.file.Files.createTempFile(
                "csv-validation-", ".csv");

        java.nio.file.Files.writeString(file, csv);

        StrukturDataUtama<Putusan> data = new StrukturDataUtama<>();
        CSVImporter importer = new CSVImporter();

        CSVImporter.ImportResult hasil =
                importer.importCSV(file.toString(), data);

        System.out.println("===== PENGUJIAN VALIDASI CSV =====");
        System.out.println("Data berhasil : " + hasil.getBerhasil());
        System.out.println("Data ditolak  : " + hasil.getGagal());
        System.out.println("Data tersimpan: " + data.size());
        System.out.println("Invariant     : " + data.validateInvariant());

        System.out.println("\n===== DETAIL PENOLAKAN =====");
        System.out.println(hasil.getDetailError());

        if (hasil.getBerhasil() == 1
                && hasil.getGagal() == 8
                && data.size() == 1
                && data.validateInvariant()) {

            System.out.println("\nSTATUS: PENGUJIAN BERHASIL");

        } else {
            throw new AssertionError("Pengujian validasi CSV gagal.");
        }

        java.nio.file.Files.deleteIfExists(file);
    }
}
