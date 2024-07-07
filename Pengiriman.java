import java.sql.Date;

public class Pengiriman {
    private int idPengiriman;
    private int idPelanggan;
    private int idBarang;
    private int jumlah;
    private Date tanggalPengiriman;

    public Pengiriman(int idPengiriman, int idPelanggan, int idBarang, int jumlah, Date tanggalPengiriman) {
        this.idPengiriman = idPengiriman;
        this.idPelanggan = idPelanggan;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.tanggalPengiriman = tanggalPengiriman;
    }

    // Getter and setter methods
    public int getIdPengiriman() {
        return idPengiriman;
    }

    public void setIdPengiriman(int idPengiriman) {
        this.idPengiriman = idPengiriman;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTanggalPengiriman() {
        return tanggalPengiriman;
    }

    public void setTanggalPengiriman(Date tanggalPengiriman) {
        this.tanggalPengiriman = tanggalPengiriman;
    }

    @Override
    public String toString() {
        return "Pengiriman{" +
                "idPengiriman=" + idPengiriman +
                ", idPelanggan=" + idPelanggan +
                ", idBarang=" + idBarang +
                ", jumlah=" + jumlah +
                ", tanggalPengiriman=" + tanggalPengiriman +
                '}';
    }
}
