<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HeaderView from "@/views/HeaderView.vue";
</script>

<template>
  <el-container style="width: 1280px; margin-top: 25px;">
    <el-header id="header">
      <HeaderView/>
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
