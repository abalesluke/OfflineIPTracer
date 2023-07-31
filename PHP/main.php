<?php
function ipv4_trace($ip_decimal) {
    $ip_db_path = "./IP2LOCATION-LITE-DB11.CSV";
    if (($handle = fopen($ip_db_path, "r")) !== false) {
        $notFound = true;
        while (($data = fgetcsv($handle)) !== false) {
            list($start, $end, $code, $country, $region, $city, $lat, $lon, $zip, $tz) = $data;
            if ($ip_decimal >= (int)$start && $ip_decimal <= (int)$end) {
                $notFound = false;
                echo str_repeat("=", 30) . "[IP-INFO]" . str_repeat("=", 30) . PHP_EOL;
                echo "Country Code: $code" . PHP_EOL;
                echo "Country: $country" . PHP_EOL;
                echo "Region: $region" . PHP_EOL;
                echo "City: $city" . PHP_EOL;
                echo "ZIP Code: $zip" . PHP_EOL;
                echo "Time Zone: $tz" . PHP_EOL;
                echo "Latitude: $lat" . PHP_EOL;
                echo "Longitude: $lon" . PHP_EOL;
                echo str_repeat("=", 30) . "[IP-INFO]" . str_repeat("=", 30) . PHP_EOL;
                break;
            }
        }
        fclose($handle);
        if ($notFound) {
            echo "[Sorry](🥺)-IP range was not in the database!" . PHP_EOL;
        }
    }
}

function get_ipv4_decimal($ipv4_addr) {
    $octets = explode(".", $ipv4_addr);
    $ipv4_decimal = 0;
    foreach ($octets as $octet) {
        $ipv4_decimal = ($ipv4_decimal << 8) + (int)$octet;
    }
    return $ipv4_decimal;
}

function banner() {
    echo '
    The IP Database/Informations used in this project was from: IP2Location.com
    Please comply with their Terms of use and license.'.PHP_EOL;
}

banner();
if(isset($_REQUEST['ip'])){
    $ipv4_addr = $_REQUEST['ip'];
    $ipv4_addr = trim($ipv4_addr);
    $ipv4_decimal = get_ipv4_decimal($ipv4_addr);
    ipv4_trace($ipv4_decimal);
}

