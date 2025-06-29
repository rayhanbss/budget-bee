# BudgetBee

<p align="center">
  <img src="./Logosvg.svg" alt="BudgetBee Logo" width="150" />
</p>

BudgetBee merupakan aplikasi berbasis Android untuk membantu anda untuk mengelola keuangan pribadi dengan memberikan fitur personalisasi yaitu dashboard berisikan rekapan semua aktivitas keuangan dan tracking target.

## 🚀 Fitur Utama

- Rekapan aktivitas keuangan pengguna
- Personalisasi kategori income dan expense
- Notulensi transaksi aktivitas keuangan
- Menentukan target tabungan pengguna

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
   git clone https://github.com/rayhanbss/budget-bee
   ```
2. Buka proyek di Android Studio.
3. Sinkronisasi Gradle dan tunggu proses build selesai.
4. Jalankan aplikasi di emulator atau perangkat Android.

## 👩‍💻 Tim Pengembang

| [<img src="https://github.com/rayhanbss.png" width="100px"><br><sub><b>Rayhan Bagus Sadewa</b></sub>](https://github.com/rayhanbss) | [<img src="https://github.com/safinasabil.png" width="100px"><br><sub><b>Safina Sabil</b></sub>](https://github.com/safinasabil) | [<img src="https://github.com/syvlnl.png" width="100px"><br><sub><b>Syavela N. Liset</b></sub>](https://github.com/syvlnl) | [<img src="https://github.com/Velelen.png" width="100px"><br><sub><b>Velen Novanti</b></sub>](https://github.com/Velelen) |
| :---: | :---: | :---: | :---: |

