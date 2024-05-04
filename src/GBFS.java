public class GBFS {
    public static int getDistance(String currentString, String targetString){
        int distanceCounter = 0;
        for(int i = 0; i < currentString.length(); i++){
            if(currentString.charAt(i) != targetString.charAt(i))
                distanceCounter ++;
        }
        return distanceCounter;
    }    
}
