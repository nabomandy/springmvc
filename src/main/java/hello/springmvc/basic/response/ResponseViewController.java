package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    //권장하지는 않음, 불명확해서. 스프링이 관례적으로 도와주는 거. 컨트롤러의 이름=뷰의 이름일때.
    //컨트롤러의 경로의 이름이랑 리턴(뷰의 논리적이름)의 이름이랑 같을 때. 생략가능.
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
