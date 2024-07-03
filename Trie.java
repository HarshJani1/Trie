package triemain;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    public class Node {
        Node[] children;
        boolean eow;

        private Node() {
            children = new Node[26];
            eow = false;
        }
    }
    
    public int size = 1;
    public Node root = new Node();

    public void insert(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new Node();
                size++;
            }
            if (i == key.length() - 1) {
                curr.children[index].eow = true;
            }
            curr = curr.children[index];
        }
    }

    public boolean search(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.eow;
    }

    public boolean wordBreak(String key) {
        Map<String, Boolean> memo = new HashMap<>();
        return wordBreakUtil(key, memo);
    }

    private boolean wordBreakUtil(String key, Map<String, Boolean> memo) {
        if (key.length() == 0) {
            return true;
        }
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        for (int i = 1; i <= key.length(); i++) {
            String firstPart = key.substring(0, i);
            String secondPart = key.substring(i);
            if (search(firstPart) && wordBreakUtil(secondPart, memo)) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }
    Node temp = root; 
    String ans ="";
    public String longestWord(StringBuilder sb){
        
        for(int i =0;i<26;i++){
            if(temp.children[i] != null && temp.children[i].eow == true){
                sb.append((char)(i+'a'));
                if(ans.length() < sb.length()){
                    ans = sb.toString();
                }
                temp = temp.children[i];
                longestWord(sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return ans;
    }

}
