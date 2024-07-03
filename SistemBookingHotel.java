import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Kamar {
    private int nomorKamar;
    private boolean sudahDipesan;

    public Kamar(int nomorKamar) {
        this.nomorKamar = nomorKamar;
        this.sudahDipesan = false;
    }

    public int getNomorKamar() {
        return nomorKamar;
    }

    public boolean isSudahDipesan() {
        return sudahDipesan;
    }

    public void pesan() {
        if (!sudahDipesan) {
            sudahDipesan = true;
            System.out.println("Kamar " + nomorKamar + " telah dipesan.");
        } else {
            System.out.println("Kamar " + nomorKamar + " sudah dipesan.");
        }
    }
}

class Hotel {
    private ArrayList<Kamar> daftarKamar;

    public Hotel(int jumlahKamar) {
        daftarKamar = new ArrayList<>();
        for (int i = 1; i <= jumlahKamar; i++) {
            daftarKamar.add(new Kamar(i));
        }
    }

    public void tampilkanKamarTersedia() {
        System.out.println("Kamar yang tersedia:");
        for (Kamar kamar : daftarKamar) {
            if (!kamar.isSudahDipesan()) {
                System.out.println("Kamar " + kamar.getNomorKamar());
            }
        }
    }

    public void pesanKamar(int nomorKamar) {
        for (Kamar kamar : daftarKamar) {
            if (kamar.getNomorKamar() == nomorKamar) {
                kamar.pesan();
                return;
            }
        }
        System.out.println("Kamar " + nomorKamar + " tidak ada.");
    }
}

class Pengguna {
    private HashMap<String, String> dataPengguna;

    public Pengguna() {
        dataPengguna = new HashMap<>();
      
        dataPengguna.put("GhostFog", "Nekopoi12");
        dataPengguna.put("user2", "password2");
    }

    public boolean login(String username, String password) {
        if (dataPengguna.containsKey(username) && dataPengguna.get(username).equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}

public class SistemBookingHotel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Misal hotel memiliki 10 kamar
        Pengguna pengguna = new Pengguna();
        
        System.out.print("Masukkan username: ");
        String username = scanner.next();
        System.out.print("Masukkan password: ");
        String password = scanner.next();

        if (!pengguna.login(username, password)) {
            System.out.println("Login gagal. Username atau password salah.");
            scanner.close();
            return;
        }

        System.out.println("Login berhasil. Selamat datang " + username + "!");
        
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tampilkan kamar yang tersedia");
            System.out.println("2. Pesan kamar");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    hotel.tampilkanKamarTersedia();
                    break;
                case 2:
                    System.out.print("Masukkan nomor kamar yang akan dipesan: ");
                    int nomorKamar = scanner.nextInt();
                    hotel.pesanKamar(nomorKamar);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan Sistem Booking Hotel.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }
}
