source ./07-docker-env.sh

echo -e "********** Stopping dockerized demo application. Please wait.... **********\n"
# Comando para bajar la demo
docker-compose -f docker-compose.yml down
