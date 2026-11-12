# ADR 0001: Penggunaan Singly Linked List sebagai Struktur Data Utama

- **Status:** Accepted
- **Tanggal:** 12 November 2026

## Konteks

Sistem Pengelolaan Putusan Pengadilan Narkotika membutuhkan struktur data untuk menyimpan dan mengelola kumpulan data putusan perkara narkotika.

Sistem membutuhkan mekanisme untuk menambahkan, mencari, mengambil, dan menghapus data putusan.

## Keputusan

Proyek menggunakan Singly Linked List sebagai struktur data utama yang diimplementasikan pada komponen `StrukturDataUtama`.

Setiap node menyimpan data putusan dan referensi menuju node berikutnya.

## Alasan Pemilihan

- Mendukung penambahan elemen secara dinamis.
- Tidak memerlukan ukuran penyimpanan tetap.
- Mendukung penelusuran data melalui hubungan antar-node.
- Sesuai dengan tujuan pembelajaran implementasi struktur data menggunakan Java.

## Konsekuensi

- Penambahan dan penghapusan elemen tidak memerlukan pergeseran elemen seperti pada array.
- Pencarian data membutuhkan penelusuran node secara berurutan.
- Akses berdasarkan indeks membutuhkan traversal dari node awal.
- Referensi antar-node harus dikelola dengan benar.

## Implementasi

Diterapkan pada komponen `StrukturDataUtama` dalam package `strukturdata`.
