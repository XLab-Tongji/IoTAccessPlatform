import Vue from 'vue'
import Router from 'vue-router'

import ShowGroup from '../components/ShowGroup.vue'
import ClientConfig from '../components/ClientConfig.vue'

Vue.use(Router)

const routers = [
  {
    path: '/',
    name: 'show-group',
    component: ShowGroup
  },
  {
    path : '/config',
    name: 'client-config',
    component: ClientConfig
  }
]

export default routers
