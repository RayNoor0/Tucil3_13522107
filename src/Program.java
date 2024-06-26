// Program.java

import java.util.*;
import java.lang.String;

public class Program {

    private static ArrayList<String> wordPath;
    private static String start, finish, chosenAlgorithm;
    private static EnglishWords englishWords;
    public static long startTime ;
    public static void main(String[] args) {

        englishWords = new EnglishWords("src/dictionary.txt");

        takeInput();
        solve();

    }

    public static void solve() {
        System.out.println("\n========\"Result\"========");
        try{
            startTime = System.nanoTime();
            wordPath = WordLadderSolver.WordLadderSolve(start, finish, englishWords, chosenAlgorithm);
            System.out.printf("Path :\n", wordPath.size());
            for(String word : wordPath){
                System.out.println(word);
            }
            System.out.printf("Path length: %d\n", wordPath.size());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            double processMillisecondTime = (System.nanoTime()-startTime)/1e6;
            System.out.printf("Process time(Millisecond): %f\n", processMillisecondTime);
            int nodeVisited = WordLadderSolver.getNodeVisited();
            System.out.printf("Node visited: %d\n", nodeVisited);
        }
    }

    private static void takeInput(){
        boolean str1Valid, str2Valid, str3Valid;
        Scanner scanner = new Scanner(System.in);
        str1Valid = str2Valid = str3Valid = false;

        System.out.println("==\"Word Ladder Solver\"==");

        while(!str1Valid){
            System.out.printf("Start word: ");
            start = scanner.nextLine().trim().toLowerCase();
            if(englishWords.isWordValid(start)){
                str1Valid = true;
            }
            else{
                System.out.println("Start word is not a valid english word.");
            }
        }

        while(!str2Valid){
            System.out.printf("Target word: ");
            finish = scanner.nextLine().trim().toLowerCase();
            if(englishWords.isWordValid(finish)){
                if(start.length() == finish.length()){
                    str2Valid = true;
                }
                else{
                    System.out.println("Input invalid, target word has different length with start word.");
                }
            }
            else{
                System.out.println("Target word is not a valid english word.");
            }
        }

        while(!str3Valid){
            System.out.println("Available algorithm:\n-GBFS\n-AStar\n-UCS");
            System.out.printf("Select algorithm: ");
            chosenAlgorithm = scanner.nextLine().trim();
            chosenAlgorithm.toLowerCase();
            if(chosenAlgorithm.equalsIgnoreCase("ucs") || chosenAlgorithm.equalsIgnoreCase("gbfs") ||
            chosenAlgorithm.equalsIgnoreCase("astar")){
                str3Valid = true;
            }
            else{
                System.out.println("Start word is not a valid algorithm.");
            }
        }
        scanner.close();
    }
}