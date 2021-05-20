package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController //RestAPI의 그 Rest //문자를 있는 그대로 반환함. 여기선 리턴에 ok를 그냥 반환함
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
//@Slf4j 어노테이션 쓰면 자동으로 넣어주니 이거 빼도 된다.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

//        log.info("info log="+ name); //이렇게 사용하면 안됨.

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
