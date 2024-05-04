import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;

public class WordLadderSolver {
    private static PriorityQueue<SearchNode> pq;
    private static HashSet<String> visited;
    private static String target;
    private static EnglishWords englishWords;

    public static ArrayList<String> WordLadderSolve(String start, String target, EnglishWords englishWords, String chosenAlgorithm)throws Exception{
        prepare(start, target, englishWords);
        try{
            return solve(chosenAlgorithm);
        }
        catch(Exception e){
            throw e;
        }
    }
    
    private static void prepare(String _start, String _target, EnglishWords _englishWords){
        pq = new PriorityQueue<>();
        ArrayList<String> currentPath = new ArrayList<String>();
        currentPath.add(_start);
        pq.add(new SearchNode(_start, currentPath, 0));
        visited = new HashSet<>();
        englishWords = _englishWords;
        target = _target;
    }


    public static ArrayList<String> solve(String chosenAlgorithm) throws Exception{
        SearchNode currentNode;
        ArrayList<String> nearbyWords;
        ArrayList<String> newCurrentPath;
        int newPathLength;
        while(!pq.isEmpty()){
            currentNode = pq.remove();

            if(currentNode.getWord().equalsIgnoreCase(target)){
                return currentNode.getPath();
            }

            visited.add(currentNode.getWord());
            nearbyWords = englishWords.getNearby(currentNode.getWord());
            for(String nearbyWord : nearbyWords){

                // skip if visited
                if(visited.contains(nearbyWord))
                    continue;

                
                newCurrentPath = new ArrayList<String>(currentNode.getPath());
                newCurrentPath.add(nearbyWord);

                // measure distance depends on chosen Algorithm
                if(chosenAlgorithm.equalsIgnoreCase("UCS"))
                    newPathLength = UCS.getDistance(currentNode.getPathLength());
                else if(chosenAlgorithm.equalsIgnoreCase("GBFS"))
                    newPathLength = GBFS.getDistance(nearbyWord, target);
                else
                    newPathLength = Astar.getDistance(currentNode.getPathLength(), nearbyWord, target);

                // add nearbyNode to queue
                pq.add(new SearchNode (nearbyWord, newCurrentPath, newPathLength));
            }
        }
        throw new Exception("target tidak dapat dicapai");
    }
}
