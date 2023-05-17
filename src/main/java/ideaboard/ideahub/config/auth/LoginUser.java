package ideaboard.ideahub.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 세션정보를 한번에 가져오는 어노테이션
 * Target : 어노테이션 사용 위치 - 파라미터 타입
 * Retention : 어노테이션이 어디에 적용되는지, 언제까지 어노테이션 소스가 유지 되는지 - 실행시점동안 유지
 * */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
