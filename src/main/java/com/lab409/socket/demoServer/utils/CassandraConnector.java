package sse.tongji.thunder;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import sse.tongji.thunder.model.Terminal;
import sse.tongji.thunder.model.Terminal2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CassandraConnector {

    private Cluster cluster;

    private Session session;

    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

    /**
     *
     * @param keyspaceName 键空间名字
     * @param replicationStrategy 它只是把副本放在戒指中的策略。我们有简单策略（机架感知策略），旧网络拓扑策略（机架感知策略）和网络拓扑策略（数据中心共享策略）等策略。
     * @param replicationFactor 它是集群中将接收相同数据副本的计算机数
     */
    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor){
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
    public void checkKeySpace(){
        ResultSet resultSet = session.execute("SELECT * FROM system_schema.keyspaces;");
        System.out.println(resultSet.all());
    }

    public void insertData(data, prefix){       
        session.execute(data+prefix+");");
    }


    public static void main(String[] args){
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
        // 这是插入一条的样例
        terminalData = "'4#雷电分析仪',1.3,598,'正','2018-03-05 06:39:35','千安','微秒'";
        terminalPrefix = "INSERT INTO terminal (terminal_description, peak, lasting_time, polarity, date, unit1, unit2)"
               + " VALUES(";
        insertData(terminalData, terminalPrefix);

        // 这是另一个表的一条的样例
        terminal2Data = "'4#雷电分析仪', 2.1, '是', '正常', '闭合', 227.9, 220.4, 230.1, 49.3, 52.2, 33.2,"
                +"246.0, 60.0, 143.0, 7.1, 23.4";
        terminal2Prefix = "INSERT INTO terminal2 (terminal_description, resistance, earthing, SPD, kk, UA, UB, UC, IA, IB, IC,"
                +"IrA, IrB, IrC, humidity, temperature)"
                +" VALUES(";
        insertData(terminal2Data, terminal2Prefix);

        connector.close();
    }
}
