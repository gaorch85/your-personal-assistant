<template>
    <div>
        <vue-markdown-it :source="post.content" :highlight="true"></vue-markdown-it>

        <h3>点赞量：{{ post.likes.length }}</h3>
        <h3>收藏量：{{ post.favorites.length }}</h3>
        <h3>我已点赞：{{ post.myLike ? '是' : '否' }}</h3>
        <h3>我已收藏：{{ post.myFavorite ? '是' : '否' }}</h3>
        <el-button type="primary" @click="like()" round>{{ post.myLike ? '取消点赞' : '点赞' }}</el-button>
        <el-button type="primary" @click="favorite()" round>{{ post.myFavorite ? '取消收藏' : '收藏' }}</el-button>
        <br><br>


        <div class="comment-box">
          <el-input
            v-model="comment"
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="写下你的评论..."
          >
          </el-input>
          <br><br>
          <el-col :span="2" :offset="22">
            <el-button type="primary" @click="addComment">发表评论</el-button>
          </el-col>
        </div>

        <br><br><br>
        <div id="cmt-section">
          <div v-for="comment in post.comments"  class="cmt-block" :key="comment.id">
              <div class="cmt-name">
                  {{comment.username}}
              </div>
              <div class="cmt-details">
                  {{comment.createdAt}}
              </div>
              <div class="cmt-value">
                  {{comment.content}}
              </div>
          </div>
      </div>
    </div>
  </template>
  
  <script>
  import VueMarkdownIt from 'vue-markdown-it';
  import markdownItHighlight from 'markdown-it-highlightjs';
  import VueClipboard from 'vue-clipboard2';
  import { api_getPostById, api_insert_like, api_delete_like, 
          api_insert_favorite, api_delete_favorite, api_add_comment } from "@/api/blog"
  export default {
    name: 'BlogPost',
    components:{
      VueMarkdownIt
    },
    data() {
      return {
        postId: this.$route.params.id,
        post: '',
        comment: '',
        mdcontent: '# hello'
      };
    },
    mounted()
    {
        //console.log(this.postId)
        this.getPost(this.postId);

    },
    created() {
      VueMarkdownIt.use(markdownItHighlight);
    },
    methods: {
        // RenderMarkdown()
        // {
        //   const md = markdownIt({
        //   highlight: function (str, lang) {
        //     if (lang && hljs.getLanguage(lang)) {
        //       try {
        //         return '<pre class="hljs"><code>' +
        //               hljs.highlight(lang, str, true).value +
        //               '</code></pre>';
        //       } catch (__) {}
        //     }
        //     return '<pre class="hljs"><code>' + md.utils.escapeHtml(str) + '</code></pre>';
        //   }
        //   });
        //   this.markdown = md.render(post.content);
        // },

        getPost(postId)
        {
            api_getPostById(this.postId)
            .then((response)=>
            {
                this.post = response.data.data.items;    
            //console.log(this.posts);
            });
        },

        like()
        {
          if(this.post.myLike)
          {
            api_delete_like(this.post.myLikeId)
              .then((response)=>
              {
                  //this.post = response.data.data.items;    
                  //console.log(this.posts);
                  this.getPost(this.postId);
              });
          }
          else
          {
            api_insert_like(this.postId)
            .then((response)=>
            {
                //this.post = response.data.data.items;    
                //console.log(this.posts);
                this.getPost(this.postId);
            });
          }
            
        },

        favorite()
        {
          if(this.post.myFavorite)
          {
            api_delete_favorite(this.post.myFavoriteId)
            .then((response)=>
            {
                //this.post = response.data.data.items;    
                //console.log(this.posts);
                this.getPost(this.postId);
            });

          }
          else
          {
            api_insert_favorite(this.postId)
            .then((response)=>
            {
                //this.post = response.data.data.items;    
                //console.log(this.posts);
                this.getPost(this.postId);
            });
          }
        },

        addComment()
        {
          if (this.comment.trim() === '') {
            this.$message.error('评论内容不能为空');
            return;
          }
          else
          {
            api_add_comment(this.postId,{content: this.comment})
            .then((response)=>
            {
              this.getPost(this.postId);
              this.comment = '';
              this.$message.success('评论成功');
            });
          }
        }
    },
    watch: {
      '$route.params.id'(newId) {
        this.postId = newId;
        this.getPost(this.postId);
      },
    },
  };
  </script>

<style>
/* 
.markdown-container {
  max-width: 800px;
  margin: 0 auto;
}

/* 以下是 highlight.js 的默认样式 */
/* .hljs {
  display: block;
  overflow-x: auto;
  padding: 0.5em;
  color: #333;
  background: #f8f8f8;
}

.hljs-keyword,
.hljs-selector-tag,
.hljs-title {
  color: #1e90ff;
} */

.cmt-block {
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 10px;
    background-color: #f9f9f9;
}

.cmt-name {
    font-weight: bold;
    margin-bottom: 5px;
}

.cmt-details {
    color: #666;
    font-size: 12px;
    margin-bottom: 5px;
}

.cmt-value {
    margin-top: 5px;
}
</style>
  