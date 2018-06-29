package com.lab409.socket.demoServer.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RemoteConfig {
    @Value("${remote.cassadra.host}")
    private String host;

    @Value("${remote.cassadra.port}")
    private Integer port;

    /*@Bean
    @Qualifier("cassadra_session")
    @Scope("singleton")
    public Session session() {
        Cluster.Builder b = Cluster.builder().addContactPoint(host);
        if (port != null) {
            b.withPort(port);
        }
        return b.build().connect();
    }*/
}
