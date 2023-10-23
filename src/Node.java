import java.util.HashSet;

public class Node {
    String word;
    Node left,right;
    HashSet<String> docList;
    public Node(String word){
        this.word=word;
        docList=new HashSet<>();
        left=right=null;
    }

}
