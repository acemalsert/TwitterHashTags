//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part1
// Description: This class defines a reader which has a readHashTags method that reads the  hashTags of the given file and put them into HashTable
//-----------------------------------------------------

package Part1;
import java.io.*;
import java.util.Scanner;

public class Reader {

    // Reads the given input file word by word into an Seperate Chaining HashTable if the word contains "#"
    // Throws FileNotFoundException if given file couldn't be opened
    public static void readHashtags(String fileName, SeperateChainingHashTable<String,Integer> seperateChainingHashTable)  {
        File file = new File(fileName);
        try (Scanner input = new Scanner(file)) {
            int i = 0;
            while (input.hasNext()) {
                String word = input.next();
                if (word.contains("#")){
                    seperateChainingHashTable.put(word, i);
                    i++;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("The file path could not found.");
        }
    }

    // class tester
    public static void main(String [] args)  {
        SeperateChainingHashTable<String,Integer> seperateChainingHashTable = new SeperateChainingHashTable(4);
        String fileName = "C:\\Users\\ACS\\Desktop\\twitter.txt";
        String anotherFile = "C:\\Users\\ACS\\Desktop\\test.txt";
        readHashtags(fileName,seperateChainingHashTable);
        //readHashtags(anotherFile,seperateChainingHashTable);
        System.out.println(seperateChainingHashTable.getSize());
        System.out.println(seperateChainingHashTable.keys());
        System.out.println(seperateChainingHashTable.keysAndCounts());
        System.out.println(seperateChainingHashTable.MostPopularHashTags());
    }
}
