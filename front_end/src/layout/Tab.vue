<template>
  <div>
    <el-tabs
      v-model="activeTabName" 
      type="card" 
      @tab-remove="removeTab"
      @tab-click="clickTab">
      <el-tab-pane
        v-for="tab in tabs"
        :key="tab.name"
        :label="tab.label"
        :name="tab.name"
        :closable="tab.closable">
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeTabName: '',
        tabs: [],
      };
    },

    watch: {
      $route(to) {
        this.addTab(to);
      }
    },

    created() {
      this.addTab(this.$route);
    },

    methods: {
      addTab(route) {
        const tabName = route.params.id ? `${route.name}-${route.params.id}` : `${route.name}`;
        const existingTab = this.tabs.find(tab => tab.name === tabName);
        if (!existingTab) {
          this.tabs.push({
            label: route.meta.title || `${route.name} ${route.params.id}`,
            name: tabName,
            closable: route.name !== 'Home', // Home tab is not closable
            route: {
              name: route.name,
              params: route.params,
            }
          });
        }
        this.activeTabName = tabName;
      },

      removeTab(targetName) {
        const targetIndex = this.tabs.findIndex(tab => tab.name === targetName);
        if (targetIndex !== -1) {
          this.tabs.splice(targetIndex, 1);
          if (this.activeTabName === targetName && this.tabs.length > 0) {
            const newActiveTab = this.tabs[this.tabs.length - 1];
            this.$router.push({ name: newActiveTab.route.name, params: newActiveTab.route.params });
          } else if (this.tabs.length === 0) {
            this.$router.push({ path: '/home' });
          }
        }
      },

      handleBlogDeleted(postId) {
        //console.log("删除博客的标签" + postId)
        const tabName = this.tabs.find(tab => tab.route.params.id === postId)?.name;
        if (tabName) {
          this.removeTab(tabName);
        }
      },

      clickTab(targetTab) {
        const clickedTab = this.tabs.find(tab => tab.name === targetTab.name);
        if (clickedTab && (this.$route.name !== clickedTab.route.name || this.$route.params.id !== clickedTab.route.params.id)) {
          this.$router.push({ name: clickedTab.route.name, params: clickedTab.route.params });
        }
        
      }
    }
  }
</script>


