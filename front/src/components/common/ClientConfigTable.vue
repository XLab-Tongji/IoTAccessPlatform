<template>
    <el-table :data="tableData" border>
        <el-table-column :label="clientType">
            <el-table-column prop="id" label="id" width="100">
            </el-table-column>
            <el-table-column label="msg">
                <template slot-scope="scope">
                    <el-input v-model="scope.row.msg" :disabled="!scope.row.active" placeholder="请输入需要发送的内容"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="interval" width="100">
                <template slot-scope="scope">
                    <el-input v-model="scope.row.interval" :disabled="!scope.row.active" placeholder="请输入间隔"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="update" width="90">
                <template slot-scope="scope">
                    <el-button size="mini" :disabled="!scope.row.active" @click="updateSendingMsg(scope.row.id, scope.row.msg, scope.row.interval)">update</el-button>
                </template>
            </el-table-column>
            <el-table-column label="switch" width="70">
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.active" @change="updateClientState(scope.row.id, scope.row.active)" on-color="#00A854" off-color="#F04134" on-value=true off-value=false on-text="on" off-text="off"></el-switch>
                </template>
            </el-table-column>
        </el-table-column>
    </el-table>
</template>

<script>
name: 'client-config-table'
export default {
  data() {
    return {}
  },
  props: ['tableData','clientType'],
  methods: {
    updateSendingMsg(id, msg, interval) {
      console.log('id: ' + id + ' msg: ' + msg + ' interval: ' + interval)
    },
    updateClientState(id, active) {
      console.log('id: ' + id + 'active: ' + active)
    }
  }
}
</script>