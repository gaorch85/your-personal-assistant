<template>
    <div class = "blog-form-container" >     
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>编辑博客</span>
            </div>
            <el-form :model="form" label-width="80px" class="blog-form">
            <el-form-item label="标题:">
                <el-input v-model="form.title" placeholder="请输入标题"></el-input>
            </el-form-item>
            <el-form-item label="内容:">
                <mavon-editor  v-model="form.content"></mavon-editor>
            </el-form-item>
            <el-form-item label="是否公开:">
                <el-switch
                v-model="form.isPublic"
                active-text="公开"
                inactive-text="私密">
                </el-switch>
            </el-form-item>
            <el-form-item class="form-footer">
                <el-button type="primary" @click="edit">修改</el-button>
            </el-form-item>
            </el-form>
        </el-card>
    </div>
  </template>
  
  <script>
  import { mavonEditor } from 'mavon-editor';
  import 'mavon-editor/dist/css/index.css';
  import { api_update, api_getPostById } from '@/api/blog';
  
  export default {
    components: {
      mavonEditor
    },
    mounted() {
      console.log(this.postId);
      this.getPost();
    },

    data() {
      return {
        postId: this.$route.params.id,
        form: {
          title: '',
          content: '',
          isPublic: true
        }
      };
    },
    methods: {
        getPost()
        {
            api_getPostById(this.postId)
            .then((response)=>
            {
                if(response.data.code == 20001)
                  if(this.$route.name != '404')
                    this.$router.push('/404');
                this.form = response.data.data.items;    
            });
        },
        edit() {
            console.log(this.form);
            if(this.form.title && this.form.content)
            {
            this.$confirm('确认修改博客?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error',
                    center: true
                    }).then(() => {
                    api_update(this.postId, this.form)
                        .then((response)=>
                        {
                        if(response.data.code == 20000)
                        {
                            this.$message.success('修改成功!');
                        }
                        else
                        {
                            this.$message.error('修改失败!');
                        }
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                });
            }
            else
            this.$message.error('标题或内容不能为空!');
        },
        goBack()
        {
            this.$router.push({ name: "Blog"});
            this.$router.push({ name: "BlogPost", params: this.postId });
        }
    }
  };
  </script>
  
  <style scoped>
  .blog-form-container {
    max-width: 1000px;
    margin: 20px auto;
  }
  
  .box-card {
    padding: 20px;
  }
  
  .blog-form {
    width: 100%;
  }
  
  .el-form-item {
    margin-bottom: 20px;
  }
  
  .el-input,
  .mavon-editor .ma-edit {
    width: 100%;
  }
  
  .form-footer {
    text-align: center;
  }
  
  
  </style>
  