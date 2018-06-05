
<template>
    <div>
        <el-row>
            <el-col :span="12">
                <el-table :data="tableData.slice(0, tableData.length/2)">
                    <el-table-column align="center" type="expand" sytle="padding : 0">
                        <template slot-scope="scope" style="margin: 0">
                            <el-row>
                                <el-col :span="4">
                                    <span style="float : left">addr :</span>
                                </el-col>
                                <el-col :span="10">
                                    <span style="float : left">{{scope.row.host}} : {{scope.row.port}}</span>
                                </el-col>
                                <el-col :span="5">
                                    <span>msg num :</span>
                                </el-col>
                                <el-col :span="5">
                                    <span>100</span>
                                </el-col>
                            </el-row>
                            <el-row style="margin-bottom: 0">
                                <el-col :span="4">
                                    <span style="float : left">desc :</span>
                                </el-col>
                                <el-col :span="10">
                                    <span style="float : left">{{scope.row.descr}}</span>
                                </el-col>
                            </el-row>
                        </template>
                    </el-table-column>
                    <el-table-column sortable align="center" prop="id" label="id" width="60">
                    </el-table-column>
                    <el-table-column sortable align="center" prop="type" label="type" width="100">
                    </el-table-column>
                    <el-table-column sortable align="center" prop="state" label="state" width="100">
                    </el-table-column>
                    <el-table-column align="center" prop="latestMsg" label="latestMsg">
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="12">
                <el-table :data="tableData.slice(tableData.length/2, tableData.length)">
                    <el-table-column align="center" type="expand" sytle="padding : 0">
                        <template slot-scope="scope" style="margin: 0">
                            <el-row>
                                <el-col :span="4">
                                    <span style="float : left">addr :</span>
                                </el-col>
                                <el-col :span="10">
                                    <span style="float : left">{{scope.row.host}} : {{scope.row.port}}</span>
                                </el-col>
                                <el-col :span="5">
                                    <span>msg num :</span>
                                </el-col>
                                <el-col :span="5">
                                    <span>100</span>
                                </el-col>
                            </el-row>
                            <el-row style="margin-bottom: 0">
                                <el-col :span="4">
                                    <span style="float : left">desc :</span>
                                </el-col>
                                <el-col :span="10">
                                    <span style="float : left">{{scope.row.descr}}</span>
                                </el-col>
                            </el-row>
                        </template>
                    </el-table-column>
                    <el-table-column sortable align="center" prop="id" label="id" width="60">
                    </el-table-column>
                    <el-table-column sortable align="center" prop="type" label="type" width="100">
                    </el-table-column>
                    <el-table-column sortable align="center" prop="state" label="state" width="100">
                    </el-table-column>
                    <el-table-column align="center" prop="latestMsg" label="latestMsg">
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>

        <el-button type="text" @click="jump()">jump to home</el-button>
        <div>{{websocketData}}</div>
        <el-button type="text" @click="send()">send</el-button>
    </div>

</template>

<script>
import data from '../../assets/data/clients.json'
import Stomp from 'stompjs'

export default {
  name: 'observe-clients',
  data() {
    return {
      tableData: null,
      tableIndex: {},
      client: null,
      clientSubscription: null,
      msgSubscription: null,
      websocketData: null
    }
  },
  computed() {},
  mounted() {
    this.connect()
  },
  methods: {
    jump() {
      this.$router.push('/')
    },
    onConnected(frame) {
      console.log('connected: ' + frame)
      this.clientSubscription = this.client.subscribe(
        '/topic/client',
        this.getClient
      )
    },
    onFailed(frame) {
      console.log('failed: ' + frame)
    },
    getClient(frame) {
      this.tableData = JSON.parse(frame.body)
      console.log(frame)
      var index = 0
      for (var i of this.tableData) {
        console.log(i['id'])
        this.tableIndex[i['id']] = index++
      }
      this.clientSubscription.unsubscribe()
      this.msgSubscription = this.client.subscribe(
        '/topic/clientMsg',
        this.getClientMsg
      )
    },
    getClientMsg(frame) {
      var msgs = JSON.parse(frame.body)
      //console.log(frame)
      var i = 0
      for (var msg of msgs) {
        console.log(msg['id'] + ' ' + msg['msg'])
        console.log(this.tableIndex[msg['id']])
        this.tableData[this.tableIndex[msg['id']]]['latestMsg'] = msg['msg']
      }
    },
    send() {
      this.client.send('/application/clientSend', {}, null)
      this.tableData[0]['latestMsg'] = 'asdfasdfasdf'
    },
    connect() {
      this.client = Stomp.client('ws://localhost:8082/my-websocket')
      this.client.connect({}, this.onConnected, this.onFailed)
    }
  }
}
</script>

<style>
</style>

