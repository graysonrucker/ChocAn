package chocan.report;

import java.util.ArrayList;

import chocan.model.Member;

public class MemberReport {

    private Member member;
    private ArrayList<MemberServiceSummary> memberServiceList = new ArrayList<>();

    public MemberReport(Member member, ArrayList<MemberServiceSummary> memberServiceList){
        this.member = new Member(member);
        this.memberServiceList = new ArrayList<MemberServiceSummary>(memberServiceList);
    }

    public Member getMember(){
        return new Member(member);
    }

    public ArrayList<MemberServiceSummary> getMemberServiceList(){
        return new ArrayList<MemberServiceSummary>(memberServiceList);
    }

}
