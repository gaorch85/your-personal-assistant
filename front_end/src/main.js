import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import router from './router'

import './mock'


axios.defaults.baseURL = "http://localhost:8090"
Vue.prototype.$http = axios

Vue.config.productionTip = false

Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
