import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class SearchEngine {
    MyBinarySearchTree bst = new MyBinarySearchTree();
    String outputFilePath = "C:/Users/LENOVO/Desktop/output.txt";

    public void load(String filePath,ArrayList<String>docNames) {
        String docName = null;
        boolean isFirstLine = true;
        int lineWithHashTagCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.equals("###")) {
                    lineWithHashTagCount++;
                    continue;
                }
                if (lineWithHashTagCount == 2) {
                    docName = line.toLowerCase();
                    if(!docNames.contains(docName)){
                        docNames.add(docName);
                    }
                    lineWithHashTagCount = 0;
                    continue;
                }
                if (isFirstLine) {
                    docName = line.toLowerCase();
                    if(!docNames.contains(docName)){
                        docNames.add(docName);
                    }
                    isFirstLine = false;
                } else {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                        //wordu binary search treeye ekle
                        if (!word.isEmpty()) {
                            bst.insert(word);
                            //get the node of the word
                            Node currnetNode = bst.get(word);
                            currnetNode.docList.add(docName);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void remove(String docName) {
        docName = docName.toLowerCase();
        //traverse every node in binary search tree and remove the docname from its hashset
        bst.removeDocName(docName);

        //current.docList.remove(docName)
        System.out.println("Document name is successfully removed from our structure");

    }

    public void clearList() {
        try {
            FileWriter writer = new FileWriter(outputFilePath);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //empty out the binary search tree
        bst.removeAll();
        //System.out.println("output file and data structure is successfully deleted!\n");
    }

    public void search(String word) {
        HashSet<String> result = new HashSet<>();
        String[] words = word.split(",");
        for (String query : words) {
            if (query.charAt(0) == '!') {
                query = query.substring(1);
                Node currentNode = bst.get(query);
                if (currentNode != null) {
                    for (String docName : currentNode.docList) {
                        result.remove(docName);
                    }
                }
            } else {
                Node currentNode = bst.get(query);
                if (currentNode != null) {
                    for (String docName : currentNode.docList) {
                        result.add(docName);
                    }
                }

            }

        }
        System.out.println("query " + word + "\n" + result+"\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath,true))) {//this opens the file in append mode. and with this way we are not overwriting output files content. we are adding new lines to it.
            //output file boşsa direkt en başa yazabilirsin ama eğer içinde bir şeyler varsa en sonuna gelip sonuna eklemen lazım
            writer.write("query " + word + "\n" + result);
            writer.newLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listAllDocumentNames(ArrayList<String>docNames){
        for(int i=0;i<docNames.size();i++){
            System.out.println(docNames.get(i));
        }

    }

}



