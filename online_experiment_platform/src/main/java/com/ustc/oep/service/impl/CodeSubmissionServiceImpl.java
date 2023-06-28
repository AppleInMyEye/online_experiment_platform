package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ustc.oep.dto.CodeRequest;
import com.ustc.oep.entity.CodeSubmission;
import com.ustc.oep.mapper.CodeSubmissionMapper;
import com.ustc.oep.service.CodeSubmissionService;
import com.ustc.oep.entity.JudgeResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.ustc.oep.utils.Utils.readNBytes;

/**
 * @author YuJianhua
 * @create 2023-06-04 9:38
 */
@Service
public class CodeSubmissionServiceImpl extends ServiceImpl<CodeSubmissionMapper, CodeSubmission> implements CodeSubmissionService {
    @Value("${judge-server.host}")
    public String host;
    @Value("${judge-server.port}")
    public int port;

    /**
     * 向判题机发送代码，请求判题
     *
     * @param codeRequest
     * @return
     */
    @Override
    public JudgeResult codeSubmit(CodeRequest codeRequest) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port), 1000);
            //获取输入输出流
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            //发送数据
            //1表示请求判题
            out.writeInt(1);
            out.writeLong(codeRequest.getSubmitId());
            out.writeInt(codeRequest.getProblemId());
            out.writeInt(codeRequest.getTimeLimit());
            out.writeInt(codeRequest.getMemLimit());
            out.writeInt(codeRequest.getCodeLength());
            out.writeInt(codeRequest.getTestpointCount());
            byte[] zeroFilledBytes = new byte[20];
            byte[] languageBytes = codeRequest.getLanguage().getBytes();
            System.arraycopy(languageBytes, 0, zeroFilledBytes, 0, languageBytes.length);
            out.write(zeroFilledBytes, 0, 20);
            out.write(codeRequest.getSourcecode().getBytes());
            out.flush();

            //读取返回数据
            int dataLength = in.readInt();
            byte[] dataBuf = readNBytes(in, dataLength);
            socket.close();

            //将返回数据转换为JudgeResult对象
            String responseData = new String(dataBuf);
            System.out.println(responseData);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseData, JudgeResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getSubmitNum(int problemId, Long userId) {
        return baseMapper.getSubmitNum(problemId, userId);
    }

//    @Override
//    public void getCodeSubmissionId(CodeSubmission codeSubmission) {
//        QueryWrapper<CodeSubmission> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("problem_id", codeSubmission.getProblemId());
//        queryWrapper.eq("user_id", codeSubmission.getUserId());
//        queryWrapper.eq("submit_count", codeSubmission.getSubmitNum());
//
//
//    }
}
