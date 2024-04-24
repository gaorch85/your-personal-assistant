<template>
    <div>
        <el-container>
            <!-- 导航栏 -->
            <el-header style="height: 10vh;">
                <el-row>
                    <el-col :span="6"><div class="grid-content bg-purple">
                        <h1>这是导航栏</h1>
                    </div></el-col>

                    <el-col :span="2" :offset="16"><div class="grid-content bg-purple">
                        <br>
                        <el-button type="danger" round @click="logout()">logout</el-button>
                    </div></el-col>

                </el-row>
                <!-- 分隔线 -->
                <el-divider></el-divider>
            </el-header>
            
            
            <!-- 下方主体 -->
            <el-container style="height: 90vh;">
                <!-- 侧边栏 -->
                <el-aside width="200px">
                    <el-row class="tac">
                        <el-col>
                            <el-menu
                                default-active="Home"
                                class="el-menu-vertical-demo"
                                @select="handleSelect">

                                <el-menu-item index="Home">
                                    <i class="el-icon-menu"></i>    
                                    <span>首页</span>
                                </el-menu-item>

                                <el-menu-item index="Table">
                                    <i class="el-icon-menu"></i>    
                                    <span>表格</span>
                                </el-menu-item>

                                <el-menu-item index="Todo">
                                    <i class="el-icon-menu"></i>    
                                    <span>待办事项</span>
                                </el-menu-item>

                    


                                <el-menu-item index="403">
                                    <i class="el-icon-menu"></i>    
                                    <span>403</span>
                                </el-menu-item>

                                <el-menu-item index="Personal">
                                    <i class="el-icon-menu"></i>    
                                    <span>个人中心</span>
                                </el-menu-item>

                                <el-menu-item index="example" disabled>
                                    <i class="el-icon-menu"></i>    
                                    <span>示例</span>
                                </el-menu-item>

                                <el-menu-item index="example" disabled>
                                    <i class="el-icon-menu"></i>    
                                    <span>示例</span>
                                </el-menu-item>

                                <el-menu-item index="example" disabled>
                                    <i class="el-icon-menu"></i>    
                                    <span>示例</span>
                                </el-menu-item>

                            </el-menu>
                        </el-col>
                    </el-row>
                </el-aside>
                
                <!-- 主体 -->
                <el-main>

                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
        
    
  </template>
  
  <script>
    import { removeToken } from '@/utils/auth';
    export default {
        methods: {
        handleSelect(index)
        {
            console.log(this.$route.name);
            console.log(index);
            if(this.$route.name != index)
                this.$router.push({ name: index });
        },

        logout()
        {
            removeToken();
            this.$router.push('/login')
        }
        }
    };
  </script>


  <!-- 调整分割线的高度 -->
  <style>
    .el-divider--horizontal{
        margin: 8px;

    } 
  </style>

  