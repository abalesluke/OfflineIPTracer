import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static <x> void print(x text){
        System.out.println(text);
    }

    public static int intz(String text){
        return Integer.parseInt(text);
    }
    public static void banner(){
        print("\nThe IP Database/Informations used in this project was from: IP2Location.com");
        print("Please comply with their Terms of use and license.");
    }

    public static void ipv4_trace(int ip_decimal){
        Boolean notFound = true;
        String line;
        String ip_db_path = "../ip_database/IP2LOCATION-LITE-DB11.CSV";
        try (BufferedReader br = new BufferedReader(new FileReader(ip_db_path))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(ip_decimal >= intz(data[0]) && ip_decimal <= intz(data[1])){
                    notFound = false;
                    print("===============[IP-INFO]===============");
                    print("Country Code: "+data[2]);
                    print("Country: "+data[3]);
                    print("Region: "+data[4]);
                    print("City: "+data[5]);
                    print("ZIP Code: "+data[6]);
                    print("Time Zone: "+data[7]);
                    print("Latitude: "+data[8]);
                    print("Longitude: "+data[9]);
                    print("===============[IP-INFO]===============");
                    break;
                }
            }
            if(notFound){
                print("[Sorry](🥺)-IP range was not on the database!");
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
        banner();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IPv4 Address: ");
        String user_input = sc.nextLine();
        int ipv4_decimal = get_ipv4_decimal(user_input);
        ipv4_trace(ipv4_decimal);
	}
}

