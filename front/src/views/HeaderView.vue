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
      <RouterLink to="/letter">남기신글</RouterLink>
      <RouterLink to="/statis">통계</RouterLink>
    </el-row>
</template>

<style scoped>

#header {
  height: 100px;
}

#header_top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 20px; /* 좌우 여백 추가 */
  background-color: #f5f5f5;
}

#header_top .logo {
  width: 40px;
  height: 40px;
  background-image: url("src/assets/icons2.svg"); /* 경로 수정 */
  background-size: cover;
  background-repeat: no-repeat;
}

#header_top .info {
  display: flex;
  gap: 15px;
  font-size: 16px; /* 텍스트 크기 조정 */
}

#header_bottom {
  background-color: #2c3e50;
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  padding: 15px 0; /* 상하 패딩 사용 */
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
