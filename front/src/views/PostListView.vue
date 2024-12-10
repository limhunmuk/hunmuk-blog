<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from "vue-router";
const route = useRouter();

import apiClient from "@/stores/authStore.ts";

interface Post {
  id: number;
  title: string;
  regId: string;
  regDt: string;
}

const posts = ref<Post[]>([]);

const currentPage = ref(1);
const totalPages = ref(1);


const getPosts = async (page = 1) => {
    const response = await apiClient.get('/post?page='+page);
    posts.value = response.data.content;
    totalPages.value = response.data.totalPages;
    currentPage.value = response.data.number + 1;

    console.log("=============================");
    console.log(response);
    console.log("=============================");
}

onMounted(() => {
  getPosts();
});

const create = () => {
  route.push({name: 'create'});
}

const goToDetail = (postId: number) => {
  route.push({name: 'postDetail', params:{postId : postId}});
}

const changePage = (page: number) => {
  if (page > 0 && page <= totalPages.value) {
    getPosts(page);
  }
};

/*function isTokenExpired() {
  const token = localStorage.getItem("token");
  if (!token) return true; // 토큰이 없으면 유효하지 않음

  const payloadBase64 = token.split('.')[1]; // JWT는 "헤더.페이로드.서명" 형식
  const decodedPayload = JSON.parse(atob(payloadBase64));

  const exp = decodedPayload.exp * 1000; // `exp`는 초 단위이므로 밀리초로 변환
  const currentTime = Date.now();

  return currentTime > exp; // 현재 시간이 만료 시간보다 크면 만료됨
}*/
</script>
<template>

  <div>
    <el-row class="my-3">
      <h5>게시글이 표현됩니다</h5>
    </el-row>
    <ul class="post-list">
      <li class="ui-menu">
        <span>번호</span>
        <span>제목</span>
        <span>작성자</span>
        <span>작성일</span>
      </li>
      <li v-for="(post, index) in posts" :key="post.id">
        <span>{{ index + 1 }}</span>
        <a @click="goToDetail(post.id)" style="cursor: pointer; color: #409EFF;">
          {{ post.title }}
        </a>
        <span>{{ post.regId }}</span>
        <span>{{ post.regDt }}</span>
      </li>
    </ul>
  </div>
  <div class="pagination">
    <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
    <span v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }" @click="changePage(page)">
        {{ page }}
      </span>
    <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
  </div>
  <el-row class="button-row">
    <el-col>
      <el-button class="el-button" type="primary" @click="create">등록</el-button>
    </el-col>
  </el-row>

</template>
<style scoped>
h5 {
  text-align: left;
  padding-left: 100px;
  font-size: 24px;
  margin-top: 30px;
  margin-bottom: 15px;
  color: #2c3e50;
}
.post-list {
  list-style: none;
  padding: 0;
}

.post-list li.ui-menu {
  display: grid;
  grid-template-columns: 5% auto 25% 25%; /* 5%, auto, 10%, 15%로 비율 설정 */
  padding: 10px;
  border-bottom: 1px solid #7d7d7d;
}

.post-list li {
  display: grid;
  grid-template-columns: 5% auto 25% 25%; /* 5%, auto, 10%, 15%로 비율 설정 */
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.post-list li span,
.post-list li a {
  flex: 1;
  text-align: center;
}

.post-list li a:hover {
  text-decoration: underline;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  gap: 8px;
}

.pagination span {
  padding: 5px 10px;
  cursor: pointer;
}

.pagination span.active {
  font-weight: bold;
  color: #409EFF;
}

.pagination button {
  padding: 5px 10px;
}

.button-row {
  text-align: right;
  margin-top: 15px;
}

.el-button {
  margin-top: 15px;
  margin-right: 100px;
}
</style>
