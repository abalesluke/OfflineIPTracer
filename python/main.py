import csv

def ipv4_trace(ip_decimal: int):
    ip_db_path = "../ip_database/IP2LOCATION-LITE-DB11.CSV"
    with open(ip_db_path, 'r', newline='') as csv_data:
        notFound = True
        csv_reader = csv.reader(csv_data)
        for start,end, code, country, region, city, lat, lon, zip, tz in csv_reader:
            # print(start, end, ip_decimal)
            if(ip_decimal >= int(start) and ip_decimal <= int(end)):
                notFound = False
                print(f"{'[IP-INFO]':=^30}")
                print("Country Code:",code)
                print("Country:", country)
                print("Region:",region)
                print("City:",city)
                print("ZIP Code:",zip)
                print("Time Zone:",tz)
                print("Latitude:",lat)
                print("Longitude:",lon)
                print(f"{'[IP-INFO]':=^30}")
                break

        if(notFound):
            print("[Sorry](🥺)-IP range was not on the database!")

def get_ipv4_decimal(ipv4_addr):
    octet = ipv4_addr.split(".")
    return (int(octet[0]) * 16777216) + (int(octet[1]) * 65536) + (int(octet[2]) * 256) + int(octet[3])

def banner():
    print("""
The IP Database/Informations used in this project was from: IP2Location.com
Please comply with their Terms of use and license.
    """)

if(__name__=="__main__"):
    banner()
    ipv4_addr = input("Enter IPv4 address: ").strip()
    ipv4_decimal = get_ipv4_decimal(ipv4_addr)
    ip_info = ipv4_trace(ipv4_decimal)

