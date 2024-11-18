<script setup lang="ts">
import { ref } from 'vue';
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

getPosts();
const create = () => {
  if(isTokenExpired()) {
    alert("토큰이 만료되었습니다.");
    route.push("/login");
  }else{
    alert("토큰 있음");
  }

  route.push({name: 'create'});
}

function isTokenExpired() {
  const token = localStorage.getItem("token");
  if (!token) return true; // 토큰이 없으면 유효하지 않음

  const payloadBase64 = token.split('.')[1]; // JWT는 "헤더.페이로드.서명" 형식
  const decodedPayload = JSON.parse(atob(payloadBase64));

  const exp = decodedPayload.exp * 1000; // `exp`는 초 단위이므로 밀리초로 변환
  const currentTime = Date.now();

  return currentTime > exp; // 현재 시간이 만료 시간보다 크면 만료됨
}
</script>
<template>
  <el-row class="my-3">
    <h1>리스트</h1>
  </el-row>
  <el-row>
    <ul>
      <li v-for="item in posts" :key="item.id">
        <router-link :to="{name: 'detail', params:{postId : item.id}}">{{ item.title }}> {{item.id}}</router-link>
        <p>{{ item.content }}</p>
      </li>
    </ul>
  </el-row>
  <el-row>
    <el-col>
      <el-button type="primary" @click="create">등록</el-button>
    </el-col>
  </el-row>
</template>
