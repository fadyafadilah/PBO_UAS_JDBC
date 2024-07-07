import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Tambah Data Pelanggan");
            System.out.println("2. Tambah Data Barang");
            System.out.println("3. Tambah Pengiriman");
            System.out.println("4. Lihat Data Pelanggan");
            System.out.println("5. Lihat Data Barang");
            System.out.println("6. Lihat Data Pengiriman");
            System.out.println("7. Export Data Pengiriman ke TXT");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (pilihan) {
                case 1:
                    tambahDataPelanggan();
                    break;
                case 2:
                    tambahDataBarang();
                    break;
                case 3:
                    tambahPengiriman();
                    break;
                case 4:
                    lihatDataPelanggan();
                    break;
                case 5:
                    lihatDataBarang();
                    break;
                case 6:
                    lihatDataPengiriman();
                    break;
                case 7:
                    exportDataPengirimanToTXT();
                    break;
                case 8:
                    System.out.println("Keluar...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih dari menu."); 
            }
        }
    }

    private static void tambahDataPelanggan() {
        System.out.print("Nama Pelanggan: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();

        try (Connection conn = DBconnection.getConnection()) {
            String sql = "INSERT INTO pelanggan (nama, alamat) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.executeUpdate();
            System.out.println("Data pelanggan berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void tambahDataBarang() {
        System.out.print("Nama Barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        System.out.print("Stok: ");
        int stock = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        try (Connection conn = DBconnection.getConnection()) {
            String sql = "INSERT INTO barang (namaBarang, harga, stock) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, namaBarang);
            stmt.setDouble(2, harga);
            stmt.setInt(3, stock);
            stmt.executeUpdate();
            System.out.println("Data barang berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void tambahPengiriman() {
        System.out.print("ID Pelanggan: ");
        int idPelanggan = scanner.nextInt();
        System.out.print("ID Barang: ");
        int idBarang = scanner.nextInt();
        System.out.print("Jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Date tanggalPengiriman = new Date(); // Tanggal pengiriman hari ini

        try (Connection conn = DBconnection.getConnection()) {
            String sql = "INSERT INTO pengiriman (idPelanggan, idBarang, jumlah, tanggalPengiriman) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPelanggan);
            stmt.setInt(2, idBarang);
            stmt.setInt(3, jumlah);
            stmt.setDate(4, new java.sql.Date(tanggalPengiriman.getTime()));
            stmt.executeUpdate();
            System.out.println("Pengiriman berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatDataPelanggan() {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT * FROM pelanggan";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n=== Data Pelanggan ===");
            System.out.printf("%-10s %-30s %-30s\n", "ID Pelanggan", "Nama", "Alamat");
            System.out.println("----------------------------------------------");
            while (rs.next()) {
                int idPelanggan = rs.getInt("idPelanggan");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                System.out.printf("%-10d %-30s %-30s\n", idPelanggan, nama, alamat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatDataBarang() {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT * FROM barang";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n=== Data Barang ===");
            System.out.printf("%-10s %-30s %-10s %-10s\n", "ID Barang", "Nama Barang", "Harga", "Stok");
            System.out.println("----------------------------------------------");
            while (rs.next()) {
                int idBarang = rs.getInt("idBarang");
                String namaBarang = rs.getString("namaBarang");
                double harga = rs.getDouble("harga");
                int stock = rs.getInt("stock");
                System.out.printf("%-10d %-30s %-10.2f %-10d\n", idBarang, namaBarang, harga, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lihatDataPengiriman() {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT pengiriman.idPengiriman, pelanggan.nama AS namaPelanggan, barang.namaBarang, pengiriman.jumlah, pengiriman.tanggalPengiriman " +
                         "FROM pengiriman " +
                         "JOIN pelanggan ON pengiriman.idPelanggan = pelanggan.idPelanggan " +
                         "JOIN barang ON pengiriman.idBarang = barang.idBarang";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n=== Data Pengiriman ===");
            System.out.printf("%-15s %-20s %-30s %-10s %-15s\n", "ID Pengiriman", "Nama Pelanggan", "Nama Barang", "Jumlah", "Tanggal Pengiriman");
            System.out.println("---------------------------------------------------------------");
            while (rs.next()) {
                int idPengiriman = rs.getInt("idPengiriman");
                String namaPelanggan = rs.getString("namaPelanggan");
                String namaBarang = rs.getString("namaBarang");
                int jumlah = rs.getInt("jumlah");
                Date tanggalPengiriman = rs.getDate("tanggalPengiriman");
                System.out.printf("%-15d %-20s %-30s %-10d %-15s\n", idPengiriman, namaPelanggan, namaBarang, jumlah, tanggalPengiriman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void exportDataPengirimanToTXT() {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT pengiriman.idPengiriman, pelanggan.nama AS namaPelanggan, barang.namaBarang, pengiriman.jumlah, pengiriman.tanggalPengiriman " +
                         "FROM pengiriman " +
                         "JOIN pelanggan ON pengiriman.idPelanggan = pelanggan.idPelanggan " +
                         "JOIN barang ON pengiriman.idBarang = barang.idBarang";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            try (PrintWriter writer = new PrintWriter(new FileWriter("data_pengiriman.txt"))) {
                writer.println("=== Data Pengiriman ===");
                writer.printf("%-15s %-20s %-30s %-10s %-15s\n", "ID Pengiriman", "Nama Pelanggan", "Nama Barang", "Jumlah", "Tanggal Pengiriman");
                writer.println("---------------------------------------------------------------");
                while (rs.next()) {
                    int idPengiriman = rs.getInt("idPengiriman");
                    String namaPelanggan = rs.getString("namaPelanggan");
                    String namaBarang = rs.getString("namaBarang");
                    int jumlah = rs.getInt("jumlah");
                    Date tanggalPengiriman = rs.getDate("tanggalPengiriman");
                    writer.printf("%-15d %-20s %-30s %-10d %-15s\n", idPengiriman, namaPelanggan, namaBarang, jumlah, tanggalPengiriman);
                }
                System.out.println("Data pengiriman berhasil diekspor ke data_pengiriman.txt.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
