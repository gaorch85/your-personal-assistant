<template>
  <div class="home-page">
    <header class="hero">
      <div class="hero-content">
        <h1>欢迎来到你的个人助手</h1>
        <p>在这里，您可以管理待办事项、阅读博客文章，并查看您的课表。</p>
        <div v-if="!isLogin">
          <router-link to="/login" class="btn btn-primary">登录</router-link>
          <router-link to="/signup" class="btn btn-secondary">注册</router-link>
        </div>
      </div>
    </header>

    <section class="features">
      <div class="feature" @click="navigateTo('/todo')">
        <i class="fas fa-tasks fa-3x"></i>
        <h2>待办事项</h2>
        <p>轻松管理您的待办事项，保持井井有条。</p>
      </div>
      <div class="feature" @click="navigateTo('/blog')">
        <i class="fas fa-blog fa-3x"></i>
        <h2>博客</h2>
        <p>创建和阅读最新的博客文章，获取新的知识和灵感。</p>
      </div>
      <div class="feature" @click="navigateTo('/schedule')">
        <i class="fas fa-calendar-alt fa-3x"></i>
        <h2>课表</h2>
        <p>查看和管理您的课表，合理安排您的学习时间。</p>
      </div>
    </section>

    <section class="about">
      <h2>关于我们</h2>
      <p>我们的应用旨在帮助您更好地管理时间和任务，提高效率。通过集成多种功能，我们希望为您提供一个一站式的管理平台。</p>
    </section>

    <footer class="footer">
      <p>&copy; 访问我们：https://github.com/gaorch85/Project </p>
    </footer>
  </div>
</template>

<script>
import { getToken } from "@/utils/auth";

export default {
  name: 'HomePage',
  data() {
    return {
      isLogin: false
    };
  },
  mounted() {
    this.isLogin = !!getToken();
  },
  methods: {
    navigateTo(route) {
      this.$router.push(route);
    }
  }
};
</script>

<style scoped>
.home-page {
  display: flex;
  flex-direction: column;
  height: 100%;
  font-family: 'Arial', sans-serif;
  color: #333;
  text-align: center;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

.hero {
  background: url('@/assets/home-background.jpg') no-repeat center center;
  background-size: cover;
  color: white;
  padding: 100px 20px;
  flex-shrink: 0;
}

.hero-content {
  background: rgba(0, 0, 0, 0.5);
  display: inline-block;
  padding: 20px 40px;
  border-radius: 8px;
}

.hero h1 {
  font-size: 3em;
  margin: 0;
}

.hero p {
  font-size: 1.2em;
  margin: 20px 0;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  margin: 10px;
  border-radius: 4px;
  text-decoration: none;
  font-weight: bold;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.features {
  display: flex;
  justify-content: space-around;
  padding: 40px 20px;
  flex-grow: 1;
  cursor: pointer;
}

.feature {
  text-align: center;
  width: 30%;
  transition: transform 0.2s;
}

.feature:hover {
  transform: scale(1.05);
}

.feature i {
  color: #007bff;
  margin-bottom: 10px;
}

.feature h2 {
  font-size: 1.5em;
  margin: 10px 0;
}

.feature p {
  font-size: 1em;
  color: #555;
}

.about {
  background-color: #f8f9fa;
  padding: 40px 20px;
  flex-shrink: 0;
}

.about h2 {
  font-size: 2em;
  margin-bottom: 20px;
}

.about p {
  font-size: 1.2em;
  color: #555;
}

.footer {
  background-color: #343a40;
  color: white;
  padding: 20px;
  font-size: 0.9em;
  flex-shrink: 0;
}
</style>
