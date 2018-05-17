package com.lab409.socket.demoServer.controller;

import com.lab409.socket.demoServer.model.UserEntity;
import com.lab409.socket.demoServer.enums.UserSexEnum;
import com.lab409.socket.demoServer.mapper.UserMapper;
import com.lab409.socket.demoServer.viewmodel.SocketClientViewModel;
import com.lab409.socket.demoServer.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
@RequestMapping("/test")
public class TestController {
    @Value("${com.huzehao.name}")
    String name;

    @Value("${com.huzehao.age}")
    int age;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/insert")
    public String insertTest() {
        userMapper.insert(new UserEntity("ab", "a123456", UserSexEnum.MAN));
        userMapper.insert(new UserEntity("ba", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new UserEntity("cb", "b123456", UserSexEnum.WOMAN));
        return "success";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return name + age;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        List<Sensor> sensorList = new ArrayList<>();
        for (Long i = new Long(0); i < 10; i++) {
            Sensor sensor = new Sensor
                    (i, "type_" + i, "127.0.0.1", "8080", "today has thunder", "online","made in china");
            sensorList.add(sensor);
        }
        modelAndView.addObject("sensorList", sensorList);
        return modelAndView;
    }

    @PostMapping("/refresh/{refreshed}")
    public void doRefresh(@PathVariable int refreshed) {
        this.refreshed = new Long(refreshed);
    }

    @GetMapping("/getChangedSocketClient")
    public @ResponseBody
    List<SocketClientViewModel> getChangedSocketClient() throws Exception {
        Thread.sleep(2000);
        List<SocketClientViewModel> list = new ArrayList<>();
        SocketClientViewModel model1 = new SocketClientViewModel
                (refreshed, "type_" + refreshed++, "127.0.0.2", "8081", "tomorrow has thunder", "offline", "made in china", 1);
        SocketClientViewModel model2 = new SocketClientViewModel
                (refreshed, "type_" + refreshed++, "127.0.0.2", "8081", "tomorrow has thunder", "offline", "made in china", 0);
        list.add(model1);
        list.add(model2);
        return list;
    }

    private Long refreshed = new Long(0);
}
