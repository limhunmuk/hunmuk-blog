<script setup lang="ts">

import {RouterLink} from "vue-router";
</script>

<template>
    <el-row id="header_top">
      <span class="logo"></span>
      <br>
    </el-row>
    <el-row id="header_bottom">
      <RouterLink to="/">대시보드</RouterLink>
      <RouterLink to="/post">게시글</RouterLink>
      <RouterLink to="/inquiry">공지사항</RouterLink>
      <RouterLink to="/statis">통계</RouterLink>
      <RouterLink to="/calc">계산기</RouterLink>
    </el-row>
</template>

<style scoped>

.logo {
  width: 40px;
  height: 40px;
  background-image: url("/favicon.ico");
  background-size: contain;
  background-repeat: no-repeat;
}

#header_top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 20px; /* 좌우 여백 추가 */
  background-color: #f5f5f5;
}

#header_bottom {
  background-color: #2c3e50;
  display: flex;
  justify-content: space-evenly; /* 균등하게 요소를 배치 */
  align-items: center;
  width: 100%;
  padding: 15px 0;
}

#header_bottom a {
  color: white;
  text-decoration: none;
  font-size: 16px;
}

#header_bottom a:hover {
  text-decoration: underline;
}
</style>
