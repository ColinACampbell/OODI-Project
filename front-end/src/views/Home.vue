<template>
  <div class="container">
    <div>
      <div class="heading">
        <h1 class="title">Welcome, {{ name }}!</h1>
        <h3>{{ date }}</h3>
      </div>
      <h2>Asset Status Report</h2>
    </div>
    <div class="display-content">
      <div class="content">
        <div class="cards">
          <div class="card submitted" @click="viewAssets = assetsSubmitted">
            <div>{{ assetsSubmitted.length  }}</div>
            <p>{{ assetsSubmitted.length > 1 ? "ASSETS SUBMITTED" : "ASSET SUBMITTED" }}</p>
          </div>
          <div class="card pending" @click="viewAssets = assetsPending">
            <div>{{ assetsPending.length }}</div>
            <p>{{ assetsPending.length > 1 ? "ASSETS PENDING" : "ASSET PENDING" }}</p>
          </div>
          <div class="card approved" @click="viewAssets = assetsApproved">
            <div>{{ assetsApproved.length }}</div>
            <p>{{ assetsApproved.length > 1 ? "ASSETS APPROVED" : "ASSET APPROVED" }}</p>
          </div>
          <div class="card completed" @click="viewAssets = assetsCompleted">
            <div>{{ assetsCompleted.length }}</div>
            <p>{{ assetsCompleted.length > 1 ? "ASSETS COMPLETED" : "ASSET COMPLETED" }}</p>
          </div>
        </div>
        <div>
          <canvas id="myChart" width="200" height="200"></canvas>
        </div>
      </div>
      <div class="details">
        <ul>
          <li v-for="asset in viewAssets" :key="asset.title">
              {{ asset.title }}
              <ul>
                
                <li v-for="info in asset.history" :key="info.title">
                    <p>{{ info.status }}</p>
                    <p>Date Modified: {{ new Date(+info.time).toDateString() }}</p>
                    <p>Modified by: {{ info.updatedBy.name }}</p>
                </li>
              </ul>
          </li>
        </ul>
      </div>
    </div>
  </div> 
</template>

<script>
import store from '../store/store'
import AssetService from '../services/asset.service'

import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default {
  name: 'Home',
  data(){
    return {
      name: store.getters.userName,
      date: "",
      assetsSubmitted: [],
      assetsPending: [],
      assetsApproved: [],
      assetsCompleted: [],
      viewAssets: [],
    }
  },
  mounted(){
    const ctx = document.getElementById('myChart');
    ctx.fillStyle = 'transparent';
    new Chart(ctx, {
      type: 'bar',
      data: {
          labels: ['Submitted', 'Pending', 'Approved', 'Completed'],
          datasets: [{
              label: 'Asset Statuses',
              data: [3, 6, 8, 1],
              // data: [this.assetsSubmitted.length, this.assetsPending.length, this.assetsApproved.length,this.assetsCompleted.length, ],
              backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
              ],
              borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)',
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
      });
  },

  beforeMount(){
    let date = new Date()
    this.date = date.toDateString()
    AssetService.getAssets(store.getters.token)
      .then(res => {
        // console.log(res)
        if(res === "Failed to fetch"){
          this.$router.push("/dashboard")
        } else {
          [...res.sent, ...res.received].forEach(asset => {
            switch (asset.status) {
              case "SUBMITTED":
                this.assetsSubmitted.push(asset)
                break;
              case "APPROVED":
                this.assetsApproved.push(asset)
                break;
              case "PENDING":
                this.assetsPending.push(asset)
                break;
              case "COMPLETED":
                this.assetsCompleted.push(asset)
                break;
              default:
                break;
            }
          });

        }
      }).catch(err => console.log(err))

  }

 
}
</script>

<style scoped>
.container{
  padding: 10px 30px 10px 10px;
}

.cards{
  display: flex;
  flex-wrap: row;
}

.display-content{
  display: grid;
  grid-template-columns: 750px 1fr;
}

.content{
  border-right: 1px solid rgba(0, 0, 0, 0.05);
  padding-right: 10px ;
}

.card{
  border-radius: 10px;
  width: 200px;
  height: 150px;
  margin: 10px;
  text-align: center;
  padding: 5px 10px;
  cursor: pointer;
}

.card div:nth-child(1){
  font-size: 60px;
  text-align: center;
  height: 70px;
  margin-top: 10px;
}

.card p{
  font-size: 16px;
  font-weight: 900;
  width: 100px;
  text-align: center;
  margin: 10px auto;
}

.submitted{
  border: 1px solid #fee4e2;
  color: #f04438;
}

.submitted:hover{
  background: #fee4e2;
}

.pending{
  border: 1px solid#d1fadf;
  color: #12b76a;
}

.pending:hover{
  background: #d1fadf;
}

.approved{
  border: 1px solid #d1e9ff;
  color: #2e90fa;
}

.approved:hover{
  background: #d1e9ff;
}

.completed{
  border: 1px solid #fef0c7;
  color: #f79009;
}

.completed:hover{
  background: #fef0c7;
}

.heading{
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart{
  display: block;
}

</style>
