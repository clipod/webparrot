# webparrot
This is a light weight webserver mock application written in spring boot. Using this application you can mock headers,body and status code to show up in the response. The mock data will be stored in JVM memory so every restart gets your data refreshed.

Supported Java Versions : 1.7 and higher.

Supported methods types for web parrot are POST,PUT,DELETE,GET,HEAD.

<h3>Deploying and Running:</h3>

1)Clone this project into your local directory.

2)Install maven.

3)Navigate into the project directory and run the below command
```
mvn clean spring-boot:run
```
<h5>OR</h5>

If you dont want to clone the project everytime and run it you can execute below commands.
```
mvn clean package
java -jar target/webparrot-0.0.1-SNAPSHOT.jar
```
The second apporach we are using the power of spring boot to create an executable jar file.

4)The application is available on localhost:8080


<h3>Usage:</h3>

To mock a response, first the information has to be fed to the application via POST call. For feeding this information, along with the mock data, pass this header in the request 

```
insert : true
```

For mocking response headers, the header name should be prefixed with "resp-".

For mocking http response status, pass this header "resp-status" with appropriate response code.


<h3>Example:</h3>

<h5>Insert</h5>

```
URL: localhost:8080/v1/accounts/020-123/methods

Headers:
Content-Type : text/plain
Accept : text/plain
insert: true
resp-status: 200
resp-header1: value1

Body:
<xmltag1>This is a demo xml</xmltag1>
```

After the insert you will get response with no body and status code as 204.

Now when you try to retrieve the mock data you have to use the same URL, 
```
localhost:8080/v1/accounts/020-123/methods
```
<h5>POST/PUT/GET/DELETE/HEAD response</h5>

```
Http Status: 200

Headers:
Content-Type : text/plain
header1 : value1

Body:
<xmltag1>This is a demo xml</xmltag1>
