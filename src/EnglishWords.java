import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

class EnglishWords{
    private HashSet<String> data;


    EnglishWords(String filePath){
        HashSet<String> result = new HashSet<>();

        try(BufferedReader r = new BufferedReader(new FileReader(filePath))){
            String word = r.readLine();
            while(word != null){
                result.add(word);
                word = r.readLine();
            }
        }
        catch(IOException e){
            e.getStackTrace();
        }
        data = new HashSet<>(result);
    }

    public boolean isWordValid(String s){
        return data.contains(s);
    }

    public ArrayList<String> getNearby(String s){
        ArrayList<String> nearby = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            char[] cCopy = s.toCharArray();
            for(char c = 'a'; c <= 'z'; c++){
                if(c == s.charAt(i))
                    continue;
                cCopy[i] = c;
                String sCopy = new String(cCopy);
                if(!isWordValid(sCopy))
                    continue;
                nearby.add(sCopy);
            }
        }
        return nearby;
    }

}