public class Pelanggan {
    private int idPelanggan;
    private String nama;
    private String alamat;

    public Pelanggan(int idPelanggan, String nama, String alamat) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.alamat = alamat;
    }

    // Getter and setter methods
    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "Pelanggan{" +
                "idPelanggan=" + idPelanggan +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                '}';
    }
}
