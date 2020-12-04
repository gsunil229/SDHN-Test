package bqtest.service.impl;

import bqtest.service.Member;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberImporterImplTest {

    Logger logger = LoggerFactory.getLogger(MemberImporterImplTest.class);


    @Test
    void importMembers() throws IOException {
        MemberImporterImpl  memberImporter = new MemberImporterImpl();
        String workingDirectory = System.getProperty("user.dir");
        File file=new File(workingDirectory+"\\"+"Members.txt");
        assertTrue(file.exists());
        List<Member> listMembers= memberImporter.importMembers(file);
       assertTrue(listMembers.size()==108);
        listMembers.forEach(member ->{
            assertFalse(StringUtils.isEmpty(member.getId()));
            assertFalse(StringUtils.isEmpty(member.getAddress()));
            assertFalse(StringUtils.isEmpty(member.getCity()));
            assertFalse(StringUtils.isEmpty(member.getFirstName()));
            assertFalse(StringUtils.isEmpty(member.getLastName()));
            assertFalse(StringUtils.isEmpty(member.getZip()));
            assertFalse(StringUtils.isEmpty(member.getState()));
            assertTrue(member.getZip().length()<=5);
            assertTrue(member.getState().length()<=4);
            assertTrue(member.getCity().length()<=20);
            assertTrue(member.getAddress().length()<=30);
            assertTrue(member.getLastName().length()<=25);
            assertTrue(member.getFirstName().length()<=25);
            assertTrue(member.getId().length()<=12);
        });
    }
}