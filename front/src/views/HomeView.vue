<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";
import {RouterLink, RouterView} from "vue-router";
import HeaderView from './HeaderView.vue';

const posts = ref([]);
const getPosts = async () => {
  const response = await axios.get('/api/post');
  posts.value = response.data;
}

getPosts();
</script>
<template>
  <el-container style="width: 1280px;">
    <el-main>
      <el-row>
        <el-col>
          <div>내용</div>
          <div>내용</div>
          <div>내용</div>
          <div>내용</div>
          <div>내용</div>
          <div>내용</div>
          <div>내용</div>
          <el-button type="primary">등록</el-button>
        </el-col>
      </el-row>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>
<style>
</style>
