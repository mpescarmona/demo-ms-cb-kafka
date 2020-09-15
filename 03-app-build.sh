source ./07-docker-env.sh

echo -e "********** Building docker image of demo application. Please wait.... **********\n"
# Comando para reconstruir la imagen docker de la aplicación
sh gradlew clean bootBuildImage
