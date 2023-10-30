package com.example.feignstudy2.jsch;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaTest {
    public static void main(String[] args) {
        final SFTPUtil sftpUtil = new SFTPUtil();

        final String host = "52.79.122.104";
        final String userName = "ec2-user";
        final int port = 22;
        final String uploadPath = "/home/nexgrid/sftp/download";
        final String downloadPath = "C:/Users/admin";
        final String privateKey = "C:/keys/aws_pair.ppk";

        // 업로드 시 업로드 폴더 아래에 현재 날짜 년월일을 생성하고 그 아래 올리기 위한 날짜 변수
        final Date today = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // 접속
        sftpUtil.init(host, userName, null, port, privateKey);

        // 업로드 테스트
        File uploadfile = new File("C:/Users/admin/uploadTestFile.txt"); // 파일 객체 생성

        String mkdirPath = sdf.format(today); //현재날짜 년월일
        sftpUtil.mkdir(uploadPath, mkdirPath); // 업로드경로에 현재날짜 년월일 폴더 생성
        boolean isUpload = sftpUtil.upload(uploadPath+mkdirPath, uploadfile); //업로드
        System.out.println("isUpload -" + isUpload); // 업로드 여부 확인

        /* 다운로드 테스트 */
        sftpUtil.download(downloadPath, "/home/nexgrid/sftp/download", "C:/Users/admin/sftpTest_20230427.txt");
        File downloadFile = new File("C:/Users/admin/sftpTest_20230427.txt");
        if (downloadFile.exists()) {
            System.out.println("다운로드 완료");
            System.out.println(downloadFile.getPath());
            System.out.println(downloadFile.getName());
        }

        // 업로드 다운로드 수행 후 꼭 연결을 끊어줘야 한다!!
        sftpUtil.disconnection();

    }
}
