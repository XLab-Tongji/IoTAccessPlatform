<template>
    <div>
        <el-table :data="tableData" border style="width: 70%">
            <el-table-column prop="id" label="id" width="50">
            </el-table-column>
            <el-table-column prop="type" label="type" width="100">
            </el-table-column>
            <el-table-column prop="descr" label="description">
            </el-table-column>
            <el-table-column prop="host" label="host" width="100">
            </el-table-column>
            <el-table-column prop="port" label="port" width="100">
            </el-table-column>
            <el-table-column prop="state" label="state" width="100">
            </el-table-column>
            <el-table-column prop="latestMsg" label="latestMsg">
            </el-table-column>
        </el-table>
        <el-button type="text" @click="jump()">jump to home</el-button>
        <div>{{websocketData}}</div>
        <el-button type="text" @click="send()">send</el-button>
    </div>

</template>

<script>
import data from '../assets/data/clients.json'
import Stomp from 'stompjs'

export default {
  data() {
    name: 'observe-clients'
    return {
      tableData: null,
      tableIndex: {},
      client: null,
      clientSubscription: null,
      msgSubscription: null,
      websocketData: null
    }
  },
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
      for (var i in this.tableData) {
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
      console.log(frame)
      var i = 0
      for (var msg of msgs) {
        console.log(msg['id'] + ' ' + msg['msg'])
        this.tableData[msg['id']]['latestMsg'] = msg['msg']
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
