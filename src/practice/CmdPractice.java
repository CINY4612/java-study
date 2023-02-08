package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdPractice {

    public static void main(String[] args) {
        CmdPractice cmdPractice = new CmdPractice();

        try{
            String cmd = "type hello.txt";
            String result = cmdPractice.readCmdResult(cmd);
            System.out.println(result);

            cmd = "c: && cd c:Users/user && type hello.txt";
            result = cmdPractice.readCmdResult(cmd);
            System.out.println(result);
        } catch (IOException e){
            System.out.println("ERROR!!!");
            System.out.println(e.getMessage());
        }
    }

    public String readCmdResult(String cmd) throws IOException {
        System.out.println("<< cmd >> : " + cmd);
        System.out.println("##########################################");
        Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
        // cmd 결과값이 담긴 process를 문자 단위로 읽어들임
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "MS949"));
        String line = null;

        // 문자열 추가 자료형
        StringBuffer sb = new StringBuffer();

        try{
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e){
            System.out.println("ERROR!!!");
            System.out.println(e.getMessage());
        }

        return sb.toString();
    }
}
