source ./07-docker-env.sh

echo -e "********** Starting dockerized demo application. Please wait.... **********\n"
# Comando para iniciar la demo
docker-compose -f docker-compose.yml up -d
