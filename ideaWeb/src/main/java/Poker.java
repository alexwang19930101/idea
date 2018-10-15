import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.*;

/**
 * create by pinkill on ${date}
 */
public class Poker {
    private static final String[] NUM = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
    private static final String[] COLOR = {"♠", "♥", "♣", "♦"};
    private static final String[] KINGS = {"大王", "小王"};

    //实际上数据已经在这里通过Collection.shuffle()存好了，每个人取用17张就好。
    //没有做 % 3的发牌，因为如果向前端穿数据 % 3也仅仅是将数据再打乱一遍而已
    List<String> pokers = new ArrayList<String>();

    public List<String> pokerShuffle() {
        for (String num : NUM) {
            for (String color : COLOR) {
                pokers.add(color + num);
            }
        }
        for (int i = 0; i < KINGS.length; i++) {
            pokers.add(KINGS[i]);
        }
        Collections.shuffle(pokers);
        return pokers;
    }

    public void licensing() {
        int i;
        for (i=0 ;i<54;i++){
            if (i%17 == 0 && i<51){
                System.out.println();
                System.out.println("第"+(i/17+1)+"个人的牌是：");
            }else if(i % 17 == 0){
                System.out.println();
                System.out.println("底牌是：");
            }
            System.out.print(pokers.get(i)+" ");
        }
    }

    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.pokerShuffle();
        poker.licensing();
    }

    //比较器没写好，感觉写的很丑，不要了
    private static class MyComparator implements  Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            if (o1.equals("大王")) {
                return 1;
            } else if (o2.equals("大王")) {
                return -1;
            } else if (o1.equals("小王")) {
                return 1;
            } else if (o2.equals("小王")) {
                return -1;
            } else {
                return o1.charAt(1) - o2.charAt(1);
            }
        }
    }
}
