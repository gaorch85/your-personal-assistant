<template>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>大语言模型</span>
      </div>
      <div style="margin-bottom: 20px;">
        <el-input
          v-model="inputString"
          placeholder="请输入你的问题..."
          clearable
          @keyup.enter.native="getPrediction">
        </el-input>
      </div>
      <el-button type="primary" @click="getPrediction" :loading="loading">获取回答</el-button>
      <div v-if="prediction" style="margin-top: 20px;">
        <div class="markdown-container">
          <MarkdownContainer :markdownContent="prediction"></MarkdownContainer>
        </div>
      </div>
    </el-card>
  </template>
  
  <script>
    import MarkdownContainer from "../Blog/MarkdownContainer.vue";
  import { api_predict } from "@/api/ai";

  export default {
    components:{
      MarkdownContainer
    },
    data() {
      return {
        inputString: '',
        prediction: '',
        loading: false
      };
    },
    methods: {
      async getPrediction() {
        if (!this.inputString) {
            this.$message.error('Please enter a string');
            return;
        }
        this.loading = true;
        api_predict({string: this.inputString})
          .then((response)=>{
            if(response.data.code == 20000)
            {
              this.$message({
                message: '获取成功！',
                type: 'success'
              });
              this.prediction = response.data.data.items;
            }
          })
          .finally(()=>{
            this.loading = false;
          })
      }
    }
  };
  </script>

<style scoped>
.box-card {
  max-width: 900px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
}


</style>
  