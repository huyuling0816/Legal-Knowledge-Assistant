package com.lkms;

import com.lkms.po.LogicPo;
import com.lkms.service.LogicService;
import com.lkms.vo.LogicVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicTest {
    @Autowired
    LogicService logicService;

    @Test
    public void getLogicFromBodyTest(){
        String body="非法拘禁";
        List<LogicVo> vos=logicService.getLogicsFromBody(body);
        for(LogicVo vo: vos){
            System.out.println(vo.getBody());
            System.out.println(vo.getConsequence());
        }
    }
}
