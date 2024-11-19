<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from "axios";
import { useRouter } from "vue-router";
const route = useRouter();

const posts = ref([]);

const getPosts = async () => {
  const response = await axios.get('/api/post', {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`
    }
  });
  posts.value = response.data;
}

onMounted(() => {
  getPosts();
});

const create = () => {
  route.push({name: 'create'});
}

const goToDetail = (postId: number) => {
  route.push({name: 'detail', params:{postId : postId}});
}

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
    <h2>목록</h2>
    <ul class="post-list">
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

  <el-row>
    <el-col>
      <el-button type="primary" @click="create">등록</el-button>
    </el-col>
  </el-row>

</template>
<style scoped>
h2 {
  text-align: left;
  padding-left: 100px;
  font-size: 24px;
  margin-bottom: 15px;
  color: #2c3e50;
}
.post-list {
  list-style: none;
  padding: 0;
}

.post-list li {
  display: grid;
  grid-template-columns: 5% auto 15% 15%; /* 5%, auto, 10%, 15%로 비율 설정 */
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
.el-button {
  margin-top: 15px;
}
</style>
