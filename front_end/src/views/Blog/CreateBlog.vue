<template>
    <div>
       <el-form>
          <el-form-item label="标题">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <mavon-editor v-model="form.content"/>
          </el-form-item>
       </el-form>
      <el-button @click="insert()">click</el-button>


    </div>
</template>
  
<script>

  import { mavonEditor } from 'mavon-editor';
  import 'mavon-editor/dist/css/index.css';
  import { api_insert } from '@/api/blog';
  export default({
    components:{
      mavonEditor 
    },
    data() 
    {
     return {
       form: {}

     }
    },

    methods: 
    {
      insert()
      {
        console.log(this.form.content);
        api_insert(this.form)
        .then((response)=>
        {
            console.log(response);
        })
      }  

    }
      
  })

</script>
  