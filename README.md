# Janji
Saya Razan Raihan Malik dengan NIM 2508838 mengerjakan Tugas Praktikum 4 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

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

## Dokumentasi
