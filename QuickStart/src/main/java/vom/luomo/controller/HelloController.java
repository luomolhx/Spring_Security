package vom.luomo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Package: vom.luomo.controller
 * Description:
 *
 * @Author: luomo
 * @Create: 2023/6/8 - 14:07
 * @Version: v1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
