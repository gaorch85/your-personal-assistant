<template>
    <div>
        <el-container>
            <!-- 导航栏 -->
            <el-header style="height: 7vh;">
                <el-row>
                    <el-col :span="3"><div class="grid-content bg-purple">
                        <h2>你的个人助手</h2>
                    </div></el-col>

                    <el-col :span="6"><div class="grid-content bg-purple">
                        <br>
                        <clock></clock>
                    </div></el-col>

                    

                    <el-col :span="2" :offset="13"><div class="grid-content bg-purple">
                        <br>
                        <el-button type="danger" round @click="logout()">logout</el-button>
                    </div></el-col>

                </el-row>
            </el-header>
            <!-- 分隔线 -->
            <el-divider></el-divider> 
            
            <!-- 下方主体 -->
            <el-container style="height: 92vh;">
                <!-- 侧边栏 -->
                <el-aside width="200px">
                    <el-row class="tac">
                        <el-col>
                            <Sidebar :routes="routes"></Sidebar>
                        </el-col>
                    </el-row>
                </el-aside>
                
                <!-- 主体 -->
                <el-main class="custom-main">
                    <Tabs></Tabs>
                    <div class="really-main">
                        <router-view></router-view>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
        
    
  </template>
  
  <script>
    import { removeToken } from '@/utils/auth';
    import Sidebar from './Sidebar.vue';
    import Clock from './Clock.vue';
    import Tabs from './Tab.vue';
    export default {
        components: {
            Sidebar,
            Clock,
            Tabs
        },
        computed: {
            routes() {
                const sidebarRoutes = [];
                this.$router.options.routes.forEach(route => {
                    if(route.show)
                    {
                        const curObject = route.children[0];
                        if(curObject.hasOwnProperty('children'))
                        {
                            const curChildren = [];
                            curObject.children.forEach(child => {
                                curChildren.push(
                                    {
                                        name: child.name,
                                        meta: child.meta
                                    }
                                )
                            });
                            sidebarRoutes.push(
                            {
                                name: curObject.name,
                                meta: curObject.meta,
                                children: curChildren
                            })
                        }
                        else
                        {
                            sidebarRoutes.push(
                            {
                                name: curObject.name,
                                meta: curObject.meta
                            })
                        }
                    }
                });
                return sidebarRoutes;
            }
        },
        methods: {
    
            logout()
            {
                removeToken();
                this.$router.push('/login')
            }
        }
    };
  </script>


  
  <style>

    /* 覆盖 el-main 默认 padding */
    .custom-main {
    padding: 0 !important;
    }   
    /* 调整分割线的高度 */
    .el-divider--horizontal{
        margin: 0px;

    } 
    .really-main{
        padding-left: 20px;
        padding-right: 20px;
    }
  </style>



  