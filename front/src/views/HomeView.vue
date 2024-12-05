<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";
import {RouterLink, RouterView} from "vue-router";
import HeaderView from './HeaderView.vue';
import apiClient from "@/stores/authStore";

const posts = ref([]);
const getPosts = async () => {
  const response = await apiClient.get('/post');
  posts.value = response.data;
}

getPosts();
</script>
<template>
  <el-container style="width: 1280px;">
    <el-main>
      <el-row>
        <el-col>
          <div style="width: 100%; height: 500px; background-color: #7d7d7d; color: #ffffff">현제 컨텐츠가 없습니다</div>
        </el-col>
      </el-row>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>
<style>
</style>
