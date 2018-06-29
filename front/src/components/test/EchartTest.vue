<template>
    <div id="app">
        <!--创建一个echarts的容器-->
        <div id="echartContainer" style="width:500px; height:500px"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'echart-test',
  data() {
    return {}
  },
  mounted() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echartContainer'))
    // 绘制图表
    myChart.setOption({
      title: { text: 'ECharts 入门示例' },
      tooltip: {},
      xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
      },
      yAxis: {},
      series: [
        {
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }
      ]
    })
  }
}
</script>
