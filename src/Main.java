import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputFile = "C:/Users/LENOVO/Desktop/short-poems.txt";
        SearchEngine searchEngine = new SearchEngine();
        ArrayList<String>docNames=new ArrayList<>();
        String[]fileNames={"information,short-poems,child-stories,scientists-contributions"};
        Scanner scan = new Scanner(System.in);
        /*searchEngine.load(inputFile);
        searchEngine.search("science");
        searchEngine.clearList();
        searchEngine.load(inputFile);
        searchEngine.search("science");
        searchEngine.remove("computer science");//bunu çıkardıktan sonraki science aramasında sonuçlarda computer science gözükmicek
        searchEngine.search("science");
        searchEngine.clearList();//ama clear list yapıp
        searchEngine.load(inputFile);//dosyayı yeniden binary search treemize yüklediğimizde gene scienceın hashsetinde computer science olacak
        searchEngine.search("science");//yani tekrar arama yaptığımızda computer sciencı da göreceğiz sonuçlarımızda
        searchEngine.search("science,!web");
        searchEngine.load(inputFile);
        searchEngine.search("the");
        searchEngine.search("room");
        searchEngine.search("of");
        searchEngine.clearList();
        searchEngine.load(inputFile);
        searchEngine.search("the");
        searchEngine.search("room");
        searchEngine.search("of");
        searchEngine.remove("this room");
        searchEngine.search("of");
         */

        String again="";
        searchEngine.clearList();

        do {

            System.out.println("Please enter 0 if you want the see the names of the available files");
            System.out.println("Please enter 1 if you want to load a file to the structure");
            System.out.println("Please enter 2 if you want to search a word from the structure and learn which documents include this word");
            System.out.println("Please enter 3 if you want to see the document names this file includes");
            System.out.println("Please enter 4 if you want to remove a document name from the structure.(once the doc name is removed you cannot see the name of it in the search results");
            System.out.println("Please enter 5 if you want to clear the structure and output file.");


            System.out.println("Welcome! Please enter which option you want to execute!");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 0:
                    seeTheNamesOfTheFiles(fileNames);
                    break;
                case 1:
                    System.out.println("enter the name of the file you want to load");//if the user enters a file name that does not exist in the computer show an error message saying pls enter an existing file name. these are the file names de ve listele oln dosyaları
                    String nameOfTheFile=scan.next();
                    inputFile = "C:/Users/LENOVO/Desktop/"+nameOfTheFile+".txt";

                    searchEngine.load(inputFile,docNames);
                    System.out.println(nameOfTheFile+" file is loaded, and all words are now organized alphabetically in our structure.");
                    break;
                case 2:
                    System.out.println("Enter the word you want to search for. If you are going to enter multiple words use this format='wantedword,!notwantedword'." +
                            " \nDon't forget to seperate your words with commas and add a '!' at the beginning of the word if you want to exclude files containing that word. " );//istenen formatta girmezse kızsınm

                    String searchQuery = scan.nextLine();
                    searchEngine.search(searchQuery);
                    break;
                case 3:
                    searchEngine.listAllDocumentNames(docNames);
                    break;
                case 4:
                    System.out.println("Enter the document name you want to remove");// bi integer fln girerse ya da docname listesinde yer almayan bi kelime girerse pls enter an existing docname die kızsın.ayrıca yanlış girdiği için belki docnameleri unutmuştur die o tekrar onu çağırmadan docnameleri yazdıralım mümkünse
                    String docToBeRemoved = scan.nextLine();
                    searchEngine.remove(docToBeRemoved);
                    break;
                case 5:
                    searchEngine.clearList();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option. Please enter a number between 0 and 5.");

            }
            System.out.println("Do you want to do another operation?y/n");
            again=scan.next().toLowerCase();

        }while(again.equals("y"));


    }
    public static void seeTheNamesOfTheFiles(String[]fileNames){
        for(int i=0;i<fileNames.length;i++){
            System.out.println(fileNames[i]);
        }
    }
}