<template>
    <div>
      <el-table
        :data="tableData"
        style="width: 100%">
        <!-- 姓名列  -->
        <el-table-column
          prop="topic"
          label="主题"
          width="100">
        </el-table-column>
        <!-- 性别列  -->
        <el-table-column
          prop="details"
          label="描述"
          width="300">
        </el-table-column>
        <!-- 生日列  -->
        <el-table-column
          prop="deadline"
          label="截止日期"
          width="250">
        </el-table-column>
        <!-- 地址列  -->
        <el-table-column
          label="状态"
          width="100">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status" size="small" type="success">已完成</el-tag>
            <el-tag v-else size="small" type="danger">未完成</el-tag>
          </template>
        </el-table-column>
        <!-- 按钮列  -->
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-check" circle @click="update(scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" circle @click="deleteByID(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 插入 -->
      <br>
      <el-button type="primary" icon="el-icon-plus" circle @click="insertEmployee()"></el-button>

      <br>
      <el-button type="primary" icon="el-icon-plus" circle @click="quick_insertEmployee()"></el-button>

      
  
      <!-- 插入更新对话框 -->
      <el-dialog
        title="请输入"
        :visible.sync="dialogVisible"
        width="450px">
        <template>
          <!-- 表单 -->
          <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="主题">
                <el-col :span="16">
                  <el-input v-model="form.topic"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="详细信息">
                <el-col :span="16">
                  <el-input v-model="form.details"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="截止日期">
                  <el-col :span="16">
                    <el-date-picker type="datetime" placeholder="选择日期时间" 
                     v-model="form.deadline" value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                  </el-col>
              </el-form-item>
              <el-form-item>
                <el-col :span="16">
                  <el-button type="primary" @click="onSubmit()">添加</el-button>
                  <el-button @click="cancel()">取消</el-button>
                </el-col>
                  
              </el-form-item>
          </el-form>
        </template>
      </el-dialog>

      

    </div>  
  </template>
  
  
  <script>

  import { api_delete, api_getAll, api_insert, api_update } from '@/api/todo';


    export default {
      mounted() {
        this.getAll();
      },
      data() {
        return {
          dialogVisible: false,
          tableData: [],
          form: {
            id: 0,
            topic: '',
            details: '',
            deadline: '',
            status: 0,
          }
        }
      },
      methods: {
        getAll() 
        {
          console.log("查询所有数据")
          api_getAll()
            .then((response)=>
            {
              this.tableData = response.data.data.items
            })
        },

        insertEmployee()
        {
          console.log("插入数据");
          this.button_name = "立即插入";
          this.dialogVisible = true;
        },

        onSubmit() 
        {
            console.log(this.form)
            api_insert(this.form)
              .then((response)=>
              {
                this.form = {};
                this.dialogVisible = false;
                this.getAll();
            })
        },

        cancel()
        {
          this.form = {};
          this.dialogVisible = false;
        },

        update(row)
        {
            const newRow = { ...row }; // 创建一个新对象，拷贝原始数据, 避免直接修改原始数据
            if(row.status == false)
            {
                row.status = true;
                api_update(row)
                .then((response)=>
                {
                    if(response.data.code == "20000")
                    {
                        this.$message({
                        message: '修改成功!',
                        type: 'success'
                        });
                        this.getAll();
                    }
                    else
                    {
                        this.$message({
                        message: '修改失败!',
                        type: 'error'
                        });
                    }
                });
            }
            else
            {
                this.$message({
                    message: '已经完成了',
                    type: 'warning'
                });
            }
        },

        deleteByID(id)
        {
          console.log("删除数据")
          console.log(id)
          api_delete(id)
          .then((response)=>
            {
                if(response.data.code == "20000")
                {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.getAll();
                }
                else
                {
                    this.$message({
                        message: '删除失败',
                        type: 'error'
                    });
                }
            })
        },

        quick_insertEmployee()
        {

          this.form = Object.assign({}, this.form, 
          {
            topic: 'Test',
            details: 'this is the test information',
            deadline: '2000-1-1',
          })

          api_insert(this.form)
              .then((response)=>
              {
                this.form = {};
                console.log(response.data);
                this.dialogVisible = false;
                this.getAll();
            })
        }

        
      }
    }
  </script>