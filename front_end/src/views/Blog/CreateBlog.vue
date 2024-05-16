<template>
  <div class="blog-form-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>创建博客</span>
      </div>
      <el-form :model="form" label-width="80px" class="blog-form">
        <el-form-item label="标题">
          <el-input v-model="form.title" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <mavon-editor  v-model="form.content"></mavon-editor>
        </el-form-item>
        <el-form-item class="form-footer">
          <el-button type="primary" @click="insert">创建</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import { api_insert } from '@/api/blog';

export default {
  components: {
    mavonEditor
  },
  data() {
    return {
      form: {
        title: '',
        content: ''
      }
    };
  },
  methods: {
    insert() {
      console.log(this.form);
      if(this.form.title && this.form.content)
      {
        this.$confirm('确认创建博客?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'error',
              center: true
              }).then(() => {
                api_insert(this.form)
                  .then((response)=>
                  {
                    if(response.data.code = 40000)
                    {
                      this.$message.success('创建成功!');
                      this.form = {
                              title: '',
                              content: ''
                            };
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
