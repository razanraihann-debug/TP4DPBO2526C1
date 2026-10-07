import java.time.Year;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Menu utama aplikasi "Data Kru Stasiun Antariksa" (CRUD).
 */
public class PeopleMenu extends JFrame {

    // Field
    private JPanel mainPanel;
    private JLabel lblTitle, lblId, lblNama, lblTahunLahir, lblKategori, lblEnergi, lblEnergiValue;
    private JTextField txtId, txtNama, txtTahunLahir;
    private JComboBox<String> cmbKategori;
    private JSlider sldEnergi;
    private JButton btnAdd, btnUpdate, btnDelete, btnCancel;
    private JScrollPane scrollPane;
    private JTable tblPeople;
    private static final String PLACEHOLDER = "Pilih jabatan...";


    private final ArrayList<Person> listPerson = new ArrayList<>();
    private DefaultTableModel model;
    private int selectedIndex = -1; // -1 = mode tambah, >= 0 = mode edit

    public PeopleMenu() {
        setContentPane(mainPanel);
        setTitle("Stasiun Antariksa Nusantara-1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(760, 620);
        setLocationRelativeTo(null);
        initListeners();
        initTable();
        populateList();
        refreshTable();
        setEditMode(false);
    }

    //setup

    private void initListeners() {
        btnAdd.addActionListener(e -> addPerson());
        btnUpdate.addActionListener(e -> updatePerson());
        btnDelete.addActionListener(e -> deletePerson());
        btnCancel.addActionListener(e -> clearForm());
        sldEnergi.addChangeListener(e -> lblEnergiValue.setText(sldEnergi.getValue() + "%"));
    }

    private void initTable() {
        model = new DefaultTableModel(
                new String[]{"ID", "Nama", "Tahun Lahir", "Kategori", "Level Energi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPeople.setModel(model);
        tblPeople.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPeople.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int row = tblPeople.getSelectedRow();
            if (row >= 0) loadToForm(row);
        });
    }

    private void populateList() {
        listPerson.add(new Person("KRU001", "Laksmana Aditya", 1985, "Komandan Misi", 92));
        listPerson.add(new Person("KRU002", "Nayla Prameswari", 1991, "Pilot Roket", 85));
        listPerson.add(new Person("KRU003", "Bima Saputra", 1988, "Insinyur Reaktor", 70));
        listPerson.add(new Person("KRU004", "Dr. Kirana Larasati", 1990, "Astrobiolog", 64));
        listPerson.add(new Person("KRU005", "Rangga Wiratama", 1994, "Dokter Antariksa", 78));
        listPerson.add(new Person("KRU006", "Sekar Ayuningtyas", 1996, "Teknisi Hidroponik", 55));
        listPerson.add(new Person("KRU007", "Galih Nusantara", 1993, "Pilot Roket", 88));
        listPerson.add(new Person("KRU008", "Tara Wulandari", 1998, "Teknisi Hidroponik", 40));
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Person p : listPerson) {
            model.addRow(new Object[]{
                p.getId(), p.getNama(), p.getTahunLahir(), p.getKategori(), p.getLevelEnergi() + "%"
            });
        }
    }

    //form helpers
    private void setEditMode(boolean editing) {
        txtId.setEnabled(!editing);   // ID tidak boleh diubah saat edit
        btnAdd.setEnabled(!editing);
        btnUpdate.setEnabled(editing);
        btnDelete.setEnabled(editing);
    }

    private void loadToForm(int row) {
        Person p = listPerson.get(row);
        selectedIndex = row;
        txtId.setText(p.getId());
        txtNama.setText(p.getNama());
        txtTahunLahir.setText(String.valueOf(p.getTahunLahir()));
        cmbKategori.setSelectedItem(p.getKategori());
        sldEnergi.setValue(p.getLevelEnergi());
        setEditMode(true);
    }

    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtTahunLahir.setText("");
        cmbKategori.setSelectedIndex(0);
        sldEnergi.setValue(50);
        selectedIndex = -1;
        tblPeople.clearSelection();
        setEditMode(false);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
    }

    //Validasi form. Mengembalikan Person baru jika valid, null jika tidak.
    private Person readForm(boolean checkDuplicateId) {
        String id = txtId.getText().trim();
        String nama = txtNama.getText().trim();
        String tahunStr = txtTahunLahir.getText().trim();
        String kategori = String.valueOf(cmbKategori.getSelectedItem());

        if (id.isEmpty() || nama.isEmpty() || tahunStr.isEmpty()) {
            showError("ID, Nama, dan Tahun Lahir wajib diisi!");
            return null;
        }
        if (kategori.equals(PLACEHOLDER)) {
            showError("Silakan pilih Kategori / jabatan kru.");
            return null;
        }
        int tahun;
        try {
            tahun = Integer.parseInt(tahunStr);
        } catch (NumberFormatException ex) {
            showError("Tahun Lahir harus berupa angka.");
            return null;
        }
        int now = Year.now().getValue();
        if (tahun < 1900 || tahun > now) {
            showError("Tahun Lahir harus antara 1900 dan " + now + ".");
            return null;
        }
        if (checkDuplicateId) {
            for (Person p : listPerson) {
                if (p.getId().equalsIgnoreCase(id)) {
                    showError("ID \"" + id + "\" sudah dipakai kru lain.");
                    return null;
                }
            }
        }
        return new Person(id, nama, tahun, kategori, sldEnergi.getValue());
    }

    //aksi CRUD

    // CREATE
    private void addPerson() {
        Person p = readForm(true);
        if (p == null) return;
        listPerson.add(p);
        refreshTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Kru baru berhasil ditambahkan ke stasiun!",
                "Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }

    // UPDATE
    private void updatePerson() {
        if (selectedIndex < 0) return;
        Person p = readForm(false);
        if (p == null) return;
        listPerson.set(selectedIndex, p);
        refreshTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Data kru berhasil diperbarui.",
                "Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }

    // DELETE
    private void deletePerson() {
        if (selectedIndex < 0) return;
        Person p = listPerson.get(selectedIndex);
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Yakin ingin mengeluarkan \"" + p.getNama() + "\" (" + p.getId() + ") dari stasiun?\n"
                + "Data yang dihapus tidak dapat dikembalikan.",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (pilihan == JOptionPane.YES_OPTION) {
            listPerson.remove(selectedIndex);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Data kru berhasil dihapus.",
                    "Berhasil", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PeopleMenu().setVisible(true));
    }
}
