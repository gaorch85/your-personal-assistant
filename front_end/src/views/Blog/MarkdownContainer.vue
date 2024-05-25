<template>
    <div class="markdown-container" v-html="renderedMarkdown"></div>
  </template>
  
  <script>
  import MarkdownIt from 'markdown-it';
  import katex from 'markdown-it-katex';
  import hljs from 'highlight.js'
  import 'highlight.js/styles/default.css';

  export default {
    props: {
      markdownContent: {
        type: String,
        required: true
      }
    },
    computed: {
      renderedMarkdown() {

        const md = MarkdownIt({
        highlight: function (str, lang) {
          if (lang && hljs.getLanguage(lang)) {
            try {
              return hljs.highlight(str, { language: lang }).value;
            } catch (__) {}
          }

          return ''; // use external default escaping
        }
      });
        md.use(katex);
        return md.render(this.markdownContent);
      }
    }
  };
  </script>
  
  <style>
  /* 在这里添加KaTeX的CSS样式 */
  @import 'katex/dist/katex.min.css';

  /* Markdown 渲染容器 */
.markdown-container {
  max-width: 900px; /* 最大宽度 */
  margin: 0 auto; /* 居中显示 */
  font-family: Arial, sans-serif; /* 字体样式 */
  line-height: 1.6; /* 行高 */
  color: #333; /* 文字颜色 */
  font-size: 16px; /* 字体大小 */
}

/* 标题样式 */
.markdown-container h1 {
  font-size: 24px; /* 标题字体大小 */
  font-weight: bold; /* 标题粗细 */
  color: #333; /* 标题颜色 */
  margin-bottom: 20px; /* 标题下边距 */
}

.markdown-container h2 {
  font-size: 20px;
  font-weight: bold;
  color: #333;
  margin-bottom: 16px;
}

.markdown-container h3 {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-bottom: 12px;
}

.markdown-container h4 {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
}

.markdown-container h5 {
  font-size: 14px;
  font-weight: bold;
  color: #333;
  margin-bottom: 8px;
}

.markdown-container h6 {
  font-size: 12px;
  font-weight: bold;
  color: #333;
  margin-bottom: 6px;
}

/* 段落样式 */
.markdown-container p {
  margin-bottom: 16px; /* 段落下边距 */
}

/* 列表样式 */
.markdown-container ul,
.markdown-container ol {
  margin-bottom: 16px; /* 列表下边距 */
}

.markdown-container ul li {
  margin-bottom: 8px; /* 列表项下边距 */
}

.markdown-container ol li {
  margin-bottom: 8px;
}

/* 链接样式 */
.markdown-container a {
  color: #007bff; /* 链接颜色 */
}

.markdown-container a:hover {
  color: #0056b3; /* 鼠标悬停时的链接颜色 */
}

/* 代码块样式 */
.markdown-container pre {
  background-color: #f8f9fa; /* 代码块背景色 */
  padding: 10px; /* 代码块内边距 */
  border-radius: 5px; /* 代码块圆角 */
  overflow: auto; /* 滚动条样式 */
}

.markdown-container code {
  font-family: Consolas, Monaco, 'Andale Mono', 'Ubuntu Mono', monospace; /* 代码字体样式 */
}

/* 引用样式 */
.markdown-container blockquote {
  border-left: 4px solid #007bff; /* 引用边框颜色 */
  padding-left: 10px; /* 引用内边距 */
  margin-left: 0; /* 引用左侧外边距 */
}

/* 图片样式 */
.markdown-container img {
  max-width: 100%; /* 图片最大宽度 */
  height: auto; /* 图片高度自适应 */
  margin-bottom: 16px; /* 图片下边距 */
}

  </style>
  