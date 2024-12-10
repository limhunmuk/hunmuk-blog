import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
const PostListView = import('@/views/PostListView.vue');
const PostCreateView = import('@/views/PostCreateView.vue');
const PostEditView = import('@/views/PostEditView.vue');
const PostDetailView = import('@/views/PostDetailView.vue');

const InquiryListView = import('@/views/InquiryListView.vue');
const InquiryDetailView = import('@/views/InquiryDetailView.vue');
const InquiryEditView = import('@/views/InquiryEditView.vue');

const LoginView = import('@/views/LoginView.vue');

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

  ],
})

export default router
