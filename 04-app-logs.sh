source ./07-docker-env.sh

echo -e "********** Logs from demo app **********\n"
# Comando para ver los logs de la aplicación
docker logs app -f
