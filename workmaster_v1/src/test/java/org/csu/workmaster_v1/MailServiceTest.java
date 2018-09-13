package org.csu.workmaster_v1;


import org.csu.workmaster_v1.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

        @Autowired
        private org.csu.workmaster_v1.service.MailService mailService;

        @Test
        public void testSimpleMail() throws Exception {
            mailService.sendSimpleMail("1025565665@qq.com","这是一封简单邮件","大家好，这是我的第一封邮件！");
        }
}
