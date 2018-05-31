import Vue from 'vue'
import Router from 'vue-router'
import ShowGroup from '../components/ShowGroup.vue'
Vue.use(Router)


const routers = [
  {
    path : '/',
    name : 'show-group',
    component : ShowGroup
  }
]


export default routers
