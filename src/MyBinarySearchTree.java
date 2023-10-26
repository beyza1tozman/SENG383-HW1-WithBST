public class MyBinarySearchTree {
    Node root;

    public void insert(String word) {
        root = insert_Recursive(root, word);
    }


    public Node insert_Recursive(Node root, String word) {
        if (root == null) {
            root = new Node(word);
            return root;
        }

        int comparisonResult = word.compareToIgnoreCase(root.word);//eklenicek kelimenin alfabetik olarak bi öncekine göre ne konumda olduüuna bakıyoruz

        if (comparisonResult < 0) {
            root.left = insert_Recursive(root.left, word);
        } else if (comparisonResult > 0) {
            root.right = insert_Recursive(root.right, word);
        }
        // If comparisonResult is 0, it's a duplicate, so you can choose to ignore it. aynı kelimelerin eklenmesine izin vermiyorym yani. binary search treede her kelimeden bir tane olcak
        return root;
    }


    public Node get(String word) {
        Node current = root;

        while (current != null) {
            int comparisonResult = word.compareToIgnoreCase(current.word);

            if (comparisonResult == 0) { // Word found, return the node
                return current;
            } else if (comparisonResult < 0) {// Word is smaller, search in the left subtree
                current = current.left;
            } else { // Word is larger, search in the right subtree
                current = current.right;
            }
        }

        return null;// Word not found, return null
    }
    public void removeDocName(String docName) {//bstdeki her wordden o docnamei çıkarıcak. hashsetlerinden
        Node current = root;

        while (current != null) {
            if (current.left == null) {
                // process the current node (remove the docName from its hashset).sol boşsa currentten çıkart ve currentı sağa götür
                current.docList.remove(docName);

                current = current.right;
            } else {//sol taraf null deilse
                // find the in-order previousNode
                Node previousNode = current.left;
                while (previousNode.right != null && previousNode.right != current) {
                    previousNode = previousNode.right;
                }

                if (previousNode.right == null) {
                    //set the right link of the in-order previousNode to the current node
                    previousNode.right = current;
                    current = current.left;
                } else {
                    // restore the right link of the in-order previousNode
                    previousNode.right = null;

                    // process the current node (e.g., remove the docName from its hashset)
                    current.docList.remove(docName);

                    current = current.right;
                }
            }
        }
    }
    public void removeAll() {
        root = removeAll(root);
    }

    private Node removeAll(Node current) {
        if (current == null) {
            return null;
        }

        // recursively remove the left and right subtrees
        current.left = removeAll(current.left);
        current.right = removeAll(current.right);

        // delete the current node
        current = null;

        return current;
    }

}
