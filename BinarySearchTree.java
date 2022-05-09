public class BinarySearchTree  {
    private static TreeNode root;
    private static class TreeNode { // nested private treeNode class
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode () {
        }
        public void setLeft(TreeNode left) {
            this.left = left;
        }
        public TreeNode getLeft() {
            return left;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
    public static TreeNode ArrayToTree(int array[], int low, int high) {
        if (low > high) { //ensuring that the left value is never greater than the right value
            return null;
        }
        TreeNode root = new TreeNode();
        int midPoint = low + (high - low) / 2; //calculating mid point of array

        root.setData(array[midPoint]); // getting index of root
        // base case #2 - when there only one node and it has no children
        if (low == high) {
            root.setLeft(null);
            root.setRight(null);
            return root;
        }
        root.setLeft(ArrayToTree(array, low, midPoint - 1)); //recursively adding node to left side of tree
        root.setRight(ArrayToTree(array, midPoint + 1, high)); //recursively adding node to right side of tree
        return root;
    }
    public static void postOrder(TreeNode root) {
        if(root == null) { //error condition - root is null, return
            return;
        }
        postOrder(root.getLeft()); //traverse left
        postOrder(root.getRight()); // traverse right
        System.out.print(root.getData() + " "); //visit root

    }
    public static void preOrder(TreeNode root) {
        if(root == null) { //error condition - root is null
            return;
        }

        System.out.print(root.getData() + " "); //visit root
        preOrder(root.getLeft()); //traverse left
        preOrder(root.getRight()); // traverse right

    }
    public static void inOrder(TreeNode root) { // error condition root is null
        if (root == null) { // error coniditon -  root is null
            return;
        }
        inOrder(root.getLeft()); //traverse left
        System.out.print(root.data + " "); //visit root
        inOrder(root.getRight());//traverse right
    }

    public static void main(String[] args) {

        int[] ar = {1,2,3,4,5,6,7,8,9};

        int end = ar.length;
        root = ArrayToTree(ar, 0, end - 1);
        System.out.println("PreOrder :");
        preOrder(root);
        System.out.println();
        System.out.println("PostOrder : ");
        postOrder(root);
        System.out.println();
        System.out.println("In-Order : ");
        inOrder(root);
        System.out.println();

    }
}
