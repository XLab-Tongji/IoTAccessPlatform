<template>
  <div>
    <div v-for="g in groupData" :key="g" class="box-card">
      <button class="card-btn" @click="getAllGroup()">
        <el-card>
          <div slot="header" class="clearfix">
            <div class="groupName">{{g.name}}</div>
            <div class="groupDetail">
              <span>{{g.creater}}</span><br>
              <span>{{g.time}}</span>
            </div>
          </div>
          <div v-for="o in g.client" :key="o.type" class="text item" style="{float: left;}">
            {{o.type + ":" + o.num }}
          </div>
        </el-card>
      </button>
    </div>
    <el-button type="text" @click="addNewGroup()">add new Group</el-button>
    <group-form :dialogFormVisible="dialogFormVisible" v-on:cancelForm="dialogFormVisible=false"></group-form>

  </div>
</template>




<script>
import group from '../assets/data/group.json'
import GroupForm from './GroupForm.vue'

export default {
  name: 'show-group',
  data() {
    return {
      groupData: group,
      formLabelWidth: '120px',
      dialogFormVisible: false
    }
  },
  mounted() {},
  components: { GroupForm },
  methods: {
    c(object) {
      this.$axios
        .post('http://127.0.0.1:8082/api/postTest', {
          sender: 'client',
          content: 'hello server!'
        })
        .then(function(res) {
          console.log(res.data['sender'] + ' ' + res.data['content'])
        })
        .catch(function(res) {
          console.log(res)
        })
    },
    getAllGroup: function() {
      this.$axios
        .get('http://127.0.0.1:8082/api/getAllGroup')
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
    addNewGroup: function() {
      this.dialogFormVisible = true
    }
  }
}
</script>

<style>
.card-btn {
  background-color: white;
  border: 0ch;
}
.groupName {
  float: left;
  vertical-align: middle;
  font-size: 2em;
}
.groupDetail {
  float: left;
  padding-left: 20px;
  margin-left: 30px;
  text-align: left;
}

.text {
  font-size: 14px;
}

.item {
  left: px;
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}

.box-card {
  float: left;
  margin: 10px;
  width: 250px;
}
</style>