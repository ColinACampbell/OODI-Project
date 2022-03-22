import { Bar } from 'vue-chartjs'

export default {
  extends: Bar,
  props: {
    chartdata: {
      type: Object,
      default: null
    },
  },
  data(){
      return{
        options: {
            responsive: true,
            legend: {
              display: false,
            },
            title: {
              display: true,
              text: "Chart"
            }
        }
      }
  },
  mounted () {
    this.renderChart(this.chartdata, this.options)
  }
}