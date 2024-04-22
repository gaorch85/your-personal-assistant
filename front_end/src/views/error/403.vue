<template>
    <div class="container">
      <div class="centered-icon">
        <i class="el-icon-lock" style="font-size: 200px;"></i>
      </div>
      <div class="text">抱歉！您无权限访问该页面</div>
      <div class="buttons">
        <el-button type="primary" @click="redirect('/login')">登录</el-button>
        <el-button type="primary" @click="redirect('/home')">返回首页</el-button>
      </div>
    </div>
  </template>
    
<script>

export default {

    methods: {
        redirect(path)
        {
            this.$router.push(path);
        }
    }
};

</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 60vh;
}

.centered-icon {
  margin-bottom: 20px;
}

.text {
  font-size: 24px;
  margin-bottom: 20px;
}

.buttons {
  display: flex;
  justify-content: center;
}

.buttons > .el-button {
  margin: 0 10px;
}
</style>
