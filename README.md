# SDETPRO_K11_WEB

# Build jar file
mvn clean install -DskipTest=true

# Run jar file
java -Dbrowser=CHROME -jar target/SDETPRO_K11_WEB-1.0-SNAPSHOT-fat-tests.jar
 