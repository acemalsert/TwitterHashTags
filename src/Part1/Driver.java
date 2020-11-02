//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part1
// Description: This class contains a main class for using the program with command line arguments
//-----------------------------------------------------

package Part1;

public class Driver {
    public static void main(String[] args) {

        SeperateChainingHashTable seperateChainingHashTable = new SeperateChainingHashTable(4);// Creates a Seperate Chaining Hash Table with the capacity of 4
        Reader.readHashtags(args[0], seperateChainingHashTable); // Reads the file given with command line arguments into Seperate Chaining HashTable if file doesn't exist throws an excepiton
        //System.out.println(seperateChainingHashTable.keysAndCounts());
        System.out.println(seperateChainingHashTable.MostPopularHashTags());
    }
}