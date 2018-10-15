package 基础阶段.树;

import java.util.Stack;

public class Code_01_PreInPosTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /*
     * 递归的遍历
     */
    public void preTraversal(Node head) {
        if (head == null) {
            return;
        }

        System.out.println(head.value);
        preTraversal(head.left);
        preTraversal(head.right);
    }

    public void inTraversal(Node head) {
        if (head == null) {
            return;
        }

        preTraversal(head.left);
        System.out.println(head.value);
        preTraversal(head.right);
    }

    public void postTraversal(Node head) {
        if (head == null) {
            return;
        }

        preTraversal(head.left);
        preTraversal(head.right);
        System.out.println(head.value);
    }

    /*
     * 非递归遍历
     */
    public void preTraversalNotRecursion(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value+" ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public void inTraversalNotResursion(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value+" ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    public void postTraverslNotResursion(Node head){
        System.out.print("post-order: ");
        if (head != null){
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null){
                    stack1.push(head.left);
                }
                if(head.right != null) {
                    stack1.push(head.right);
                }
            }
            while (!stack2.isEmpty()){
                System.out.print(stack2.pop().value+" ");
            }
        }
    }
}
