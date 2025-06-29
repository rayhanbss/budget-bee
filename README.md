# BudgetBee

BudgetBee adalah aplikasi Android untuk membantu Anda mengelola keuangan pribadi, menetapkan target finansial, dan melacak progres dengan mudah dan intuitif.

## 🚀 Fitur Utama

- **Kelola Target Finansial**: Buat, edit, dan hapus target tabungan atau anggaran.
- **Progres Visual**: Tampilan progres target dalam bentuk bar atau persentase.
- **Pencarian Target**: Cari target dengan cepat menggunakan fitur pencarian.
- **Riwayat dan Selesai**: Tandai target yang telah tercapai dan lihat riwayat.
- **Notifikasi Pengingat**: Pengingat untuk target yang mendekati tenggat waktu.
- **Ringkasan & Analitik**: Laporan dan grafik sederhana untuk memonitor perkembangan keuangan.

## 🔧 Prasyarat

- Android Studio Arctic Fox (atau lebih baru)
- JDK 11 atau yang kompatibel
- Emulatord atau perangkat Android dengan minimum API level sesuai `build.gradle`

## 🌐 API Server

BudgetBee membutuhkan API server Laravel untuk penyimpanan dan pengambilan data. Silakan clone dan jalankan API server dari repository berikut:

https://github.com/syvlnl/BuBee

1. Clone repository API server:
```
git clone https://github.com/syvlnl/BuBee.git
```
2. Ikuti instruksi di README di repo BuBee untuk menjalankan server (default: http://localhost:8080).
3. Pastikan server API berjalan sebelum menjalankan aplikasi BudgetBee.

## ⚙️ Konfigurasi Aplikasi

Buka file `ApiService.kt` (atau file konfigurasi network di proyek) dan atur `BASE_URL` sesuai alamat server API:

```kotlin
const val BASE_URL = "http://10.0.2.2:8080/"
```

> Catatan: Jika menjalankan di emulator Android, gunakan `10.0.2.2` untuk mengakses `localhost` pada komputer host.

> Jika menggunakan perangkat fisik (HP), ubah `BASE_URL` menjadi `"http://<IP-perangkat-hoster>:8080/"` di `ApiService.kt`, jalankan server API pada IP tersebut, dan pastikan perangkat Android dan server API berada di jaringan yang sama.

## ▶️ Cara Menjalankan Aplikasi

1. Clone repository ini:
   ```
   git clone https://github.com/yourusername/BudgetBee.git
   ```
2. Buka proyek di Android Studio.
3. Sinkronisasi Gradle dan tunggu proses build selesai.
4. Jalankan aplikasi di emulator atau perangkat Android.

## 👩‍💻 Tim Pengembang

| [<img src="https://github.com/rayhanbss.png" width="100px;"><br><sub><b>Rayhan Bagus Sadewa</b></sub>](https://github.com/rayhanbss) |
| :---: |
| [<img src="https://github.com/safinasabil.png" width="100px;"><br><sub><b>Safina Sabil</b></sub>](https://github.com/safinasabil) |
| :---: |
| [<img src="https://github.com/syvlnl.png" width="100px;"><br><sub><b>Syavela Nisrina Liset</b></sub>](https://github.com/syvlnl) |
| :---: |
| [<img src="https://github.com/Velelen.png" width="100px;"><br><sub><b>Velen Novanti</b></sub>](https://github.com/Velelen) |
| :---: |

