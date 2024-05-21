<template>
  <div>
    <el-button type="primary" icon="el-icon-refresh-right" circle @click="refresh()"></el-button>
    <el-table
      :data="data"
      height="550"
      style="width: 100%">
      <!-- 课程名称  -->
      <el-table-column
        prop="name"
        label="课程名称"
        width="300">
      </el-table-column>
      <!-- 删除按钮  -->
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" circle @click="deleteByID(scope.row.id)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 插入 -->
    <br>
    <el-button type="primary" icon="el-icon-plus" circle @click="addCousrse()"></el-button>

  </div>
</template>

<script>
import { api_addCourse, api_deleteCourse, api_getCourses} from '@/api/curriculum';

export default {
    mounted() {
      this.getAll();
    },
    data() {
      return {
        data: [],

        
      }
    },
    methods: {
        getAll()
        {
            api_getCourses()
            .then((response)=>
            {
                this.data = response.data.data.items;
            })
        },

        refresh()
        {
            this.getAll();
        },

        deleteByID(id)
        {
            api_deleteCourse(id)
            .then((response)=>
            {
                this.getAll();
            })
        },

        addCousrse()
        {
            this.$prompt('请输入课程名', '提示', {
                confirmButtonText: '添加',
                cancelButtonText: '取消'})
            .then(({ value }) => {
                const curCourse = {
                    id: 0,
                    name: value
                };
                api_addCourse(curCourse)
                .then((response)=>
                {
                    if(response.data.code == 20001)
                        this.$message({
                        type: 'danger',
                        message: '课程已存在！'
                        });      
                    else
                        this.getAll();
                });
            })
            .catch(() => 
            {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });  
            });
        }
    
    
    }
}



</script>
