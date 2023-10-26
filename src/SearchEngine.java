import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class SearchEngine {
    MyBinarySearchTree bst = new MyBinarySearchTree();
    String outputFilePath = "C:/Users/LENOVO/Desktop/output.txt";
    String commandFilePath= "C:/Users/LENOVO/Desktop/command.txt";


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

        bst.removeDocName(docName);


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

        bst.removeAll();
    }

    public void search(String word) {//word that isnt wanted should be written at last.
        HashSet<String> result = new HashSet<>();
        HashSet<String> notWantedElements=new HashSet<>();

        String[] words = word.split(",");
        for (String query : words) {
            if (query.charAt(0) == '!') {
                query = query.substring(1);
                Node currentNode = bst.get(query);
                if (currentNode != null) {
                    notWantedElements.addAll(currentNode.docList);
                }
            } else {//istediğimiz kelime ise. result empty ise ad all dicez. result empty deilse retainall dicez
                Node currentNode = bst.get(query);
                if (currentNode != null) {
                    if(result.isEmpty()){
                        result.addAll(currentNode.docList);
                    }else{
                        result.retainAll(currentNode.docList);
                    }

                }
            }
        }

        result.removeAll(notWantedElements);

        System.out.println("query " + word + "\n" + result+"\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath,true))) {
            //this opens the file in append mode. and with this way we are not overwriting output files content. we are adding new lines to it.
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
    public void fillCommandFile(String commandFilePath){
        //TODO command fileın içine dosyanın adını yazıcaksın
        //sonra commandleri ekle sonuna ; koyarak
        //bu methoddan sonra da command filedan satır satır okuyarak gerçekleştireceksin komutlarını

        //kullanıcı nerede yazsın hangi dosyadan okumak istediğini ve ne zaman bunları command filea yazalım


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath,true))) {

            //sırayla gerçekleştirmek istenilen işlemler yazılınacak


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readActionsFrommCommandFile(){
        //ilk kelime gerçekleştirmek istediği işlem. sonraki gelen o methodun içine göndereceğimiz input. ilk kelime clearsa direk clearlist methodunu çağıracak bi input yok


    }

}



