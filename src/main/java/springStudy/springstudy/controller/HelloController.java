package springStudy.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 여기서 get은 http메서드의 get이다.
    public String hello(Model model){
        model.addAttribute("data", "Hi!!");
        return "hello"; // teamplates의 hello.html에 가서 model을 넘겨줘라!
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){ // Model객체를 활용해 뷰단에 렌더링할 것을 보내주는 것
        model.addAttribute("name", name);
        return "hello-template"; // 웹브라우저 url에서 get방식으로 파라미터갑을 전달해준다 . ex. localhost:8080/hello-mvc?name=spring 그러면 해당 파라미터가 컨트롤러에 전달이되고 그 갚이 렌더링 되어 웹브라우저에 표시되는 것.
    }

    @GetMapping("hello-string")
    @ResponseBody // 이거는 뭐냐? http에서 헤더부와 바디부에서 바디부에 이 데이터를 내가 직접 넣어주겠다(리턴값)
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // 이것이 api의 방식.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 리턴값으로 객체가 들어오면 default가 josn형태로 데이터를 넘기는 것이다.

    } // 이런식으로 웹에 접속을 하면 json의 형태로 데이터가 출력된다.



    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    } // 자바빈 표준방식 : private로 변수를 선언하고 메소드를 통해 해당 변수에 접근을 하는 것 (프로퍼티 방식이라고도 부름)
}



