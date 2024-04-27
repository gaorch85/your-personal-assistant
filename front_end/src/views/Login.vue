<template>
  <div class="login-container">
    <!-- 背景图 -->
    <img src="../assets/login-background.jpg" class="background-image" />
    <!-- 登录框 -->
    <div class="login-box">
      <el-tabs v-model="activateName"  @tab-click="handleClick">
        <el-tab-pane label="登录" name="login">
          <el-form ref="loginForm" :model="form" :rules="loginRules" class="login-form">
            <el-form-item prop="username">
              <el-input
                placeholder="请输入用户名"
                prefix-icon="el-icon-user"
                v-model="form.username">
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input 
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
              v-model="form.password" 
              show-password></el-input>
            </el-form-item>
            <div style="display: flex; justify-content: flex-start;">
              <el-link type="primary" @click="ResetPassword()">忘记密码?</el-link>
              <br>
            </div>
            <el-form-item style="text-align: center;">
              <el-button type="primary"  @click="login">登录</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="signup">
          <el-form ref="loginForm" :model="form" :rules="loginRules" class="login-form">
            <el-form-item prop="username">
              <el-input
                placeholder="请输入用户名"
                prefix-icon="el-icon-user"
                v-model="form.username">
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input 
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
              v-model="form.password" 
              show-password></el-input>
            </el-form-item>
            <el-form-item style="text-align: center;">
              <el-button type="primary"  @click="signup">注册</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>


    </div>
  </div>
</template>

<script>

// 导入 api 中的网络请求函数

import { api_login, api_signup } from "@/api/user"
import { setToken } from "@/utils/auth";


export default {
  data() {
    return {
      activateName:'login',
      form: {
        id: 0,
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    };
  },
  methods: {
    login() {
      // 实现登录逻辑
      console.log("登录");
      if(this.form.username.length < 3)
      {
        this.$message({
          message: '用户名不能少于3位!',
          type: 'warning'
        });
      }
      else if(this.form.password.length < 6)
      {
        this.$message({
          message: '密码不能少于6位!',
          type: 'warning'
        });
      }
      else
      {
        api_login(this.form)
          .then((response)=>
          {
            if(response.data.code == 20000)
            {
              this.$message({
                message: '登录成功！',
                type: 'success'
              });
              setToken(response.data.data.token)
              this.$router.push('/home')

              this.form.username = '';
              this.form.password = '';
            }
            else
            {
              this.$message({
                message: '用户名或密码错误！',
                type: 'error'
              });
            } 
          })
      }
      
    },
    signup() {
      // 实现注册逻辑
      console.log("注册！！！");
      if(this.form.username.length < 3)
      {
        this.$message({
          message: '用户名不能少于3位！',
          type: 'warning'
        });
      }
      else if(this.form.password.length < 6)
      {
        this.$message({
          message: '密码不能少于6位！',
          type: 'warning'
        });
      }
      else
      {
        api_signup(this.form)
          .then((response)=>
          {
            if(response.data.code == 20000)
            {
              this.$message({
                message: '注册成功！',
                type: 'success'
              });
              this.form.username = '';
              this.form.password = '';
            }
            else
            {
              this.$message({
                message: '用户名已存在，请选择其他用户名！',
                type: 'warning'
              });
              this.form.username = '';
              this.form.password = '';
            } 
          })
      }
    },

    handleClick()
    {
      this.form.username = '';
      this.form.password = '';
    },

    ResetPassword()
    {
      this.$message({
        message: '请您再仔细想一想！！！',
        type: 'success'
      });
    }

  }
};
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  /* overflow: hidden;  */
}

.login-container {
  position: relative;
  height: 100vh;
  background-color: #f0f2f5;
}

.background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.login-box {
  position: absolute;
  top: 50%;
  right: 180px;
  transform: translateY(-50%);
  padding: 20px;
  border: none;
  /* background-color: #fff; */
  background-color: rgba(255, 255, 255, 0);

}

.login-form {
  width: 300px;
}

</style>
