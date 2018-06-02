<template>
  <div>
    <div v-for="g in groupData" :key="g" class="box-card">
      <button class="card-btn" @click="useThisGroup(g.id)">
        <el-card>
          <div slot="header" class="clearfix">
            <div class="groupName">{{g.name}}</div>
            <div class="groupDetail">
              <span>{{g.createUser}}</span><br>

            </div>
          </div>
          <div v-for="o in g.groupDetails" :key="o.type" class="text item" style="{float: left;}">
            {{o.type + ":" + o.sensorNum}}
          </div>
        </el-card>
      </button>
    </div>
    <el-button plain type="prime" @click="addNewGroup()">add new Group</el-button>
    <group-form :dialogFormVisible="dialogFormVisible" v-on:cancelForm="dialogFormVisible=false"></group-form>

  </div>
</template>




<script>
//import group from '../assets/data/group.json'
import GroupForm from './GroupForm.vue'

export default {
  name: 'show-group',
  data() {
    return {
      groupData: [],
      formLabelWidth: '120px',
      dialogFormVisible: false
    }
  },
  mounted() {
    this.getAllGroup()
  },
  components: { GroupForm },
  methods: {
    getAllGroup: function() {
      this.$axios
        .get('http://127.0.0.1:8082/api/getAllGroup')
        .then(res => {
          this.groupData = res.data
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
    addNewGroup: function() {
      this.dialogFormVisible = true
    },
    useThisGroup : function(id) {
      this.$router.push("/observe");
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