package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toCollection;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

    /*
     * Implement methods here
     */
    @Autowired
    MemberImporterImpl memberImporter;

    @Override
    protected MemberImporter getMemberImporter() {
        return memberImporter;
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
        TreeSet<Member> output=new TreeSet<>(Comparator.comparing(member->member.getId()));
        output.addAll(membersFromFile);
        return new ArrayList<>(output);
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        return validMembers.stream().collect(Collectors.groupingBy(Member::getState));
    }

}
