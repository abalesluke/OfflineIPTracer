#include <iostream>
#include <fstream>
#include <sstream>

struct IPInfo {
    std::string start;
    std::string end;
    std::string code;
    std::string country;
    std::string region;
    std::string city;
    std::string lat;
    std::string lon;
    std::string zip;
    std::string tz;
};

int getIpv4Decimal(const std::string& ipv4Addr) {
    std::istringstream iss(ipv4Addr);
    std::string octet;
    int decimal = 0;
    for (int i = 0; i < 4; ++i) {
        std::getline(iss, octet, '.');
        decimal = (decimal << 8) + std::stoi(octet);
    }
    return decimal;
}

void ipv4Trace(int ipDecimal) {
    std::string ipDbPath = "../ip_database/IP2LOCATION-LITE-DB11.CSV";
    std::ifstream csvData(ipDbPath);
    if (!csvData) {
        std::cout << "Error: Unable to open the IP database file." << std::endl;
        return;
    }

    std::string line;
    bool notFound = true;
    while (std::getline(csvData, line)) {
        std::istringstream iss(line);
        IPInfo ipInfo;
        std::getline(iss, ipInfo.start, ',');
        std::getline(iss, ipInfo.end, ',');
        std::getline(iss, ipInfo.code, ',');
        std::getline(iss, ipInfo.country, ',');
        std::getline(iss, ipInfo.region, ',');
        std::getline(iss, ipInfo.city, ',');
        std::getline(iss, ipInfo.lat, ',');
        std::getline(iss, ipInfo.lon, ',');
        std::getline(iss, ipInfo.zip, ',');
        std::getline(iss, ipInfo.tz, ',');

        int start = std::stoi(ipInfo.start);
        int end = std::stoi(ipInfo.end);
        if (ipDecimal >= start && ipDecimal <= end) {
            notFound = false;
            std::cout << std::string(30, '=') << "[IP-INFO]" << std::string(30, '=') << std::endl;
            std::cout << "Country Code: " << ipInfo.code << std::endl;
            std::cout << "Country: " << ipInfo.country << std::endl;
            std::cout << "Region: " << ipInfo.region << std::endl;
            std::cout << "City: " << ipInfo.city << std::endl;
            std::cout << "ZIP Code: " << ipInfo.zip << std::endl;
            std::cout << "Time Zone: " << ipInfo.tz << std::endl;
            std::cout << "Latitude: " << ipInfo.lat << std::endl;
            std::cout << "Longitude: " << ipInfo.lon << std::endl;
            std::cout << std::string(30, '=') << "[IP-INFO]" << std::string(30, '=') << std::endl;
            break;
        }
    }

    if (notFound) {
        std::cout << "[Sorry](🥺)-IP range was not on the database!" << std::endl;
    }
}

void banner() {
    std::cout << R"(
The IP Database/Informations used in this project was from: IP2Location.com
Please comply with their Terms of use and license.
)" << std::endl;
}

int main() {
    banner();
    std::string ipv4Addr;
    std::cout << "Enter IPv4 address: ";
    std::cin >> ipv4Addr;
    int ipv4Decimal = getIpv4Decimal(ipv4Addr);
    ipv4Trace(ipv4Decimal);

    return 0;
}

