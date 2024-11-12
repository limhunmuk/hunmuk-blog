<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";
import { useRouter } from "vue-router";
const route = useRouter();

const posts = ref([]);
const getPosts = async () => {
  const response = await axios.get('/api/post', {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`
    }
  });
  posts.value = response.data;
}

getPosts();
const create = () => {
  route.push({name: 'create'});
}
</script>
<template>
  <el-row class="my-3">
    <h1>리스트</h1>
  </el-row>
  <el-row>
    <ul>
      <li v-for="item in posts" :key="item.id">
        <router-link :to="{name: 'detail', params:{postId : item.id}}">{{ item.title }}> {{item.id}}</router-link>
        <p>{{ item.content }}</p>
      </li>
    </ul>
  </el-row>
  <el-row>
    <el-col>
      <el-button type="primary" @click="create">등록</el-button>
    </el-col>
  </el-row>
</template>
