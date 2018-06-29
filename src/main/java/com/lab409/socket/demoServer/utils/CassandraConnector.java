package com.lab409.socket.demoServer.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


public class CassandraConnector {

    private Cluster cluster;

    private static Session session;

    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

    /**
     * @param keyspaceName        键空间名字
     * @param replicationStrategy 它只是把副本放在戒指中的策略。我们有简单策略（机架感知策略），旧网络拓扑策略（机架感知策略）和网络拓扑策略（数据中心共享策略）等策略。
     * @param replicationFactor   它是集群中将接收相同数据副本的计算机数
     */
    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
        session.execute(" USE " + keyspaceName + ";");
    }

    /**
     * 查看现有的键空间
     */
    public void checkKeySpace() {
        ResultSet resultSet = session.execute("SELECT * FROM system_schema.keyspaces;");
        System.out.println(resultSet.all());
    }

    public static void insertData(String data, String prefix) {
        //session.execute(prefix + data + ");");
        session.execute("INSERT INTO terminal (terminal_description, peak, lasting_time, polarity, date, unit1, unit2) VALUES('9#雷电分析仪',2.5,933,'正','2018-06-28 06:57:21','千安', '微秒')");
    }


    public static void main(String[] args) {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
        // 这是插入一条的样例
        String terminalData = "'胡泽豪的雷电分析仪',1.3,598,'正','2018-06-21 06:39:35','千安','微秒'";
        String terminalPrefix = "INSERT INTO terminal (terminal_description, peak, lasting_time, polarity, date, unit1, unit2)"
                + " VALUES(";
        insertData(terminalData, terminalPrefix);
        connector.close();
    }
}
