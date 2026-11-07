
# Sistem Pengelolaan Putusan Pengadilan Narkotika

## Deskripsi Proyek

Sistem Pengelolaan Putusan Pengadilan Narkotika merupakan aplikasi berbasis Java yang digunakan untuk mengelola data putusan perkara narkotika.

Proyek ini dibuat sebagai tugas Ujian Tengah Semester (UTS) mata kuliah Algoritma dan Pemrograman dengan menerapkan konsep struktur data.

## Teknologi yang Digunakan

- Bahasa pemrograman: Java
- Java Development Kit (JDK): 17
- IDE: IntelliJ IDEA
- Version Control: Git dan GitHub

## Struktur Data dan Komponen

Komponen yang terdapat dalam proyek:

1. **StrukturDataUtama** - Mengelola kumpulan data putusan menggunakan struktur Linked List.
2. **QueueUtama** - Mengelola antrean data putusan dengan prinsip FIFO (First In, First Out).
3. **Putusan** - Model yang menyimpan atribut data putusan.
4. **CSVImporter** - Membaca dan mengimpor data putusan dari file CSV serta melakukan validasi data.
5. **Audit** - Menyediakan pencatatan dan penampilan riwayat aktivitas.
6. **PutusanManager** - Menyediakan menu utama untuk menambah, menampilkan, dan menghitung data putusan.

## Struktur Folder

```text
uts-struktur-data-narkotika-kelompok16/
|
+-- src/
|   +-- main/
|       +-- java/
|           +-- audit/
|           +-- model/
|           +-- strukturdata/
|
+-- data/
|   +-- putusan-50.csv
|
+-- docs/
|   +-- data-provenance.csv
|   +-- TESTING_REPORT.md
|
+-- AI_USAGE.md
+-- CONTRIBUTIONS.md
+-- README.md
+-- pom.xml
```

## Cara Menjalankan Program

### 1. Kompilasi Seluruh File Java

Jalankan perintah berikut melalui PowerShell dari folder utama proyek:

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\javac.exe" -d out (Get-ChildItem src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

### 2. Menjalankan Aplikasi

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\java.exe" -cp out strukturdata.PutusanManager
```

### 3. Menjalankan Pengujian

Contoh menjalankan pengujian integrasi:

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\java.exe" -cp out strukturdata.IntegrasiTest
```

Contoh menjalankan pengujian impor CSV:

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\java.exe" -cp out strukturdata.CSVImportTest
```

Contoh menjalankan pengujian validasi CSV:

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\java.exe" -cp out strukturdata.CSVValidationTest
```

Pengujian lainnya tersedia pada package `strukturdata` dan `audit`.

## Dokumentasi Pengujian

Laporan hasil pengujian aplikasi dapat dilihat pada:

`docs/TESTING_REPORT.md`

Laporan tersebut berisi tujuan pengujian, daftar pengujian, hasil validasi CSV, hasil impor dataset, dan kesimpulan pengujian.

## Kontributor

- Faizul Rahmat
- Abdul Aziz Saputra
