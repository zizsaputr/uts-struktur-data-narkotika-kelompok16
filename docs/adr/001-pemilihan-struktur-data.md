# ADR 001: Pemilihan Struktur Data

## Status
Diterima (Accepted)

## Konteks

Aplikasi Sistem Pengelolaan Putusan Pengadilan Narkotika membutuhkan struktur data yang dapat digunakan untuk menyimpan, mengelola, mencari, menambahkan, dan menghapus data putusan secara dinamis.

Selain itu, aplikasi membutuhkan mekanisme antrean untuk mengelola proses data berdasarkan urutan masuk.

## Keputusan

Proyek ini menggunakan:

1. **Singly Linked List** sebagai struktur data utama untuk menyimpan dan mengelola objek Putusan.
2. **Queue** sebagai struktur data sekunder untuk mengelola antrean pemrosesan putusan.

Singly Linked List dipilih karena mendukung penambahan dan penghapusan elemen serta akses data berdasarkan indeks melalui implementasi node yang saling terhubung.

Queue dipilih karena menerapkan prinsip FIFO (First In, First Out), sehingga data yang pertama masuk akan menjadi data pertama yang diproses.

Kedua struktur data diimplementasikan secara mandiri menggunakan konsep Generic Type (T).

## Konsekuensi

- Singly Linked List membutuhkan pengelolaan node dan pointer secara manual.
- Akses elemen Linked List berdasarkan indeks membutuhkan penelusuran dari awal.
- Queue harus menjaga urutan FIFO dalam setiap operasi enqueue dan dequeue.
- Kedua struktur data memerlukan validasi invariant untuk memastikan konsistensi strukturnya.
- Implementasi Generic Type memungkinkan struktur data digunakan untuk berbagai tipe objek.

## Pengujian

- Operasi Singly Linked List diuji melalui MainTest.java.
- Operasi Queue diuji melalui QueueTest.java.
- Validasi invariant dilakukan untuk memastikan struktur data tetap konsisten.
