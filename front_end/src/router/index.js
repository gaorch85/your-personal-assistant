import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/views/Login.vue"
import Todo from "@/views/Todo/Todo.vue"
import TodoList from "@/views/Todo/TodoList.vue"
import ManageTopic from "@/views/Todo/ManageTopic.vue"
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
      name: 'Login',
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
          path: '/403',
          name: '403',
          component: Error_403,
        }
     ]
    },

    {
      path: '/',
      component: Layout,
      show: true,
      children: [
        {
          path: '/home',
          name: 'Home',
          component: Home,
          meta: { title: '首页', icon: 'el-icon-s-home' }
        }
     ]
    },

    {
      path: '/',
      component: Layout,
      show: true,
      children: [
        {
          path: '/todo',
          name: 'Todo',
          component: Todo,
          redirect: '/todo/list',
          meta: { title: '待办事项', icon: 'el-icon-s-order' },
          children: [
            {
              path: 'list',
              name: 'Todo-List',
              component: TodoList,
              meta: { title: '待办管理', icon: 'el-icon-s-order' }
            },
            {
              path: 'manage_topic',
              name: 'Todo-ManageTopic',
              component: ManageTopic,
              meta: { title: '添加主题', icon: 'el-icon-s-order' }
            }
          ]
        }
     ]
    },

    {
      path: '/',
      component: Layout,
      show: true,
      children: [
        {
          path: '/personal',
          name: 'Personal',
          component: Personal,
          meta: { title: '个人中心', icon: 'el-icon-s-custom' }
        }
      ]
    }

  ]
  
  const router = new Router({
    mode: 'history', // 使用 HTML5 history 模式，不会在 URL 中出现 #
    routes
  })

  
  export default router