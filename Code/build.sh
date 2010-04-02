#!/bin/sh

echo "rm -f ../Client/*class"
rm -f ../Client/*class
echo "rm -f ../Server/*class"
rm -f ../Server/*class

echo "javac SkatClient.java -d ../Client/"
javac SkatClient.java -d ../Client/
echo "javac SkatServer.java -d ../Server/"
javac SkatServer.java -d ../Server/



