import { createRouter, createWebHistory } from 'vue-router';

import Management from '@/views/pages/Management.vue';
import userVote from '@/views/pages/userVote.vue';
import Login from '@/views/pages/Login.vue';
import Home from '@/views/Home.vue';
import NotFound from '@/views/NotFound.vue';




// 定義網頁路由
const routes = [
    // { name: "home", path: '/', component: Home },
    { name: "notfound-link", path: "/:pathMatch(.*)*", component: NotFound },
    { name: "Management", path: '/management', component: Management },
    { name: "userVote", path: '/uservote', component: userVote },
    { name: "Login", path: '/', component: Login },
    // ...routerSecure,
    // ...routerSecure,


];

// 產生路由物件
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes,
})

//  導出路由物件以利其他元件導入
export default router