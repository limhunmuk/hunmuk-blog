import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
const PostListView = import('@/views/PostListView.vue');

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/post',
      name: 'post',
      component: () => PostListView
    },
  ],
})

export default router
