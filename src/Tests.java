public class Tests {
    public static void main(String[] args) {
        char x = 'X';
        int i = 0;
        int j = 1;
        System.out.print(true ? x : 0);
        System.out.print(false ? x : 0);

        System.out.print(true ? x : i);
        System.out.print(false ? x : i);
        //X880
        System.out.print((false ? x : i) < (false ? x : j));//true
    }
}
