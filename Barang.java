
public class Barang {
    private int idBarang;
    private String namaBarang;
    private double harga;
    private int stock;

    public Barang(int idBarang, String namaBarang, double harga, int stock) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stock = stock;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "idBarang=" + idBarang +
                ", namaBarang='" + namaBarang + '\'' +
                ", harga=" + harga +
                ", stock=" + stock +
                '}';
    }
}
