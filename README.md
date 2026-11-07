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

1. **StrukturDataUtama** — mengelola kumpulan data putusan menggunakan struktur Linked List.
2. **QueueUtama** — mengelola antrean data putusan dengan prinsip FIFO (First In, First Out).
3. **Putusan** — model yang menyimpan atribut data putusan.
4. **CSVImporter** — membaca dan mengimpor data putusan dari file CSV.
5. **Audit** — mencatat riwayat aktivitas pengelolaan data.
6. **PutusanManager** — menyediakan menu untuk menambah, menampilkan, dan menghitung data putusan.

## Struktur Folder

```text
src/
└── main/
    └── java/
        ├── audit/
        ├── model/
        └── strukturdata/

data/
└── putusan-50.csv

docs/
└── data-provenance.csv

AI_USAGE.md
CONTRIBUTIONS.md
README.md
```

## Cara Menjalankan Program

### 1. Kompilasi seluruh file Java

Jalankan perintah berikut melalui PowerShell dari folder utama proyek:

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\javac.exe" -d out (Get-ChildItem src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

### 2. Menjalankan aplikasi

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\java.exe" -cp out strukturdata.PutusanManager
```

### 3. Menjalankan pengujian

Contoh menjalankan pengujian integrasi:

```powershell
& "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.9-hotspot\bin\java.exe" -cp out strukturdata.IntegrasiTest
```

Pengujian lainnya tersedia pada package `audit` dan `