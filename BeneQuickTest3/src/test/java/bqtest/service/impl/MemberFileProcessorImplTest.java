package bqtest.service.impl;

import bqtest.service.Member;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MemberFileProcessorImplTest {

    Logger logger = LoggerFactory.getLogger(MemberFileProcessorImplTest.class);
    @Test
    void getNonDuplicateMembers() throws IOException {
        MemberImporterImpl  memberImporter = new MemberImporterImpl();
        MemberFileProcessorImpl memberFileProcessor=new MemberFileProcessorImpl();
       String workingDirectory = System.getProperty("user.dir");
        File file=new File(workingDirectory+"\\"+"Members.txt");
        assertTrue(file.exists());
        List<Member> listMembers= memberImporter.importMembers(file);
        List<Member> filteredList= memberFileProcessor.getNonDuplicateMembers(listMembers);
        Map<String,Long> idCountmap= filteredList.stream().collect(Collectors.groupingBy(Member::getId,Collectors.counting()));
        idCountmap.forEach((key, value) -> {
            assertTrue(value==1);
        });
    }
}