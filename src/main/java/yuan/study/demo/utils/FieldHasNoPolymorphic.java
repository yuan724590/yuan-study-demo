package yuan.study.demo.utils;

/**
 * @author yuan
 * @Date 2021/9/25 11:57
 */
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father, i have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Son, i have $" + money);
        }
    }

//    public static void main(String[] args) {
//        Father gay = new Son();
//        System.out.println("This gay has $" + gay.money);
//    }
}
