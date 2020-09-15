source ./07-docker-env.sh

echo -e "********** Starting bash console in dockerized kafka. Please wait.... **********\n"
# Comando para ingresar a la terminal de kafka
docker exec -it kafka /bin/bash
