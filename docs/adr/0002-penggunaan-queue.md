# ADR 0002: Penggunaan Queue untuk Pengelolaan Antrean Data

- **Status:** Accepted
- **Tanggal:** 12 November 2026

## Konteks

Sistem Pengelolaan Putusan Pengadilan Narkotika membutuhkan mekanisme untuk mengelola antrean data putusan berdasarkan urutan masuknya data.

Pengelolaan antrean diperlukan agar data dapat diproses sesuai urutan kedatangannya.

## Keputusan

Proyek menggunakan struktur data Queue dengan prinsip FIFO (First In, First Out).

Implementasi Queue diterapkan pada komponen `QueueUtama` dalam package `strukturdata`.

## Alasan Pemilihan

- Data yang pertama masuk menjadi data pertama yang diproses.
- Sesuai untuk kebutuhan pengelolaan antrean data.
- Memisahkan pengelolaan antrean dari struktur data utama.
- Mendukung penerapan konsep Queue dalam pemrograman Java.

## Konsekuensi

- Urutan pemrosesan data mengikuti urutan kedatangan.
- Penambahan data dilakukan melalui operasi enqueue.
- Pengambilan data dilakukan melalui operasi dequeue.
- Urutan FIFO harus tetap terjaga selama proses pengelolaan antrean.

## Implementasi

Diterapkan pada komponen `QueueUtama` dalam package `strukturdata`.
