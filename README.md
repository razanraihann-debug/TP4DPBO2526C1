# Data Kru Stasiun Antariksa (PeopleData)

Tugas Praktikum 4 – DPBO. Aplikasi desktop Java Swing (CRUD) untuk mengelola data kru **Stasiun Antariksa Nusantara-1**.

## Desain / Fungsi Program

Tema **People** divisualisasikan sebagai kru stasiun luar angkasa. Setiap `Person` memiliki atribut:

| Atribut | Komponen Form | Keterangan |
|---|---|---|
| ID | `JTextField` | Kode unik kru (mis. `KRU001`), tidak bisa diubah saat edit |
| Nama | `JTextField` | Nama lengkap kru |
| Tahun Lahir | `JTextField` | Angka 1900 – tahun sekarang |
| Kategori | `JComboBox` | Jabatan: Komandan Misi, Pilot Roket, Insinyur Reaktor, Astrobiolog, Dokter Antariksa, Teknisi Hidroponik |
| **Level Energi** *(atribut baru)* | **`JSlider`** | Nilai 0–100%, nilainya tampil real-time di samping slider (**bonus +20**, bukan `JTextField`) |

Struktur kode:

- `Person.java` – class model (atribut, constructor, getter/setter).
- `PeopleMenu.java` – form utama (GUI + logika CRUD). Data disimpan di `ArrayList<Person>` dan diisi awal oleh `populateList()`.
- `PeopleMenu.form` – desain form (IntelliJ IDEA GUI Designer), ter-bind ke class `PeopleMenu`.

## Alur Program

1. Program dijalankan → `populateList()` mengisi 8 data awal → ditampilkan di `JTable`.
2. **Create** – isi form, klik **Add**. Divalidasi (field kosong, tahun harus angka & rentang valid, kategori wajib dipilih, ID tidak boleh duplikat).
3. **Read** – seluruh data tampil di tabel. Klik satu baris untuk memuat datanya ke form (mode edit: tombol Add nonaktif, Update & Delete aktif, ID dikunci).
4. **Update** – ubah isi form, klik **Update**.
5. **Delete** – klik **Delete** → muncul **dialog konfirmasi** (Yes/No). Data baru dihapus jika memilih *Yes*.
6. **Cancel** – mengosongkan form & membatalkan mode edit.

## Cara Menjalankan

**IntelliJ IDEA:** buka folder `PeopleData` sebagai project (taruh `.java` dan `.form` di source root), lalu jalankan `main()` pada `PeopleMenu`.
Pastikan di *Settings → Editor → GUI Designer* opsi **Generate GUI into: Binary class files** (default) aktif.

## Dokumentasi

> Ganti gambar di bawah dengan screenshot/GIF hasil run programmu (simpan di folder `Dokumentasi/`).

| Operasi | Tampilan |
|---|---|
| Tampilan awal (Read) | ![Awal](Dokumentasi/01-awal.png) |
| Create (Add) | ![Create](Dokumentasi/02-create.png) |
| Update | ![Update](Dokumentasi/03-update.png) |
| Konfirmasi Delete | ![Konfirmasi](Dokumentasi/04-konfirmasi-delete.png) |
| Setelah Delete | ![Delete](Dokumentasi/05-delete.png) |
