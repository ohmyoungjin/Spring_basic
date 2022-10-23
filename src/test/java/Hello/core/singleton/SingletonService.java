package Hello.core.singleton;

public class SingletonService {
//    1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
//    2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다. 이 메서드를
//    호출하면 항상 같은 인스턴스를 반환한다.
//    3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new
//    키워드로 객체 인스턴스가 생성되는 것을 막는다.
//    private으로 new 키워드를 막아두었다.
//    호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.
//    > 참고: 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장 단순하고
//    안전한 방법을 선택했다.
//    싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를
//    공유해서 효율적으로 사용할 수 있다. 하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.
//    싱글톤 패턴 문제점
//    싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
//    의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
//    클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
//    테스트하기 어렵다.
//    내부 속성을 변경하거나 초기화 하기 어렵다.
//    private 생성자로 자식 클래스를 만들기 어렵다.
//    결론적으로 유연성이 떨어진다.
//    안티패턴으로 불리기도 한다.


    //1. static 영역에 객체를 딱 1개만 생성해둔다. static은 class영역에 정의되기 때문에 가져다 쓸 수 있다.
    //이 파일이 실행 될 때 자신의 객체를 생성해서 instance에 참조로 넣어둔다
    //이 instance의 참조를 꺼낼 수 있는 방법은 밑에 2번에 있는 생성자에서만 꺼내서 변경할 수 있다
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }
    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    //다른 클래스 파일에서 객체 생성을 못하게 하는 것 ! 여기에서만 정의할 수 있다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
