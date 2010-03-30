#!/bin/sh

echo "rm -f ../Client/*class"
rm -f ../Client/*class
echo "rm -f ../Server/*class"
rm -f ../Server/*class

echo "javac Client.java -d ../Client/"
javac Client.java -d ../Client/
echo "javac Skat.java -d ../Server/"
javac Skat.java -d ../Server/



