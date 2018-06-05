<template>
    <div>
        <el-tabs style="margin-top:80px" type="card" v-model="tapActiveName" @tap-click="handleTapClick">
            <el-tab-pane label="temperature" name="temperature">
                <div>
                    <el-row :gutter="10">
                        <el-col :span="12">
                            <client-config-table :tableData="temperatureData.slice(0,temperatureData.length/2)" :clientType="temperature">
                            </client-config-table>
                        </el-col>
                        <el-col :span="12">
                            <client-config-table :table-data="temperatureData.slice(temperatureData.length/2,temperatureData.length)">
                            </client-config-table>
                        </el-col>
                    </el-row>
                </div>
            </el-tab-pane>

            <el-tab-pane label="humidity" name="humidity">
                <div>
                    <el-row :gutter="10">
                        <el-col :span="12">
                            <client-config-table :table-data="humidityData.slice(0,humidityData.length/2)">
                            </client-config-table>
                        </el-col>
                        <el-col :span="12">
                            <client-config-table :table-data="humidityData.slice(humidityData.length/2, humidityData.length)">
                            </client-config-table>
                        </el-col>
                    </el-row>
                </div>
            </el-tab-pane>
            <el-tab-pane label="pressure" name="pressure">
                <div>
                    <el-row :gutter="10">
                        <el-col :span="12">
                            <client-config-table :table-data="pressureData.slice(0, pressureData.length/2)">
                            </client-config-table>
                        </el-col>
                        <el-col :span="12">
                            <client-config-table :table-data="pressureData.slice(pressureData.length/2, pressureData.length)">
                            </client-config-table>
                        </el-col>
                    </el-row>
                </div>
            </el-tab-pane>
            <el-tab-pane label="thunder" name="thunder">
                <div>
                    <el-row :gutter="10">
                        <el-col :span="12">
                            <client-config-table :table-data="thunderData.slice(0,thunderData.length/2)">
                            </client-config-table>
                        </el-col>
                        <el-col :span="12">
                            <client-config-table :table-data="thunderData.slice(thunderData.length/2,thunderData.length)">
                            </client-config-table>
                        </el-col>
                    </el-row>
                </div>
            </el-tab-pane>
            <el-tab-pane label="observe" name="observe">
                <observe-page></observe-page>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
 
<script>
import client_groups from '../assets/data/client_groups.json'
import ClientConfigTable from './common/ClientConfigTable.vue'
import ObservePage from './common/ObservePage.vue'
export default {
  name: 'client-config',
  data() {
    return {
      temperatureData: client_groups['temperature'],
      thunderData: client_groups['thunder'],
      pressureData: client_groups['pressure'],
      humidityData: client_groups['humidity'],
      tapActiveName: 'temperature',
      activeName: 'second'
    }
  },
  components: { ClientConfigTable, ObservePage },
  methods: {
    handleTapClick(tap, event) {},
    handleClick(tab, event) {
      console.log(tab, event)
    },
    updateSendingMsg(index, msg) {
      console.log('index : ' + index + ' msg: ' + msg)
    },
    msgSwitch(active) {
      console.log('active : ' + active)
    },
    goToObservePage() {
        this.$router.push("/observe")
    }
  }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
</style>
 