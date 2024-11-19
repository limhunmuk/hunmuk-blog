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
    <h1>문의하기</h1>
  </el-row>
  <br/>
  <div>
    <ul>
      <li v-for="item in posts" :key="item.id">
        <router-link :to="{name: 'detail', params:{postId : item.id}}">{{ item.title }}> {{item.id}}</router-link>
        <p>{{ item.content }}</p>
      </li>
    </ul>
  </div>
</template>
