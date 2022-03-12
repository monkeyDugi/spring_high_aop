package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    // hello.aop.order 패키지의 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allorder() {
    } // pointcut 시그니처

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..)))")
    public void allService() {
    }


    @Pointcut("allorder() && allService()")
    public void orderAndService() {
    }
}
