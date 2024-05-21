<template>
  <div class="class-table">
    <table>
      <thead>
        <tr>
          <th>时间</th>
          <th v-for="col in 7" :key="col">{{ getDayName(col) }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in 14" :key="row" :class="getRowClass(row)">
          <td>{{ getTimeForRow(row) }}</td>
          <td v-for="col in 7" :key="col">
            <select class="full-width centered-text" v-model="schedule[row - 1][col - 1]">
              <option v-for="course in courses" :key="course.id" :value="course.name">{{ course.name }}</option>
              <option>暂无</option>
            </select>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="submit">
      <el-button type="primary" @click="updateCurriculum">提交</el-button>
    </div>
  </div>
</template>

<script>
import { api_getCurriculum, api_changeCurriculum, api_getCourses } from "@/api/curriculum";

export default {
  name: 'ClassTable',
  data() {
    return {
      courses: [],
      schedule: Array.from({ length: 14 }, () => Array(7).fill('暂无')) // Initialize 14 rows and 7 columns with empty strings
    };
  },
  mounted() {
    this.getCourses();
    this.getCurriculum();
  },
  methods: {
    getTimeForRow(row) {
      const times = [
        '08:00 - 08:45', '08:55 - 09:40', '10:00 - 10:45', '10:55 - 11:40',
        '12:00 - 12:45', '12:55 - 13:40', '14:00 - 14:45', '14:55 - 15:40',
        '16:00 - 16:45', '16:55 - 17:40', '18:30 - 19:15', '19:25 - 20:10',
        '20:20 - 21:05', '21:15 - 22:00'
      ];
      return times[row - 1];
    },
    getRowClass(row) {
      if (row >= 1 && row <= 4) return 'bg-color-1';
      if (row >= 5 && row <= 6) return 'bg-color-2';
      if (row >= 7 && row <= 10) return 'bg-color-3';
      if (row >= 11 && row <= 14) return 'bg-color-4';
      return '';
    },
    getDayName(col) {
      const days = ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'];
      return days[col - 1];
    },
    updateCurriculum() {
      const scheduleList = this.schedule.flatMap((row, rowIndex) =>
        row.map((courseName, colIndex) => ({
          timeIndex: rowIndex + 1,
          dayIndex: colIndex + 1,
          courseName: courseName
        }))
      );

      const payload = { scheduleList: scheduleList };

      api_changeCurriculum(payload)
        .then(() => {
          this.getCurriculum();
        })
        .catch(error => {
          console.error("Error updating curriculum:", error);
        });
  },
    getCourses() {
      api_getCourses()
        .then(response => {
          this.courses = response.data.data.items;
        });
    },
    getCurriculum() {
      api_getCurriculum()
        .then(response => {
          const scheduleData = response.data.data.items;
          scheduleData.forEach(item => {
            //this.schedule[item.timeIndex - 1][item.dayIndex - 1] = item.courseName;
            this.$set(this.schedule[item.timeIndex - 1], item.dayIndex - 1, item.courseName);
          });
          console.log("渲染结束");
          console.log(this.schedule);
        });
    }
  }
  
};
</script>


<style scoped>
.class-table {
  font-family: Arial, sans-serif;
  margin: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

th, td {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.bg-color-1 {
  background-color: #e6f7ff;
}

.bg-color-2 {
  background-color: #fff7e6;
}

.bg-color-3 {
  background-color: #e6ffe6;
}

.bg-color-4 {
  background-color: #ffe6e6;
}

.full-width {
  width: 100%;
  border: none;
  outline: none;
  background: transparent;
  text-align: center;
  padding: 4px;
}

.centered-text {
  text-align: center;
}

.submit {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
