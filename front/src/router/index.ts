import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DetailView from '../views/PostDetailView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/post',
      name: 'post',
      component: () => import('../views/PostListView.vue')
    },
    {
      path: '/post/new',
      name: 'create',
      component: () => import('../views/PostCreateView.vue')
    },
    {
      path : '/post/:postId',
      name: 'detail',
        component: () => DetailView ,
        props: true
    },
    {
      path: '/edit/:postId',
        name: 'edit',
        component: () => import('../views/PostEditView.vue'),
        props: true
    },
    {
     path: '/inquiry',
     name: 'inquiry',
     component: () => import('../views/InquiryListView.vue')
   },


  ]
})

export default router
