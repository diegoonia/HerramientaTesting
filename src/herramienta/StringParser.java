package herramienta;

import java.util.ArrayList;

public class StringParser {
    
    private String text;
    
    public StringParser(String s) {
        text = s;
    }
    
    public String skipDuplicate(char c, int qty) {
        // c: caracter a omitir; qty: cantidad de duplicados a omitir;
        
        ArrayList<Integer> duplicates_list = new ArrayList<Integer>();
        int current_pos = 0;
        int last_pos = 0;
        int marked = 0;
        
        while((current_pos = text.indexOf(c, current_pos)) != -1 && last_pos < text.length()) {
            if(current_pos == last_pos + 1) {
                marked++;
                if(marked <= qty) {
                    duplicates_list.add(current_pos - marked);
                }
            }
            else {
                marked = 0;
            }
            last_pos = current_pos;
            current_pos++;
        }
        
        if(duplicates_list.size() > 0) {
            StringBuilder sb = new StringBuilder(text);
            for(Integer i : duplicates_list) {
                sb.deleteCharAt(i);
            }
            return sb.toString();
        }
        
        return null;
    }
    
    public void setText(String s) {
        text = s;
    }
    
}
