<template>
    <div>
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
      this.getAll();
    },

    data() {
      return {
        posts: [],
        test: '## nihao'
      };
    },
    methods: {
      getAll()
      {
        api_getAll()
        .then((response)=>
        {
          this.posts=response.data.data.items;    
          console.log(this.posts);
        })
      },

      viewPost(postId) {
        // 根据postId执行跳转或其他操作
        console.log('查看博客文章', postId);
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