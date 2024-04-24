import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/views/Login.vue"
import Table from "@/views/Table.vue"
import Todo from "@/views/Todo.vue"
import Home from "@/views/Home.vue"
import Personal from "@/views/Personal.vue"
import Error_403 from "@/views/error/403.vue"
import Error_404 from "@/views/error/404.vue"
import Layout from "@/layout"

Vue.use(Router)

const routes = [
    // 重定向默认至登录页面
    {
      path: '/',
      redirect: '/login'
    },

    {
      path: '/login',
      name: 'login',
      component: Login
    },

    {
      path: '/404',
      name: '404',
      component: Error_404
    },

    {
      path: '*',
      redirect: '/404'
    },

    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '/home',
          name: 'Home',
          component: Home
        },
        {
          path: '/table',
          name: 'Table',
          component: Table
        },
        {
          path: '/todo',
          name: 'Todo',
          component: Todo
        },

        {
          path: '/403',
          name: '403',
          component: Error_403
        },
        {
          path: '/personal',
          name: 'Personal',
          component: Personal
        }
      ]

    }

    
  ]
  
  const router = new Router({
    mode: 'history', // 使用 HTML5 history 模式，不会在 URL 中出现 #
    routes
  })

  
  export default router