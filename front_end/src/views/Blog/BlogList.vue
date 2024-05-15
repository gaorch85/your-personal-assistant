<template>
    <div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="推荐" name="recommend"></el-tab-pane>
        <el-tab-pane label="最新" name="latest"></el-tab-pane>
        <el-tab-pane label="我的博客" name="myBlog"></el-tab-pane>
        <el-tab-pane label="我的点赞" name="myLike"></el-tab-pane>
        <el-tab-pane label="我的收藏" name="myFavorite"></el-tab-pane>
      </el-tabs>
      
      <el-card shadow="hover" v-for="post in posts" :key="post.id">
        <div class="blog-post">
          <h2 class="post-title">{{ post.title }}</h2>
          <vue-markdown :source="test" />
          <div class="post-footer">
            <span class="post-author">{{ post.username }}</span>
              <span class="post-date">{{ post.time }}</span>
              <el-button type="text" @click="viewPost(post.id)">阅读更多</el-button>
          </div>



        </div>
      </el-card>

        
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
        posts: [],
        test: 'recommend',
        activeName: 'recommend',
      };
    },

    computed: {


    },
    methods: {
      handleClick(tab) 
      {
        this.activeName = tab.name;
        this.getAll(this.activeName)
        this.test = tab.name;
        //console.log(this.test);
      },

      getAll(sortIndex)
      {
        api_getAll(sortIndex)
        .then((response)=>
        {
          this.posts=response.data.data.items;    
          //console.log(this.posts);
        })
      },

      viewPost(id) {
        // 根据postId执行跳转或其他操作
        //console.log('查看博客文章', id);
        this.$router.push({ name: 'BlogPost', params: { id } });
    
      }
    }
  };
  </script>
  
<style scoped>
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