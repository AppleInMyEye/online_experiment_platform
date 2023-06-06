package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.entity.TestPoint;
import com.ustc.oep.mapper.TestPointMapper;
import com.ustc.oep.service.TestPointService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author YuJianhua
 * @create 2023-06-01 18:34
 */
@Service
public class TestPointServiceImpl extends ServiceImpl<TestPointMapper, TestPoint> implements TestPointService {

    @Value("${judge-server.host}")
    private String host;
    @Value("${judge-server.port}")
    private int port;


    @Override
    public void testPointSubmit(TestPoint testPoint, int TestPointNum) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host,port));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //当传输第一个值为2时，表示为测试点提交
            out.writeInt(2);
            //problemId
            out.writeInt(testPoint.getProblemId());
            //testPointNum
            out.writeInt(TestPointNum);
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
