package com.ustc.oep.judge;

import com.google.gson.Gson;
import com.ustc.oep.judge.beans.Request;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.ustc.oep.utils.Utils.readNBytes;

/**
 * @author YuJianhua
 * @create 2023-03-22 10:58
 */
public class JudgeTest {
    private static final String host = "192.168.190.128";
    private static final int port = 2345;

    public static void main(String[] args) {
        try {
            test(1, "c++");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test(int id, String language) throws IOException{
        Request request = new Request();
        request.setSubmitID(id);
        request.setProblemID(1);

        request.setTimeLimit(1000);
        request.setMemLimit(65536);
        Path path = new File("src/main/java/com/ustc/oep/judge/testcode/Main.cpp").toPath();
        String sourceCode = new String(Files.readAllBytes(path));
        System.out.println(sourceCode);

        byte[] codeBytes = sourceCode.getBytes();
        request.setCodeLength(codeBytes.length);

        request.setLanguage(language);
        request.setTestpointCount(10);
        request.setSourcecode(sourceCode);

        System.out.println(request);
        foo(request, new InetSocketAddress(host, port));
    }

    private static void foo(Request request, InetSocketAddress serverAddress) {
        Socket socket = new Socket();
        try {
            socket.connect(serverAddress);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(request.getSubmitID());
            out.writeInt(request.getProblemID());
            out.writeInt(request.getTimeLimit());
            out.writeInt(request.getMemLimit());
            out.writeInt(request.getCodeLength());
            out.writeInt(request.getTestpointCount());
            byte[] zeroFilledBytes = new byte[20];
            byte[] languageBytes = request.getLanguage().getBytes();
            System.arraycopy(languageBytes, 0, zeroFilledBytes, 0, languageBytes.length);
            out.write(zeroFilledBytes, 0, 20);
            out.write(request.getSourcecode().getBytes());
            out.flush();

            int dataLength = in.readInt();
            byte[] dataBuf = readNBytes(in, dataLength);
            socket.close();

            String responseData = new String(dataBuf);
            System.out.println(responseData);
//            Response resp = new Gson().fromJson(responseData, Response.class);
//            System.out.println(resp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
