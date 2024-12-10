<script setup lang="ts">
import HeaderView from "@/views/HeaderView.vue";
</script>

<template>
  <el-container>
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
</style>
