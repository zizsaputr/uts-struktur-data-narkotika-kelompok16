# LAPORAN PENGUJIAN APLIKASI
## Sistem Pengelolaan Putusan Pengadilan Narkotika

### 1. Tujuan Pengujian
Pengujian dilakukan untuk memastikan struktur data Linked List dan Queue berjalan dengan benar, proses impor CSV dapat dilakukan, validasi data berfungsi, serta integrasi dan pencatatan aktivitas berjalan sesuai kebutuhan.

### 2. Hasil Pengujian

| No. | Pengujian | Hasil |
|---|---|---|
| 1 | Kompilasi seluruh file Java | Berhasil |
| 2 | Operasi dasar Linked List | Berhasil |
| 3 | Operasi dasar Queue (FIFO) | Berhasil |
| 4 | Pengujian integrasi struktur data | Berhasil |
| 5 | Impor 50 data putusan dari CSV | Berhasil, 50 data |
| 6 | Pencarian dan penghapusan Linked List | Berhasil |
| 7 | Filter data dan pemeriksaan provenance | Berhasil |
| 8 | Pencarian Queue tanpa mengubah urutan | Berhasil |
| 9 | Penambahan tiga data putusan | Berhasil |
| 10 | Pencatatan riwayat aktivitas (Audit) | Berhasil |
| 11 | Validasi data CSV tidak valid | Berhasil, 8 data ditolak |

### 3. Hasil Pengujian Validasi CSV

Hasil pengujian menunjukkan:
- Data berhasil: 1
- Data ditolak: 8
- Data tersimpan: 1
- Invariant: true

Jenis kesalahan yang berhasil dideteksi:
1. Case ID kosong.
2. Format tanggal tidak valid.
3. Berat barang bukti bukan angka.
4. Berat barang bukti negatif.
5. Nilai tuntutan negatif.
6. Nilai vonis negatif.
7. Nilai denda negatif.
8. Jenis narkotika kosong.

### 4. Hasil Pengujian Impor Dataset

Berdasarkan pengujian file `data/putusan-50.csv`:
- Data berhasil diimpor: 50
- Data gagal: 0
- Total data: 50
- Invariant: true

### 5. Kesimpulan

Berdasarkan pengujian yang telah dilakukan, fungsi-fungsi struktur data dan proses impor serta validasi CSV yang diuji berjalan sesuai hasil yang diharapkan.

Pengujian menunjukkan bahwa data dapat ditambahkan, dicari, dihapus, diproses menggunakan Queue, dan diperiksa validitas strukturnya.

**Status pengujian: BERHASIL berdasarkan pengujian yang telah dilaksanakan.**