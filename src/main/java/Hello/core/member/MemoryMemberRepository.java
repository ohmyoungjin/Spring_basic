package Hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {

    }

    @Override
    public Member findByid(Long memberId) {
        return null;
    }
}
