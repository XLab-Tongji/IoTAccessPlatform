<template>
    <div>
        <el-row :gutter="20" style="margin-top : 100px">
            <el-col :span="12">
                <div>
                    <el-table :data="temperatureData" border>
                        <el-table-column label="temperature">
                            <el-table-column prop="id" label="id" width="100">
                            </el-table-column>
                            <el-table-column label="msg">
                                <template slot-scope="scope">
                                    <el-input v-model="scope.row.msg" placeholder="请输入需要发送的内容"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column label="update" width="90">
                                <template slot-scope="scope">
                                    <el-button size="mini" :disabled="!scope.row.active" @click="updateSendingMsg(scope.$index, scope.row.msg)">update</el-button>
                                </template>
                            </el-table-column>
                            <el-table-column label="switch" width="70">
                                <template slot-scope="scope">
                                    <el-switch v-model="scope.row.active" @change="msgSwitch" on-color="#00A854" off-color="#F04134" on-value=true off-value=false on-text="on" off-text="off"></el-switch>
                                </template>
                            </el-table-column>
                        </el-table-column>

                    </el-table>
                </div>
            </el-col>

            <el-col :span="12">
                <div>
                    <el-table :data="humidityData" border>
                        <el-table-column label="humidity">
                            <el-table-column prop="id" label="id" width="100">
                            </el-table-column>
                            <el-table-column label="msg">
                                <template slot-scope="scope">
                                    <el-input v-model="scope.row.msg" placeholder="请输入需要发送的内容"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column label="update" width="90">
                                <template slot-scope="scope">
                                    <el-button size="mini" :disabled="!scope.row.active" @click="updateSendingMsg(scope.$index, scope.row.msg)">update</el-button>
                                </template>
                            </el-table-column>
                            <el-table-column label="switch" width="70">
                                <template slot-scope="scope">
                                    <el-switch v-model="scope.row.active" @change="msgSwitch" on-color="#00A854" off-color="#F04134" on-value=true off-value=false on-text="on" off-text="off"></el-switch>
                                </template>
                            </el-table-column>
                        </el-table-column>
                    </el-table>
                </div>
            </el-col>
        </el-row>

        <el-row :gutter="20">
            <el-col :span="12">
                <div>
                    <el-table :data="pressureData" border>
                        <el-table-column label="pressure">
                            <el-table-column prop="id" label="id" width="100">
                            </el-table-column>
                            <el-table-column label="msg">
                                <template slot-scope="scope">
                                    <el-input v-model="scope.row.msg" placeholder="请输入需要发送的内容"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column label="update" width="90">
                                <template slot-scope="scope">
                                    <el-button size="mini" :disabled="!scope.row.active" @click="updateSendingMsg(scope.$index, scope.row.msg)">update</el-button>
                                </template>
                            </el-table-column>
                            <el-table-column label="switch" width="70">
                                <template slot-scope="scope">
                                    <el-switch v-model="scope.row.active" @change="msgSwitch" on-color="#00A854" off-color="#F04134" on-value=true off-value=false on-text="on" off-text="off"></el-switch>
                                </template>
                            </el-table-column>
                        </el-table-column>

                    </el-table>
                </div>
            </el-col>
            <el-col :span="12">
                <div>
                    <el-table :data="thunderData" border>
                        <el-table-column label="tunder">
                            <el-table-column prop="id" label="id" width="100">
                            </el-table-column>
                            <el-table-column label="msg">
                                <template slot-scope="scope">
                                    <el-input v-model="scope.row.msg" placeholder="请输入需要发送的内容"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column label="update" width="90">
                                <template slot-scope="scope">
                                    <el-button size="mini" :disabled="!scope.row.active" @click="updateSendingMsg(scope.$index, scope.row.msg)">update</el-button>
                                </template>
                            </el-table-column>
                            <el-table-column label="switch" width="70">
                                <template slot-scope="scope">
                                    <el-switch v-model="scope.row.active" @change="msgSwitch" on-color="#00A854" off-color="#F04134" on-value=true off-value=false on-text="on" off-text="off"></el-switch>
                                </template>
                            </el-table-column>
                        </el-table-column>
                    </el-table>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
 
<script>
import client_groups from '../assets/data/client_groups.json'
export default {
  name: 'client-config',
  data() {
    return {
      temperatureData: client_groups['temperature'],
      thunderData: client_groups['thunder'],
      pressureData: client_groups['pressure'],
      humidityData: client_groups['humidity']
    }
  },
  methods: {
    updateSendingMsg(index, msg) {
      console.log('index : ' + index + ' msg: ' + msg)
    },
    msgSwitch(active) {
      console.log('active : ' + active)
    }
  }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
</style>
 