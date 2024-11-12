<script setup lang="ts">
import {onMounted, defineProps, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";

const route = useRouter();
const post = ref({});

function isTokenExpired(_token) {
  if (!_token) return true; // 토큰이 없으면 유효하지 않음

  const payloadBase64 = _token.split('.')[1]; // JWT는 "헤더.페이로드.서명" 형식
  const decodedPayload = JSON.parse(atob(payloadBase64));

  const exp = decodedPayload.exp * 1000; // `exp`는 초 단위이므로 밀리초로 변환
  const currentTime = Date.now();

  return currentTime > exp; // 현재 시간이 만료 시간보다 크면 만료됨
}

const create = () => {

  const token = localStorage.getItem("token");
  if (isTokenExpired(token)) {
    alert("토큰이 만료되었습니다.");
  }

  alert(token);
  axios.post('/api/post', {
    title: post.value.title,
    content: post.value.content
  },
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).then((response) => {
    console.log(response);

    alert("등록되었습니다.");

    route.push('post');
  }).catch((error) => {
    console.log(error);
  });

}

</script>

<template>
  <el-row class="my-3">
    <h1>등록</h1>
  </el-row>
  <el-row>
    <el-col class="mb-2">
      <el-input type="text" v-model="post.title" placeholder="제목을 입력해주세요"/>
    </el-col>
    <el-col class="mb-2">
      <el-input type="textarea" v-model="post.content" rows="10" placeholder="내용을 입력해주세요"/>
    </el-col>
    <el-row>
      <el-col>
        <el-button type="primary" @click="create">등록</el-button>
      </el-col>
    </el-row>
  </el-row>
</template>

<style>

</style>
