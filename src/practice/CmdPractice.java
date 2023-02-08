package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdPractice {

    public static void main(String[] args) {
        CmdPractice cmdPractice = new CmdPractice();

        try {
            // 1. disk 목록 불러오기
            String cmd = "wmic logicaldisk get name";
            List<String> disks = cmdPractice.readCmdResultToArray(cmd);
            System.out.println("<< result >> disks : " + disks);

            // 2. 파일이 있는 디스크와 경로 찾기
            String fileName = "javastudy-code.txt";
            String findDisk = null;
            String filePath = null;
            for (int i = 0; i < disks.size(); i++) {
                cmd = "where /r " + disks.get(i) + " " + fileName;
                String result = cmdPractice.readCmdResult(cmd);
                if (!result.isEmpty()) {
                    filePath = result.substring(0, result.lastIndexOf("\\"));
                    findDisk = disks.get(i);
                    break;
                }
            }

            if (findDisk == null) {
                throw new NullPointerException("찾고자 하는 파일이 존재하지 않습니다.");
            }

            System.out.println("drive : " + findDisk);
            System.out.println("file path : " + filePath);

            // 3. 경로 이동 후 파일 내용 불러오기
            cmd = findDisk + " && cd " + filePath + " && type " + fileName;
            String codeInFile = cmdPractice.readCmdResult(cmd).substring(0, 4);

            // 4. 입력받은 값과 코드가 일치하는지 확인하기
            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.print("CODE : ");
                String inputCode = sc.next();

                if(inputCode.equals(codeInFile)){
                    System.out.println("Success!!!");
                    break;
                } else {
                    System.out.println("Fail...");
                }
            }
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

    public List<String> readCmdResultToArray(String cmd) throws IOException {
        System.out.println("<< cmd >> : " + cmd);
        System.out.println("##########################################");
        Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
        // cmd 결과값이 담긴 process를 문자 단위로 읽어들임
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "MS949"));
        String line = null;

        // 문자열 추가 자료형
        List<String> items = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (!line.isBlank()) {
                items.add(line.substring(0, 2));
            }
        }
        return items;
    }
}
