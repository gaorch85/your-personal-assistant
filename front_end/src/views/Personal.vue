<template>
    <div style="width: 50vh;">
        
        <el-descriptions class="margin-top" title="个人信息" :column="1"  border>   <!-- 里面加这句label-style:"width:150px" -->
            
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    用户名
                </template>
                {{info.username}}
            </el-descriptions-item>

            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    手机号
                </template>
                {{true_phoneNum}}
            </el-descriptions-item>

            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    邮箱
                </template>
                {{true_email}}
            </el-descriptions-item>


            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-tickets"></i>
                    会员
                </template>
                <el-tag v-if="info.vip" size="small" type="danger">VIP</el-tag>
                <el-tag v-else size="small" type="info">VIP</el-tag>
            </el-descriptions-item>

            <template slot="extra">
                <el-button type="primary" size="small" @click="changePassword()">修改密码</el-button>
            </template>
        </el-descriptions>
        
        <br>
        <el-button type="primary" size="small" @click="modifyPhoneNum()">修改手机号</el-button>
        
        
        <el-button type="primary" size="small" @click="modifyEmail()">修改邮箱</el-button>

        <br><br>
        <el-button type="danger"  @click="logout()">退出登录</el-button>

        <br><br>
        <el-button type="danger" @click="reallyDeactivateAccount()">注销账号</el-button>

        <br><br>
        <el-button type="success" @click="changeVip()">切换vip</el-button>

    </div>
</template>

<script>

  import { api_changePassword, api_deactivateAccount, api_getInfo, api_update } from '@/api/personal';
  import { removeToken } from "@/utils/auth"

    export default {
      mounted() {
        this.getInfo();
      },
      data() {
        return {
            info: {}
        }
      },
      computed: {
        true_phoneNum()
        {
            if(this.info.phoneNum==null)
                return "暂无";
            else
                return this.info.phoneNum;
        },
        true_email()
        {
            if(this.info.email==null)
                return "暂无";
            else
                return this.info.email;
        }
    },
      methods: {
        print()
        {
            console.log('hello');
        },

        getInfo()
        {

            api_getInfo()
            .then((response)=>
            {
                this.info = response.data.data.items;
            })
        },

        changePassword()
        {
            this.$prompt('请输入密码', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /.{6,}/,
            inputErrorMessage: '密码格式不正确'
            }).then(({ value }) => {
                api_changePassword({password: value})
                .then((response)=>
                {
                    this.getInfo();
                })
                this.$message({
                    type: 'success',
                    message: '修改成功！'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消修改'
                });       
            });
        },

        modifyPhoneNum()
        {
            this.$prompt('请输入手机号', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /^\d{11}$/,
            inputErrorMessage: '手机号格式不正确'
            }).then(({ value }) => {
                this.info.phoneNum = value; 
                api_update(this.info)
                .then((response)=>
                {
                    this.getInfo();
                })
                this.$message({
                    type: 'success',
                    message: '修改成功！'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消修改'
                });       
            });
        },

        modifyEmail()
        {
            this.$prompt('请输入邮箱', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
            inputErrorMessage: '邮箱格式不正确'
            }).then(({ value }) => {
                this.info.email = value; 
                api_update(this.info)
                .then((response)=>
                {
                    this.getInfo();
                })
                this.$message({
                    type: 'success',
                    message: '修改成功！'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消修改'
                });       
            });
        },

        logout()
        {
            removeToken();
            this.$router.push('/login')
        },

        reallyDeactivateAccount() {
            this.$confirm('此操作将注销账号, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'error',
            center: true
            }).then(() => {
                this.deactivateAccount();
                this.$message({
                    type: 'success',
                    message: '注销成功!'
                });

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },

        deactivateAccount()
        {
            api_deactivateAccount()
            .then((response)=>
            {
                if(this.$router.currentRoute.name != 'login')
                    this.$router.push('/login')
            })
        },

        changeVip()
        {
            this.info.vip = !this.info.vip; 
            api_update(this.info)
            .then((response)=>
            {
                this.getInfo();
            })
        }
      }
    }

  </script>