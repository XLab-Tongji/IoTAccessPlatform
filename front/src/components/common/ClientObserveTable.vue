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
    </div>

</template>

<script>
import Stomp from 'stompjs'
import Bus from '../../eventBus'
export default {
  name: 'client-observe-table',
  data() {
    return {
      groupId: 0,
      tableData: null,
      tableIndex: {},
      client: null,
      clientSubscription: null,
      msgSubscription: null,
      websocketData: null
    }
  },
  props: ['groupId'],
  mounted() {
    this.connect()
  },
  methods: {
    jump() {
      this.$router.push('/')
    },
    onConnected(frame) {
      console.log('connected: ' + frame)
      this.$axios
        .get('http://127.0.0.1:8082/api/getSensors', {
          params: {
            id: this.groupId
          }
        })
        .then(res => {
          console.log(res.data)
          this.tableData = res.data
          var index = 0
          for (var i of this.tableData) {
            console.log(i['id'])
            this.tableIndex[i['id']] = index++
          }
          this.msgSubscription = this.client.subscribe('/topic/clientMg', this.getClientMsg)
        })
    },
    onFailed(frame) {
      console.log('failed: ' + frame)
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
    connect() {
      this.client = Stomp.client('ws://localhost:8082/my-websocket')
      this.client.connect({}, this.onConnected, this.onFailed)
    }
  }
}
</script>

<style>
</style>

