import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DetailView from '../views/PostDetailView.vue'

let postView = import('@/views/PostListView.vue');
let PoswCreateView = import('@/views/PostCreateView.vue');
let PostEditView = import('@/views/PostEditView.vue');
let InquiryListView = import('@/views/InquiryListView.vue');
let LoginView = import('@/views/LoginView.vue');
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
      component: () => postView
    },
    {
      path: '/post/new',
      name: 'create',
      component: () => PoswCreateView
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
        component: () => PostEditView,
        props: true
    },
    {
     path: '/inquiry',
     name: 'inquiry',
     component: () => InquiryListView
    },
   {
     path: '/login',
     name: 'login',
     component: () => LoginView
    },


  ]
})

export default router


router.beforeEach((to, from, next) => {
  if (to.name !== 'login' && !localStorage.getItem('accessToken')) next({ name: 'login' })
  else next()
});
