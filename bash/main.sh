#!/bin/bash

ipv4_trace() {
    ip_decimal=$1
    ip_db_path="../ip_database/IP2LOCATION-LITE-DB11.CSV"
    while IFS=, read -r start end code country region city lat lon zip tz; do
        if ((ip_decimal >= start && ip_decimal <= end)); then
            notFound=false
            echo "===============[IP-INFO]==============="
            echo "Country Code: $code"
            echo "Country: $country"
            echo "Region: $region"
            echo "City: $city"
            echo "ZIP Code: $zip"
            echo "Time Zone: $tz"
            echo "Latitude: $lat"
            echo "Longitude: $lon"
            echo "===============[IP-INFO]==============="
            break
        fi
    done < "$ip_db_path"

    if $notFound; then
        echo "[Sorry](🥺)-IP range was not on the database!"
    fi
}

get_ipv4_decimal() {
    IFS='.' read -r octet1 octet2 octet3 octet4 <<< "$1"
    echo $(( (octet1 << 24) + (octet2 << 16) + (octet3 << 8) + octet4 ))
}

banner() {
    cat <<EOF
The IP Database/Informations used in this project was from: IP2Location.com
Please comply with their Terms of use and license.
EOF
}

banner
read -p "Enter IPv4 address: " ipv4_addr
ipv4_decimal=$(get_ipv4_decimal "$ipv4_addr")
ipv4_trace "$ipv4_decimal"

