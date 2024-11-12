<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <el-container style="width: 1280px; margin-top: 25px;">
    <el-header>
      <el-row id="h-top">
      </el-row>
      <el-row id="header">
          <RouterLink to="/">프로필</RouterLink>
          <RouterLink to="/post">게시글</RouterLink>
          <RouterLink to="/letter">남기신글</RouterLink>
          <RouterLink to="/login">로그인</RouterLink>
      </el-row>
      <el-row id="h-bottom">
      </el-row>
    </el-header>
    <el-main>
      <RouterView />
    </el-main>
</el-container>
</template>

<style scoped>
#header {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
}

#h-top {
  background-color: #2c3e50;
  height: 10px;
}

#h-bottom {
  background-color: #2c3e50;
  height: 10px;
  margin-bottom: 20px;
}

</style>
