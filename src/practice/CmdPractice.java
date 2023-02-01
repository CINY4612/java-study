package practice;

import java.io.IOException;

public class CmdPractice {
    public static void main(String[] args) {
        try{
//            String cmd = "c: && hello.txt";
//            String cmd = "c: && cd Users/user && hello.txt";
            String cmd = "c: && cd/ && cd Users/user && hello.txt";
            Runtime.getRuntime().exec("cmd /c " + cmd);
        } catch (IOException e){
            System.out.println("ERROR!!!");
            System.out.println(e.getMessage());
        }
    }
}
