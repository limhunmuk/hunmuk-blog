import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import InquiryEditView from "@/views/InquiryEditView.vue";

let PostView = import('@/views/PostListView.vue');
let PostCreateView = import('@/views/PostCreateView.vue');
let PostEditView = import('@/views/PostEditView.vue');
let InquiryListView = import('@/views/InquiryListView.vue');
let LoginView = import('@/views/LoginView.vue');
let PostDetailView = import('@/views/PostDetailView.vue');
let InquiryDetailView = import('@/views/InquiryDetailView.vue');
let CountView = import('@/views/CountView.vue');
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
      component: () => PostView
    },
    {
      path: '/post/new',
      name: 'create',
      component: () => PostCreateView
    },
    {
      path : '/post/:postId',
      name: 'postDetail',
        component: () => PostDetailView,
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
     path: '/inquiry/:inquiryId',
     name: 'inquiryDetail',
     component: () => InquiryDetailView,
      props: true
    },
    {
     path: '/editInquiry/:inquiryId',
     name: 'editInquiry',
     component: () => InquiryEditView,
      props: true
    },
    {
     path: '/login',
     name: 'login',
     component: () => LoginView
    },
{
     path: '/calc',
     name: 'calc',
     component: () => CountView
    },


  ]
})

export default router


router.beforeEach((to, from, next) => {
  if (to.name !== 'login' && (!localStorage.getItem('accessToken') || !localStorage.getItem('refreshToken'))) next({ name: 'login' })
  else next()
});
