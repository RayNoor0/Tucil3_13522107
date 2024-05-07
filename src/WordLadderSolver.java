import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;

public class WordLadderSolver {
    private static PriorityQueue<SearchNode> pq;
    private static HashSet<String> visited;
    private static HashMap<String, String> parents;
    private static ArrayList<String> resultPath;
    private static String target, start;
    private static EnglishWords englishWords;
    private static int nodeVisited;

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
        target = _target;
        start = _start;

        pq = new PriorityQueue<>();
        resultPath = new ArrayList<String>();
        pq.add(new SearchNode(_start, _start, 0));
        visited = new HashSet<>();
        parents = new HashMap<>();
        englishWords = _englishWords;
        nodeVisited = 0;
    }


    private static ArrayList<String> solve(String chosenAlgorithm) throws Exception{
        SearchNode currentNode;
        ArrayList<String> nearbyWords;
        int newPathValue;
        while(!pq.isEmpty()){
            currentNode = pq.remove();
            
            // skip if visited
            if(visited.contains(currentNode.getWord()))
            continue;
            
            // clear queue if using gbfs
            if(chosenAlgorithm.equalsIgnoreCase("gbfs"))
            pq.clear();
            
            // add to parent value to currentWord
            if(currentNode.getParent() != null)
            parents.put(currentNode.getWord(), currentNode.getParent());
            
            // add to visited
            visited.add(currentNode.getWord());
            nodeVisited+=1;
            
            // return if target word is found
            if(currentNode.getWord().equalsIgnoreCase(target)){
                traceBackParents(currentNode.getWord());
                return resultPath;
            }
            
            // visit all nearby words
            nearbyWords = englishWords.getNearby(currentNode.getWord());
            for(String nearbyWord : nearbyWords){
                // skip if visited
                if(visited.contains(nearbyWord))
                    continue;

                // measure new node value depends on chosen Algorithm
                if(chosenAlgorithm.equalsIgnoreCase("ucs")){
                    newPathValue = UCS.getDistance(currentNode.getPathLength());
                }
                else if(chosenAlgorithm.equalsIgnoreCase("gbfs")){
                    newPathValue = GBFS.getDistance(nearbyWord, target);
                }
                else{
                    assert chosenAlgorithm.equalsIgnoreCase("astar");
                    newPathValue = Astar.getDistance(currentNode.getPathLength(), nearbyWord, target);
                }

                // add nearbyNode to queue
                pq.add(new SearchNode (nearbyWord, currentNode.getWord(), newPathValue));
            }
        }
        throw new Exception("Target is unreachable");
    }

    private static void traceBackParents(String currentWord){
        // System.out.println(parents);
        resultPath.add(0,currentWord);
        if(!currentWord.equalsIgnoreCase(start))
            traceBackParents(parents.get(currentWord));
    }

    public static int getNodeVisited(){
        return nodeVisited;
    }
}
