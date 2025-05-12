package com.lkms;

import com.lkms.mapper.LawDocMapper;
import com.lkms.po.lawPo.LawDocPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBaseLinkTest {

    @Autowired
    private LawDocMapper lawDocMapper;

    @Test
    public void DataBaseLinkTest(){
        System.out.println("Test-Start");
        List<LawDocPo> lawDocPoList = lawDocMapper.selectList(null);
        lawDocPoList.forEach(System.out::println);
    }

    @Test
    @PostMapping("/save")
    public void DataBaseWriteTest(){
        LawDocPo lawDocPo = new LawDocPo();
        lawDocPo.setId(String.valueOf(2));
        lawDocMapper.insert(lawDocPo);
    }

}
