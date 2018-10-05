# wifi-badge
wifi-badge let you share your Wi-Fi configuration quick and easy. It generates a QR code composed your Wi-Fi configuration. Scanning the code allows your Wi-Fi capable devices to join the network effortlessly.

## Build
`mvn clean compile assembly:single`

## Usage

### Arguments
Argument | Description
------------ | -------------
ssid | Mandatory identifier of the network.
wpa | Optional sceurity key for the network. It will be generated if unspecified.


### Basic
`java -jar wifi-badge-1.0-SNAPSHOT-jar-with-dependencies.jar -ssid just-a-ssid`

A svg default named `wifi-badge.svg` will be created. It contains the given SSID and a generated wpa key written on it, with a QR code encoded Wi-Fi configuration. 

Example\
<img src="docs/images/wifi-badge.svg?sanitize=true" alt="wifi-badge.svg" width="118px">



### With all arguments
`java -jar wifi-badge-1.0-SNAPSHOT-jar-with-dependencies.jar -ssid another-ssid -wpa security.key you_name_it.svg`

Generating a `you_name_it.svg` with SSID `another-ssid` and security key `security.key`.

Example\
<img src="docs/images/you_name_it.svg?sanitize=true" alt="you_name_it.svg" width="126px">


## Dependencies
* [zxing](https://github.com/zxing/zxing)
* [gson](https://github.com/google/gson)
* [JFreeSVG](http://www.jfree.org/jfreesvg/)
* [qrmaze](https://github.com/mrbrique/qrmaze)

## License
GNU General Public License v3.0