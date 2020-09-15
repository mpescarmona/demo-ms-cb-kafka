source ./07-docker-env.sh

echo -e "********** Partition list of demo topic in dockerized kafka. Please wait.... **********\n"
# Comando para ver el topico de kafka
#docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --zookeeper zookeeper:2181 --topic demo-microservices-cb-kafka-topic --describe
#docker exec -it kafka kafka-topics.sh --zookeeper zookeeper:2181 --topic demo-microservices-cb-kafka-topic --describe
docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --topic demo-ms-cb-kafka-topic --describe
