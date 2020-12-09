call mvn -B -s settings.xml -DskipTests=true clean package
call java -DDATABASE_URL="postgres://postgres:password@localhost:5432/VoteTest" -jar target/dependency/webapp-runner.jar target/*.war
