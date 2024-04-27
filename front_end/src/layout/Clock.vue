<template>
    <div class="clock">
      {{ currentDate }} {{ currentTime }}
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        currentDate: '',
        currentTime: ''
      };
    },
    mounted() {
      this.updateDateTime(); // 初始化日期和时间
      setInterval(this.updateDateTime, 1000); // 每秒更新日期和时间
    },
    methods: {
      updateDateTime() {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0');
        const day = String(now.getDate()).padStart(2, '0');
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        const seconds = String(now.getSeconds()).padStart(2, '0');
        this.currentDate = `${year}-${month}-${day}`;
        this.currentTime = `${hours}:${minutes}:${seconds}`;
      }
    }
  };
  </script>
  
  <style scoped>
  .clock {
    font-size: 24px;
    font-weight: bold;
  }
  </style>