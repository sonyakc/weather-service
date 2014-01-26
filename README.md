weather-service
===============
To run the weather service forecast retriever please 

1) Run WeatherServiceClient.java passing in two String arguments
a) Sample program arguments are as follows "10018" "json"
1st argument is the zip code 
2nd argument is the format, media type representation to retrieve

Output: will be forecast details to system.out 

Program details / stack

a) Jersey 2.5.1 to code the REST web service client
b) Media moxy for response handling json
c) Java 7
d) Gson and Simple Json for json parsing
e) Maven for dependency management
f) Eclipse IDE
