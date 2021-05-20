package hello.springmvc.basic.request;

import hello.springmvc.basic.requestmapping.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}", "age=", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //ok라는 문자를 http응답메시지로 콱 박아버린다. 뷰 조회 x
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    //변수명과 값이 일치하면 이렇게 생략가능
    @ResponseBody //ok라는 문자를 http응답메시지로 콱 박아버린다. 뷰 조회 x
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //변수명(username)과 파라미터의 네임(username)이 같으면 이렇게 생략가능하다.
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
    @ResponseBody //ok라는 문자를 http응답메시지로 콱 박아버린다. 뷰 조회 x
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //ok라는 문자를 http응답메시지로 콱 박아버린다. 뷰 조회 x
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        //int는 기본형이기 떄문에 null 이 들어갈 수 없다. 0이라도 들어가야 한다.
        //Integer 는 객체형이라 null이 들어갈 수 있다.

        //이때 username만 주면 500에러가 난다. 스펙대로보냈지만 int age 가 없으면 null이 들어간다.
        //근데 자바에서 기본형 int는 null을 넣을 수 없다. 그래서 쓰려면 Integer로 바꿔줘야 한다. Integer는 객체형이라 null이 들어갈 수 있따.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //ok라는 문자를 http응답메시지로 콱 박아버린다. 뷰 조회 x
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //defaultValue가 들어가면 required 얘가 필요가 없다. 값이 있든 없든 들어가기 때문이다. 빈문자까지 다 처리해준다.
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //ok라는 문자를 http응답메시지로 콱 박아버린다. 뷰 조회 x
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);//@Data가 투스트링을 만들어주니까 이것도 사용가능하다.

        return "ok";
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    ////@ModelAttribute 생략가능
    public String modelAttributeV2(HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);//@Data가 투스트링을 만들어주니까 이것도 사용가능하다.

        return "ok";
    }

}
