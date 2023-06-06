package com.ustc.oep;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
public class JudgeServerTest {
    public static void main(String[] args) throws IOException {
        Path path = new File("src/main/java/com/ustc/oep/judge/testcode/Main.java").toPath();
        String sourceCode = new String(Files.readAllBytes(path));
        System.out.println(sourceCode);
        try {
            // 创建进程并执行编译命令
            ProcessBuilder processBuilder = new ProcessBuilder("javac", "-encoding", "GBK", "-d", "C:\\Users\\YU\\Desktop\\output");
            Process process = processBuilder.start();

            // 获取进程的输出流和错误流
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));

            // 向进程的标准输入流写入源代码
            process.getOutputStream().write(sourceCode.getBytes(StandardCharsets.UTF_8));
            process.getOutputStream().close();

            // 读取编译结果和错误信息
            String line;
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line); // 打印编译错误信息
            }
            while ((line = outputReader.readLine()) != null) {
                System.out.println(line); // 打印编译输出信息
            }

            // 等待编译完成
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("编译成功.");
            } else {
                System.out.println("编译失败.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
