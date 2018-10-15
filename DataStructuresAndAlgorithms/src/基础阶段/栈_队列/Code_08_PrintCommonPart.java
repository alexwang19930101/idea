package 基础阶段.栈_队列;

/**
 * create by pinkill on ${date}
 */
public class Code_08_PrintCommonPart {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }
    }

    public void printCommonPart(Node head1,Node head2){
        while(head1 != null && head2!= null){
            if(head1.value<head2.value){
                head1 = head1.next;
            }else if((head1.value > head2.value)){
                head2 = head2.next;
            }else {
                System.out.print(head1.value+" ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }
}
