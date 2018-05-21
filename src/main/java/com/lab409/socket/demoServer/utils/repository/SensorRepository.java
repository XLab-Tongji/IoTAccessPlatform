package com.lab409.socket.demoServer.utils.repository;

import java.util.HashSet;
import java.util.Set;

public class SensorRepository {
    private Set<Integer> client_id_set = new HashSet<>();

    public boolean isNewClient(Integer id) {
        return !client_id_set.contains(id);
    }

}
