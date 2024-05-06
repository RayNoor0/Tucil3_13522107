
public class SearchNode implements Comparable<SearchNode> {
    private String word;
    private String parent;
    private int pathLength;

    SearchNode(String word, String parent, int pathLength){
        this.word = word;
        this.parent = parent;
        this.pathLength = pathLength;
    }

    public String getWord(){
        return word;
    }
    public int getPathLength(){
        return pathLength;
    }
    public String getParent(){
        return new String(parent);
    }

    public int compareTo(SearchNode other) {
        return Integer.compare(pathLength, other.pathLength);
    }
}
