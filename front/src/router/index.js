import Vue from 'vue'
import Router from 'vue-router'

import ShowGroup from '../components/ShowGroup.vue'
import ObservePage from '../components/ObservePage.vue'


Vue.use(Router)


const routers = [
  {
    path : '/',
    name : 'show-group',
    component : ShowGroup
  },{
    path : '/observe',
    name : 'observe-clients',
    component : ObservePage
  }
]


export default routers
