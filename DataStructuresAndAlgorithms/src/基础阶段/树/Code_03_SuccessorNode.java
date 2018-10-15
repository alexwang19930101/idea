package 基础阶段.树;

/**
 * create by pinkill on ${date}
 */
public class Code_03_SuccessorNode {
    public static class Node {
        public int value;
        public Node parent;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null){
            return  getLeftMost(node.right);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private Node getLeftMost(Node head) {
        while (head.left != null){
            head = head.left;
        }
        return head;
    }
}
