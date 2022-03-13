package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록지

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        assertThatThrownBy(() -> {
            MemberServiceImpl memberServiceImpl =  (MemberServiceImpl) memberServiceProxy;
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 프록지

        // CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberServiceProxy;
    }
}
