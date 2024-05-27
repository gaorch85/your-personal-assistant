import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/views/Login.vue"
import Todo from "@/views/Todo/Todo.vue"
import Home from "@/views/Home.vue"
import Personal from "@/views/Personal.vue"
import Error_403 from "@/views/error/403.vue"
import Error_404 from "@/views/error/404.vue"
import Blog from "@/views/Blog/Blog.vue"
import BlogPost from '@/views/Blog/BlogPost.vue'
import BlogList from "@/views/Blog/BlogList.vue"
import CreateBlog from "@/views/Blog/CreateBlog.vue"
import EditBlog from '@/views/Blog/EditBlog.vue'
import Schedule from "@/views/Schedule/Schedule.vue"
import Curriculum from '@/views/Schedule/Curriculum.vue'
import AddCourse from '@/views/Schedule/AddCourse.vue'
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
          meta: { title: '未授权'}
        }
     ]
    },

    {
      path: '/',
      component: Layout,
      children: [
        {
          path: 'blog/details/:id',  // 使用占位符 :id
          name: 'BlogPost',
          component: BlogPost,
          meta: { title: '默认标题'}
        }
      ]
    },

    {
      path: '/',
      component: Layout,
      children: [
        {
          path: 'blog/edit/:id',  // 使用占位符 :id
          name: 'EditBlog',
          component: EditBlog,
          meta: { title: '编辑'}
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
          meta: { title: '待办事项', icon: 'el-icon-s-order' },
        }
     ]
    },

    {
      path: '/',
      component: Layout,
      show: true,
      children: [
        {
          path: '/blog',
          name: 'Blog',
          component: Blog,
          redirect: '/blog/list',
          meta: { title: '博客', icon: 'el-icon-s-order' },
          children: [
            {
              path: 'list',
              name: 'Blog-List',
              component: BlogList,
              meta: { title: '博客列表', icon: 'el-icon-s-order' }
            },
            
            {
              path: 'create',
              name: 'Blog-Create',
              component: CreateBlog,
              meta: { title: '创建博客', icon: 'el-icon-s-order' }
            },
            
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
          path: '/schedule',
          name: 'Schedule',
          component: Schedule,
          redirect: '/schedule/curriculum',
          meta: { title: '课表', icon: 'el-icon-s-order' },
          children: [
            {
              path: 'curriculum',
              name: 'Schedule-Curriculum',
              component: Curriculum,
              meta: { title: '我的课表', icon: 'el-icon-s-order' }
            },
            
            {
              path: 'addcourse',
              name: 'Schedule-addCourse',
              component: AddCourse,
              meta: { title: '课程管理', icon: 'el-icon-s-order' }
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


  // 路由守卫，设置动态标题
  router.beforeEach((to, from, next) => {
    if (to.name === 'BlogPost' && to.params.title) {
      to.meta.title = to.params.title;
    }
    next();
  });
  
  export default router