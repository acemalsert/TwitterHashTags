//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part2
// Description: This class contains a main for using the program with command line arguments.
// This class aims to find out which of the three hash function is faster by calculating their average number of probes.
//-----------------------------------------------------

package Part2;

public class Driver {
    public static void main (String [] args) {


        double averageNumberofProbes;


        SeperateChainingHashTable seperateChainingHashTable = new SeperateChainingHashTable(4);
        LinearProbingHashTable linearProbingHashTable = new LinearProbingHashTable(4);
        QuadraticProbingHashTable quadraticProbingHashTable = new QuadraticProbingHashTable(4);

        // This three reader functions uses different hashing functions

        System.out.println("--------------------------------------------------------------------");

        // Reader1
        Reader.readHashtags1(args[0],seperateChainingHashTable);

        System.out.println(seperateChainingHashTable.getProbing()+" times hash function called.");
        averageNumberofProbes = seperateChainingHashTable.getProbing()/seperateChainingHashTable.getM();
        System.out.println("Average number of probes are :"+averageNumberofProbes);
        seperateChainingHashTable.setProbing(0);

        seperateChainingHashTable.contains("#fb");
        System.out.println("Takes "+seperateChainingHashTable.getProbing()+" times to find #fb");

        seperateChainingHashTable.setProbing(0);
        seperateChainingHashTable.contains("#awesome");
        System.out.println("Takes "+seperateChainingHashTable.getProbing()+" times to find #awesome");

        seperateChainingHashTable.setProbing(0);
        seperateChainingHashTable.contains("#justbecause");
        System.out.println("Takes "+seperateChainingHashTable.getProbing()+" times to find #justbecause");

        System.out.println("--------------------------------------------------------------------");


        // Reader2
        Reader.readHashtags2(args[0],linearProbingHashTable);

        System.out.println(linearProbingHashTable.getProbing()+" times hash function called.");
        averageNumberofProbes = linearProbingHashTable.getProbing()/linearProbingHashTable.getM();
        System.out.println("Average number of probes are :"+averageNumberofProbes);
        linearProbingHashTable.setProbing(0);
        linearProbingHashTable.contains("#fb");
        System.out.println("Takes "+linearProbingHashTable.getProbing()+" times to find #fb");

        linearProbingHashTable.setProbing(0);
        linearProbingHashTable.contains("#awesome");
        System.out.println("Takes "+linearProbingHashTable.getProbing()+" times to find #fb");

        linearProbingHashTable.setProbing(0);
        linearProbingHashTable.contains("#justbecause");
        System.out.println("Takes "+linearProbingHashTable.getProbing()+" times to find #justbecause");

        System.out.println("--------------------------------------------------------------------");


        // Reader3
        Reader.readHashtags3(args[0],quadraticProbingHashTable);
        System.out.println(seperateChainingHashTable.getProbing()+" times hash function called.");
        averageNumberofProbes = quadraticProbingHashTable.getProbing()/quadraticProbingHashTable.getCurrentSize();
        System.out.println("Average number of probes are :"+averageNumberofProbes);
        quadraticProbingHashTable.setProbing(0);
        quadraticProbingHashTable.contains("#fb");
        System.out.println("Takes "+quadraticProbingHashTable.getProbing()+" times to find #fb");

        quadraticProbingHashTable.setProbing(0);
        quadraticProbingHashTable.contains("#awesome");
        System.out.println("Takes "+quadraticProbingHashTable.getProbing()+" times to find #awesome");

        quadraticProbingHashTable.setProbing(0);
        quadraticProbingHashTable.contains("#justbecause");
        System.out.println("Takes "+quadraticProbingHashTable.getProbing()+" times to find #justbecause");
        System.out.println("--------------------------------------------------------------------");

    }
}
