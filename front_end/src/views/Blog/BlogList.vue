<template>
    <div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="推荐" name="recommend"></el-tab-pane>
        <el-tab-pane label="最新" name="latest"></el-tab-pane>
        <el-tab-pane label="我的博客" name="myBlog"></el-tab-pane>
        <el-tab-pane label="我的点赞" name="myLike"></el-tab-pane>
        <el-tab-pane label="我的收藏" name="myCollect"></el-tab-pane>
      </el-tabs>
      
      <ul
        class="list"
        v-infinite-scroll="load"
        infinite-scroll-disabled="disabled">
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
      </ul>
      <p v-if="loading">加载中...</p>
      <p v-if="noMore">没有更多了</p>
        
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
        test: 'recommend',
        activeName: 'recommend',
        loading: false,
        isOver: false,
        loading: false,
        count: 1,
      };
    },

    computed: {
      disabled()
      {
        return this.isOver;
      },
      noMore()
      {
        return this.count > 100;
      }
    },
    methods: {
      handleClick(tab) 
      {
        this.activeName = tab.name;
        this.test = tab.name;
        console.log(this.test);
      },

      load()
      {
        this.count = this.count+1;
        console.log('正在加载');
      },

      getAll()
      {
        api_getAll()
        .then((response)=>
        {
          this.posts=response.data.data.items;    
          //console.log(this.posts);
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