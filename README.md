# Projet Hearthstone


### compilation

find . -name "*.java" > classes.txt

javac -cp bin:lib/* -d bin @classes.txt


### execution de la classe principale

java -Djava.net.useSystemProxies=true -cp bin:lib/* hearthstone.Hearthstone

