<template>
    <el-menu
      :default-active="$route.name"
      class="el-menu-vertical-demo"
      @select="handleSelect">

        <template v-for="route in routes">

            <template v-if="!route.hasOwnProperty('children')">
                <el-menu-item :index="route.name" :key="route.name">
                    <i :class="route.meta.icon"></i>
                    <span>{{ route.meta.title }}</span>
                </el-menu-item>
            </template>

            <template v-else>
                <el-submenu :index="route.name" :key="route.name">
                    <template slot="title">
                        <i :class="route.meta.icon"></i>
                        <span>{{ route.meta.title }}</span>
                    </template>

                    <template v-for="child in route.children">
                        <el-menu-item :index="child.name" :key="child.name">
                            <i :class="child.meta.icon"></i>
                            <span>{{ child.meta.title }}</span>
                        </el-menu-item>
                    </template>
                </el-submenu>
            </template>

        </template>
    </el-menu>
</template>
  
  <script>
  export default {
    props: {
      routes: {
        type: Array,
        required: true
      }
    },
    methods: {
        handleSelect(index)
        {
            if(this.$route.name != index)
                this.$router.push({ name: index });
        },
    }
  };
  </script>