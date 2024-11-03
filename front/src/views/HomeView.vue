<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";

const posts = ref([]);
const getPosts = async () => {
  const response = await axios.get('/api/post');
  posts.value = response.data;
}

getPosts();
</script>
<template>
  <el-row class="my-3">
    <h1>프로필</h1>
  </el-row>
  <el-row>
    <el-row>
      <el-col>
        <h5>인적사항</h5>
        <ul>
          <li>
            <p>이름 : 임훈묵</p>
          </li>
          <li>
            <p>나이 : 30</p>
          </li>
          <li>
            <p>성별 : 남</p>
          </li>
        </ul>
      </el-col>
      <el-col>
        <h5>주소</h5>
        <ul>
          <li>
            <p>주소 : 서울시 강남구</p>
          </li>
          <li>
            <p>우편번호 : 12345</p>
          </li>
        </ul>
      </el-col>
      <el-col>
        <h5>이력</h5>
        <ul>
          <li>
            웹비즈크리에이티브
          </li>
          <li>
            뉴리버
          </li>
          <li>
            디클라우드
          </li>
          <li>
            퍼스트마인드
          </li>
          <li>
            하늘연소프트
          </li>
          <li>
            유비즈 정보기술
          </li>
        </ul>
      </el-col>
      <el-col>
        <el-button type="primary">연락남기기</el-button>
      </el-col>
    </el-row>
  </el-row>
</template>
