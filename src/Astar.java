public class Astar {
    public static int getDistance(int currentPathLength, String currentString, String targetString){
        int distanceCounter = 0;
        for(int i = 0; i < currentString.length(); i++){
            if(currentString.charAt(i) != targetString.charAt(i))
                distanceCounter ++;
        }
        return currentPathLength + 1 + distanceCounter;
    }
}
