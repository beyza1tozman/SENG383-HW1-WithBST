public class MyBinarySearchTree {
    Node root;

    public void insert(String word) {
        root = insert_Recursive(root, word);
    }

    // Recursive insert function with duplicate checking
    public Node insert_Recursive(Node root, String word) {
        if (root == null) {
            root = new Node(word);
            return root;
        }

        int comparisonResult = word.compareToIgnoreCase(root.word);

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

            if (comparisonResult == 0) {
                // Word found, return the node
                return current;
            } else if (comparisonResult < 0) {
                // Word is smaller, search in the left subtree
                current = current.left;
            } else {
                // Word is larger, search in the right subtree
                current = current.right;
            }
        }

        // Word not found, return null
        return null;
    }
    public void removeDocName(String docName) {
        Node current = root;

        while (current != null) {
            if (current.left == null) {
                // Process the current node (e.g., remove the docName from its hashset)
                current.docList.remove(docName);

                current = current.right;
            } else {
                // Find the in-order predecessor
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Set the right link of the in-order predecessor to the current node
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Restore the right link of the in-order predecessor
                    predecessor.right = null;

                    // Process the current node (e.g., remove the docName from its hashset)
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

        // Recursively remove the left and right subtrees
        current.left = removeAll(current.left);
        current.right = removeAll(current.right);

        // Delete the current node (you can perform any necessary cleanup here)
        current = null;

        return current;
    }

}
