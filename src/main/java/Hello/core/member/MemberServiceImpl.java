package Hello.core.member;

//정적인 클래스 다이어그램을 나타내는 파일
public class MemberServiceImpl implements  MemberService{
    //DI개념이 적용되지 않은 부분 이렇게 service쪽에서 어떤 구현체를 쓸 것인가를 정해줘야 함 .. 이건 SRP원칙에 위반된다.
    //private MemberRepository memberRepository = new MemoryMemberRepository();

    //DI개념이 적용된 부분
        private final MemberRepository memberRepository ;
    //생성자 주입
    //서비스 인터페이스의 구현체가 서비스 객체를 반환한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도 싱글톤을 보장해주는지 확인용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
