package Hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    //생성자 호출 => 의존 관계 주입 완료 후 callback => NetworkClient.afterPropertiesSet
    //connect() => bean close =>NetworkClient.destroy => disconnect()
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }
    //의존 관계 주입 후 호출이 된다

    //이 두 개 PostConstruct , @PredDestroy 이 기능을 제일 많이 사용한다 사용하기도 편하다
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    //빈이 종료 될 때 실행 됨
    @PreDestroy
    public void shutdown(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
