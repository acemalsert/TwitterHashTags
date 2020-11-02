//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part2
// Description: This class defines a reader class which has three different readHashTags methods
// for three different hashtables that reads the  hashTags of the given file and put them into HashTable
//-----------------------------------------------------

package Part2;
import java.io.*;
import java.util.Scanner;

public class Reader {

    // Reads the given input file word by word into an Seperate Chaining HashTable if the word contains "#"
    // Throws FileNotFoundException if given file couldn't be opened
    public static void readHashtags1(String fileName, SeperateChainingHashTable<String,Integer> seperateChainingHashTable)  {
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

    // Same as readHashtags1 except takes a Linear Probing HashTable as parameter
    public static void readHashtags2(String fileName, LinearProbingHashTable<String,Integer> linearProbingHashTable)  {
        File file = new File(fileName);
        try (Scanner input = new Scanner(file)) {
            int i = 0;
            while (input.hasNext()) {
                String word = input.next();
                if (word.contains("#")){
                    linearProbingHashTable.put(word,i);
                    i++;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("The file path could not found.");
        }
    }

    // Same as readHashtags1 except takes a Quadratic Probing HashTable as parameter
    public static void readHashtags3(String fileName, QuadraticProbingHashTable quadraticProbingHashTable)  {
        File file = new File(fileName);
        try (Scanner input = new Scanner(file)) {

            while (input.hasNext()) {
                String word = input.next();
                if (word.contains("#")){
                    quadraticProbingHashTable.insert(word);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("The file path could not found.");
        }
    }

    // class tester
    public static void main(String [] args)  {
        SeperateChainingHashTable<String,Integer> seperateChainingHashTable = new SeperateChainingHashTable(5000);
        String fileName = "C:\\Users\\ACS\\Desktop\\twitter.txt";
        String anotherFile = "C:\\Users\\ACS\\Desktop\\test.txt";
        readHashtags1(fileName,seperateChainingHashTable);
        //readHashtags(anotherFile,seperateChainingHashTable);
        System.out.println(seperateChainingHashTable.getSize());
        System.out.println(seperateChainingHashTable.keys());
        System.out.println(seperateChainingHashTable.keysAndCounts());
    }
}
