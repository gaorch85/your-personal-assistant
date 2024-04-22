  <template>
    <div>

      <el-table
        :data="tableData"
        style="width: 100%">
        <!-- 姓名列  -->
        <el-table-column
          prop="name"
          label="姓名"
          width="150">
        </el-table-column>
        <!-- 性别列  -->
        <el-table-column
          prop="gender"
          label="性别"
          width="90">
        </el-table-column>
        <!-- 生日列  -->
        <el-table-column
          prop="birthday"
          label="生日"
          width="180">
        </el-table-column>
        <!-- 地址列  -->
        <el-table-column
          prop="address"
          label="地址"
          width="300">
        </el-table-column>
        <!-- 按钮列  -->
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="update(scope.row)"></el-button>
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
              <el-form-item label="姓名">
                <el-col :span="16">
                  <el-input v-model="form.name"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item label="性别">
                <el-col :span="16">
                  <el-select v-model="form.gender" placeholder="请选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                  </el-select>
                </el-col>
              </el-form-item>
              <el-form-item label="生日">
                  <el-col :span="16">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday" value-format="yyyy-MM-dd">
                    </el-date-picker>
                  </el-col>
              </el-form-item>
              <el-form-item label="地址">
                <el-col :span="21">
                  <el-input v-model="form.address"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item>
                <el-col :span="16">
                  <el-button type="primary" @click="onSubmit()">{{ button_name }}</el-button>
                  <el-button @click="cancel()">取消</el-button>
                </el-col>
                  
              </el-form-item>
          </el-form>
        </template>
      </el-dialog>

      

    </div>  
  </template>
  
  
  <script>

  import { api_delete, api_getAll, api_insert, api_update } from '@/api/table';


    export default {
      mounted() {
        this.getAllEmployees();
      },
      data() {
        return {
          dialogVisible: false,
          tableData: [],
          form: {
            id: 0,
            name: '',
            gender: '',
            birthday: '',
            address: '',
          },
          button_name: "",
          form_status: true   //表单状态： true代表插入,  false代表更新
        }
      },
      methods: {
        getAllEmployees() 
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
          console.log("插入数据")
          this.button_name = "立即插入"
          this.dialogVisible = true
          this.form_status = true
        },

        update(row)
        {
          console.log("更新数据")
          this.button_name = "立即更新"
          this.dialogVisible = true
          this.form_status = false
          
          //表单数据和更新行数据保持一致
          this.form = Object.assign({}, this.form, 
          {
            id: row.id,
            name: row.name,
            gender: row.gender,
            birthday: row.birthday,
            address: row.address
          })
          // 下面的做法不符合vue的规范，导致数据双向绑定出现异常，使用上面的方法(来源于Chat GPT3.5)
          // this.form.id = row.id
          // this.form.name = row.name
          // this.form.gender = row.gender
          // this.form.birthday = row.birthday
          // this.form.address = row.address

        },

        onSubmit() 
        {
          console.log(this.form)
          if(this.form_status)
          {
            api_insert(this.form)
              .then((response)=>
              {
                this.form = {};
                console.log(response.data)
                this.dialogVisible = false
                this.getAllEmployees()
              })
          }
          else
          {
            api_update(this.form)
              .then((response)=>
                {
                  this.form = {};
                  console.log(response.data)
                  this.dialogVisible = false
                  this.getAllEmployees()
                })
          }
        },

        cancel()
        {
          this.form = {};
          this.dialogVisible = false
        },

        deleteByID(id)
        {
          console.log("删除数据")
          console.log(id)
          api_delete(id)
          .then((response)=>
            {
              console.log(response.data)
              this.getAllEmployees()
            })
        },

        quick_insertEmployee()
        {

          this.form = Object.assign({}, this.form, 
          {
            name: '测试人员',
            gender: '男',
            birthday: '2000-1-1',
            address: '北京'
          })

          api_insert(this.form)
              .then((response)=>
              {
                this.form = {};
                console.log(response.data)
                this.dialogVisible = false
                this.getAllEmployees()
              })

              this.form = {};
        }

        
      }
    }
  </script>