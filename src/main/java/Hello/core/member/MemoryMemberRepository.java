package Hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{



    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        System.out.println("111");
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        System.out.println("3333333333");
        return store.get(memberId);
    }
}
