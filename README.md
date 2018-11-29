# Binance-API
This project demonstrate use of Binance exchange API to retrieve/manipulate cryptocurrency data via Java object-oriented programming languange.  Following concepts and/or tools are implemented:

- Object-oriented programming
- REST API interface implementing Java HttpURLConnection class/objects
- Maven dependency application of gson-2.8.5.jar library (make JSON response 'pretty')
- REST response manipulation of data points to calculation Simple Moving Average
- Write data points and Simple Moving Average calculation to output file (CSV format).  Output file writes to default system user path

NOTE: In the Main.java class file, replace the 'secret' and 'apiKey' variable values with your own Binance user secret & api key.  The ones provided below are NOT valid.  The keys will need to be valid in order for REST request/response to be successful.

Example: <br />
String secret = "aBcdYr2feZqVVgT1rneOl2z4Rf0MgBYNzGVkF2TJA3GrlBoNWmbvjLWcRfOGIpWJ"; <br />
String apiKey = "bDdOEYwtaz7saHQIvsF3meDMLJggr5fnYgz1FrvqbEzGeMZW0EstPhiGVCmlnAbC"; <br />
