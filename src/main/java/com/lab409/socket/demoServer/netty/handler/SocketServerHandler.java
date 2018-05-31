package com.lab409.socket.demoServer.netty.handler;

import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.utils.ClientMsgInterpreter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Scope("singleton")
@Qualifier("socketServerHandler")
@ChannelHandler.Sharable
public class SocketServerHandler extends ChannelInboundHandlerAdapter {

    private Integer receive_num = 0;

    @Autowired
    @Qualifier("clientList_1")
    private List<Sensor> list1;

    @Autowired
    @Qualifier("clientList_2")
    private List<Sensor> list2;

    @Autowired
    @Qualifier("clientList_1")
    private List<Sensor> sensorList;

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void reverseSensorHolder() {
        if(sensorList == list1)
            sensorList = list2;
        else sensorList = list1;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String information = (String)msg;
        //Sensor sensor = ClientMsgInterpreter.getSensorFromString(information);
        //if(sensor != null) sensorList.add(sensor);
        //++receive_num;
        System.out.println(information + " " + receive_num);
        ctx.writeAndFlush("received\n\r");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}