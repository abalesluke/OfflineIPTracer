import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main{
    public static <x> void print(x text){
        System.out.println(text);
    }

    public static int intz(String text){
        return Integer.parseInt(text);
    }

    public static void ipv4_trace(int ip_decimal){
        String line;
        String csvFile = "../ip_database/IP2LOCATION-LITE-DB11.CSV";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                print(data[3]);
                break;
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static int get_ipv4_decimal(String ipv4_addr){
        String[] octet = ipv4_addr.split("\\.");
        int oct1 = intz(octet[0]);
        int oct2 = intz(octet[1]);
        int oct3 = intz(octet[2]);
        int oct4 = intz(octet[3]);
        return (oct1 * 16777216) + (oct2 * 65536) + (oct3 * 256) + (oct4);
    }

	public static void main(String[] args) {
        int ipv4_decimal = get_ipv4_decimal("45.196.151.97");
        print(ipv4_decimal);
	}
}

