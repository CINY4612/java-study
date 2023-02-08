package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CmdPractice {

    public static void main(String[] args) {
        CmdPractice cmdPractice = new CmdPractice();
        try {
            String cmd = "where /r d: hello.txt";
            String result = cmdPractice.readCmdResult(cmd);
            System.out.println(result);
        } catch (IOException e) {
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

        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}
