# Binance-API
This project demonstrates utilizing Binance exchange API requests/responses to retrieve/manipulate cryptocurrency data via Java object-oriented programming languange.  Following concepts and/or tools are implemented:

- REST API interface utilizing Java HttpURLConnection class/objects
- Maven dependency gson-2.8.5.jar library - to format JSON response to print 'pretty'
- Manipulation of data points from REST response to calculation Simple Moving Average
- Write data points and Simple Moving Average calculation to output file (CSV format).  Output file writes to default system user path

<b><i><u>NOTE:</b></i></u> A runnable JAR files has also been uploaded with this project.  You may download the JAR file to your local computer, and run it in Windows command-line interface via: "java -jar binance.jar".  

<b><i><u>NOTE:</b></i></u> In the Main.java class file, replace the 'secret' and 'apiKey' variable values with your own Binance user secret & api key.  The ones provided in the Main.java class are dummy keys and are NOT valid.  The keys will need to be valid in order for REST request/response to be successful.

Example: <br />
String secret = "ulVEFfRkliPEob5Go9mEPxDIuxWnGfa2qCSnlOYyRuSEmpwUcpQAhrvzT7UiJJkl"; <br />
String apiKey = "6AilactyuNx2INdp7kedFQyFKcOUyCPTFw1bQxSQua6tnQ2bPN7RA00OpLM54rFJ"; <br />
