<template>
  <div id="app">
    <page-header></page-header>
    <!--<echart-test></echart-test>-->
    <router-view></router-view>
  </div>
</template>

<script>
import PageHeader from './components/PageHeader.vue'
import EchartTest from './components/test/EchartTest.vue'
export default {
  name: 'App',
  components: { PageHeader , EchartTest}
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>



z