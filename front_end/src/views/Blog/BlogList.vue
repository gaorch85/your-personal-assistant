<template>
    <div class="list">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="推荐" name="recommend"></el-tab-pane>
        <el-tab-pane label="最新" name="latest"></el-tab-pane>
        <el-tab-pane label="我的博客" name="myBlog"></el-tab-pane>
        <el-tab-pane label="我的点赞" name="myLike"></el-tab-pane>
        <el-tab-pane label="我的收藏" name="myFavorite"></el-tab-pane>
      </el-tabs>
      
      <div v-if="!posts">
        <el-empty :image-size="300" description="正在加载"></el-empty>
      </div>
      <div v-else-if="posts.length==0">
        <el-empty :image-size="300" description="暂无数据"></el-empty>
      </div>
      <div v-else>
        <el-card shadow="hover" v-for="post in posts" :key="post.id">
          <div class="blog-post">
            <h2 class="post-title">{{ post.title }}</h2>
            <div class="post-footer">
              <span class="post-author">{{ post.username }}</span>
                <span class="post-date">{{ post.time }}</span>
                <el-button type="text" @click="viewPost(post.id, post.title)">阅读更多</el-button>
            </div>
          </div>
        </el-card>
      </div>
      
    </div>
  </template>
  
  <script>
  import VueMarkdown from 'vue-markdown';
  import { api_getAll } from '@/api/blog';
  export default {
    components:{
      VueMarkdown
    },
    mounted()
    {
      this.getAll(this.activeName);
    },

    data() {
      return {
        posts: null,
        activeName: 'recommend',
      };
    },

    computed: {


    },
    methods: {
      handleClick(tab) 
      {
        this.activeName = tab.name;
        this.getAll(this.activeName);
        //console.log(this.test);
      },

      getAll(sortIndex)
      {
        this.posts = null;
        api_getAll(sortIndex)
        .then((response)=>
        {
          this.posts = response.data.data.items;    
          //console.log(this.posts);
        })
      },

      viewPost(id, title) {
        // 根据postId执行跳转或其他操作
        //console.log('查看博客文章', id);
        this.$router.push({ name: 'BlogPost', params: { id, title } });
    
      }
    }
  };
  </script>
  
<style scoped>
  .list {
    max-width: 900px; /* 最大宽度 */
    margin: 0 auto; /* 居中显示 */
    font-family: Arial, sans-serif; /* 字体样式 */
  }

  .blog-post {
    margin-bottom: 20px;
  }
  
  .post-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
  }
  
  .post-content {
    color: #666;
    margin-bottom: 10px;
  }
  
  .post-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .post-date {
    color: #999;
  }

  .post-author {
    color: #999;
  }

</style>