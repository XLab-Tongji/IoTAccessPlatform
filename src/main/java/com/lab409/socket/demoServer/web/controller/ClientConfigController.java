package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.SensorMapper;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.utils.ClientUtil;
import com.lab409.socket.demoServer.utils.client.SensorClientThread;
import com.lab409.socket.old.socket.client.ClientReceiveRunnable;
import com.lab409.socket.old.socket.client.ClientSendRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RestController
@RequestMapping("/config")
public class ClientConfigController {

    @Autowired
    SensorMapper mapper;

    @Autowired
    ClientUtil util;



    @GetMapping("/create")
    public @ResponseBody String clientGroup() throws Exception {
        List<Sensor> sensors = mapper.getManyByGroupId(Long.valueOf(1));
        return "asdfasdf";
    }

    @GetMapping("/destroy")
    public @ResponseBody String destroyClients() {

        return "destroy";
    }

}
