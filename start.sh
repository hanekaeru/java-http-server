echo "Compilation..."
javac Serveur.java
echo "Compilation complete..."
echo "Please, insert an available port for your server : "
read port
echo Server starting on port $port...
java Serveur $port