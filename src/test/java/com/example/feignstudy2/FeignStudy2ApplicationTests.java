package com.example.feignstudy2;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
class FeignStudy2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void sftpTest() {
        String requestContent = "";

        //파일경로
        String fileLocation = "";

        JSch jSch = new JSch();
        Session session;
        ChannelSftp sftp;

        try {
            String id = "nexgrid";
            String ip = "192.168.10.199";
            Integer port = 22;
            String pw = "~1q2w3e4r5t6y";

            // 접속 정보 세팅
            session = jSch.getSession(id, ip, port);
            session.setPassword(pw);

            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            session.connect();

            // sftp 연결
            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;

            try {
                //확인해야 되는 파일명
                String checkFileName= "sftpTest_20230427.txt";

                //파일경로 지정
                fileLocation = "/home/nexgrid/sftp/download";
                sftp.cd(fileLocation);

                //inputstream 파일 정보를 담는다.
                InputStream inputStream = null;
                //요청 파일을 가져온다.
                inputStream = sftp.get(checkFileName);
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    try {
                        int count = 0;
                        String line = ""; //텍스트 파일 한줄씩 담을 객체
                        while(line != null) {
                            try {
                                line = reader.readLine();
                            }catch (Exception e) {
                                line = null;
                            }
                            if(line == null) {break;}//읽을 행이 없으면 종료
                            requestContent = line;
                            System.out.println("읽은 정보 -> " + requestContent);
                        }
                    }catch (Exception e) {
                        System.out.println(e.toString());
                        reader.close();
                    }
                }catch (Exception e) {
                    System.out.println(e.toString());
                    sftp.quit(); //sftp 종료
                    session.disconnect(); //세션 종료
                }
            }catch (Exception e) {
                System.out.println(e.toString());
                sftp.quit(); //sftp 종료
                session.disconnect(); //세션 종료
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    @Test
    void sftpTest2() {
        String requestContent = "";

        //파일경로
        String fileLocation = "";

        JSch jSch = new JSch();
        Session session;
        ChannelSftp sftp;

        try {
            String id = "nexgrid";
            String ip = "192.168.10.199";
            Integer port = 22;
            String pw = "~1q2w3e4r5t6y";

            // 접속 정보 세팅
            session = jSch.getSession(id, ip, port);
            session.setPassword(pw);

            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            session.connect();

            // sftp 연결
            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;

            try {
                //확인해야 되는 파일명
                String checkFileName= "sftpTest_20230427.txt";

                //파일경로 지정
                fileLocation = "/home/nexgrid/sftp/download";
                sftp.cd(fileLocation);

                //inputstream 파일 정보를 담는다.
                InputStream inputStream = null;
                //요청 파일을 가져온다.
                inputStream = sftp.get(checkFileName);
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    try {
                        int count = 0;
                        String line = ""; //텍스트 파일 한줄씩 담을 객체
                        while(line != null) {
                            try {
                                line = reader.readLine();
                            }catch (Exception e) {
                                line = null;
                            }
                            if(line == null) {break;}//읽을 행이 없으면 종료
                            requestContent = line;
                            System.out.println("읽은 정보 -> " + requestContent);
                        }
                    }catch (Exception e) {
                        System.out.println(e.toString());
                        reader.close();
                    }
                }catch (Exception e) {
                    System.out.println(e.toString());
                    sftp.quit(); //sftp 종료
                    session.disconnect(); //세션 종료
                }
            }catch (Exception e) {
                System.out.println(e.toString());
                sftp.quit(); //sftp 종료
                session.disconnect(); //세션 종료
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void maskingPhoneTest() {
        // 번호 설정
        String phoneNum = "010012341234";

// 마스킹할 번호가 존재해야 하므로
        if(phoneNum != null && !"".equals(phoneNum)){
            phoneNum = phoneNum.substring(0,4) + "****"
                    + phoneNum.substring(phoneNum.length()-4, phoneNum.length());
        }

        System.out.println("휴대폰 번호 마스킹 : " + phoneNum);
    }

    @Test
    void maskingNameTest() {
        String name = "";
        StringBuilder maskedName = new StringBuilder(name);
        int length = name.length();
        System.out.println("length = " + length);
        if(length > 1) {
            for(int i = 1; i < Math.max(2, length - 1); i++) {
                maskedName.setCharAt(i, '*');
            }
        }
        System.out.println(maskedName);
    }

    @Test
    void sha512_test() {
        String pwd = "1234";
        try{

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            //출력
            System.out.println(hexString +", "+ hexString.length());
//            return hexString.toString();

        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Test
    void sha256_test() {
        String pwd = "1234";
        try{

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            //출력
            System.out.println(hexString +", "+ hexString.length());
//            return hexString.toString();

        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Test
    void md5_test() {
        String pwd = "1234";
        String MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5 = null;
        }
        System.out.println(MD5);
//        return MD5;
    }
}
