package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberImporter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {

    Function<String,String> getMemeberId = input-> input.substring(0,12).trim();
    Function<String,String> getMemeberLastName = input-> input.substring(12,37).trim();
    Function<String,String> getMemeberFirstName = input-> input.substring(37,62).trim();
    Function<String,String> getMemeberAddress = input-> input.substring(62,92).trim();
    Function<String,String> getMemeberCity = input-> input.substring(92,112).trim();
    Function<String,String> getMemeberState = input-> input.substring(112,116).trim();
    Function<String,String> getMemeberZip = input-> input.substring(116,121).trim();

    public List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                    Member member = new Member();
                    member.setId(getMemeberId.apply(line));
                    member.setAddress(getMemeberAddress.apply(line));
                    member.setCity(getMemeberCity.apply(line));
                    member.setFirstName(getMemeberFirstName.apply(line));
                    member.setLastName(getMemeberLastName.apply(line));
                    member.setState(getMemeberState.apply(line));
                    member.setZip(getMemeberZip.apply(line));
                    return member;
                }).collect(Collectors.toList());
    }

}
