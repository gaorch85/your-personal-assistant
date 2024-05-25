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
        <div class="posts-container">
          <div class="post-card" v-for="post in posts" :key="post.id" @click="viewPost(post.id, post.title)">
            <div class="blog-post">
              <h2 class="post-title">{{ post.title }}</h2>
              <p class="post-content">{{ post.summary }}</p>
              <div class="post-footer">
                <div class="post-info">
                  <span class="post-author">{{ post.username }}</span>
                  <span class="post-date">{{ post.time }}</span>
                </div>
                <div class="like-favorite-comment">
                  <div v-if="!post.isPublic" style="font-size: 24px;">
                    <i class='el-icon-lock'></i>
                  </div>
                  <div class="view">
                    <i class='el-icon-view'></i>
                    <span>{{ post.viewSize }}</span>
                  </div>
                  <div class="like">
                    <i :class="{'iconfont icon-zan': post.myLike, 'iconfont icon-zan1': !post.myLike}"></i>
                    <span>{{ post.likeSize }}</span>
                  </div>
                  <div class="favorite">
                    <i :class="{'iconfont icon-shoucang': post.myFavorite, 'iconfont icon-shoucang1': !post.myFavorite}"></i>
                    <span>{{ post.favoriteSize }}</span>
                  </div>
                  <div class="comments">
                    <i class="el-icon-chat-dot-square"></i>
                    <span>{{ post.commentSize }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
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
      },

      getAll(sortIndex)
      {
        this.posts = null;
        api_getAll(sortIndex)
        .then((response)=>
        {
          this.posts = response.data.data.items;    
        })
      },

      viewPost(id, title) {
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

  .posts-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
  padding: 20px;
  background-color: #f5f5f5;
}

.post-card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
  width: 100%;
  max-width: 800px;
  padding: 20px;
  cursor: pointer;
}

.post-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.blog-post {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.post-title {
  font-size: 1.8em;
  margin-bottom: 10px;
  color: #333;
  font-weight: bold;
}

.post-content {
  flex-grow: 1;
  font-size: 1em;
  margin-bottom: 20px;
  color: #555;
}

.post-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #999;
}

.post-info {
  display: flex;
  flex-direction: column;
}

.post-author, .post-date {
  font-size: 0.9em;
}

.like-favorite-comment {
  display: flex;
  gap: 10px;
}

.view .like, .favorite, .comments {
  display: flex;
  align-items: center;
}

.view i, .like i, .favorite i, .comments i {
  font-size: 24px;
  margin-right: 5px;
}

.like i.iconfont.icon-zan,
.like i.iconfont.icon-zan1,
.favorite i.iconfont.icon-shoucang,
.favorite i.iconfont.icon-shoucang1,
.comments i.iconfont.icon-zan1 {
  transition: color 0.3s;
}

.like i.iconfont.icon-zan {
  color: #ff6b6b; /* 红色代表点赞 */
}

.like i.iconfont.icon-zan1 {
  color: #cccccc; /* 灰色代表未点赞 */
}

.favorite i.iconfont.icon-shoucang {
  color: #ffd700; /* 金色代表收藏 */
}

.favorite i.iconfont.icon-shoucang1 {
  color: #cccccc; /* 灰色代表未收藏 */
}

.comments i.iconfont.icon-zan1 {
  color: #cccccc; /* 灰色代表评论数量 */
}

span {
  font-size: 16px;
}
</style>