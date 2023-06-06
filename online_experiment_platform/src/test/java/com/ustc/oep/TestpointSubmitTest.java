package com.ustc.oep;

import com.ustc.oep.entity.TestPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author YuJianhua
 * @create 2023-06-02 10:02
 */
@SpringBootTest
public class TestpointSubmitTest {
    @Value("${judge-server.host}")
    private String host;
    @Value("${judge-server.port}")
    private int port;

    @Test
    public void testPointSubmit(){
        Socket socket = new Socket();
        try {
            TestPoint testPoint = new TestPoint();
            testPoint.setProblemId(2);
            testPoint.setTestPointInput("1 2");
            testPoint.setTestPointOutput("3");

            socket.connect(new InetSocketAddress(host,port));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //当传输第一个值为2时，表示为测试点提交
            out.writeInt((int)2);
            //problemId
            out.writeInt(testPoint.getProblemId());
            //testPointNum
            out.writeInt(4);
            //testPointInputLength
            out.writeInt(testPoint.getTestPointInput().length());
            //testPointOutputLength
            out.writeInt(testPoint.getTestPointOutput().length());
            //testPointInput
            out.write(testPoint.getTestPointInput().getBytes());
            //testPointOutput
            out.write(testPoint.getTestPointOutput().getBytes());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
