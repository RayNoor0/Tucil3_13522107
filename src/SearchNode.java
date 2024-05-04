import java.util.ArrayList;

public class SearchNode implements Comparable<SearchNode> {
    private String word;
    private ArrayList<String> path;
    private int pathLength;

    SearchNode(String word, ArrayList<String> path, int pathLength){
        this.word = word;
        this.path = path;
        this.pathLength = pathLength;
    }

    public String getWord(){
        return word;
    }
    public int getPathLength(){
        return pathLength;
    }
    public ArrayList<String> getPath(){
        return new ArrayList<String>(path);
    }

    public int compareTo(SearchNode other) {
        return Integer.compare(pathLength, other.pathLength);
    }
}
