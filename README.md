Vertex Cover Matrix Inverter

This repository contains a Java program that reads a file containing adjacency matrices representing graphs and inverts the matrices by converting 1s to 0s and 0s to 1s. The inverted matrices are then written to an output file.

Features:
Reads adjacency matrices from an input file
Inverts adjacency matrices (1s to 0s, 0s to 1s)
Writes the inverted matrices to an output file

Getting Started:
To set up the project, clone the repository to your local machine.
git clone https://github.com/username/vertex-cover-matrix-inverter.git

After cloning the repository, compile and run the Java program using your favorite IDE or command line.

Prerequisites:
Java Development Kit (JDK) 8 or higher

Usage:
1. Prepare an input file containing adjacency matrices of graphs. The file should start with a single integer on each line indicating the size of the matrix, followed by the matrix itself. 

Example:
3
0 1 0
1 0 1
0 1 0

2. Modify the 'readFile' variable in the main method to point to your input file.
3. Run the Java program. The inverted matrices will be written to the output file specified by the outputFile variable in the main method.
