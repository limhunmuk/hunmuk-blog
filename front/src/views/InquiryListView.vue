<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from "axios";

const inquiries = ref([]);
const getInquiry = async () => {
  const response = await axios.get('/api/inquiry', {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`
    }
  });
  inquiries.value = response.data;
}


onMounted(() => {
  getInquiry();
});
</script>

<template>
  <el-row class="my-3">
    <h1>문의하기</h1>
  </el-row>
  <br/>
  <div>
    <ul>
      <li v-for="item in inquiries" :key="item.id">
        <router-link :to="{name: 'detail', params:{postId : item.id}}">{{ item.title }}> {{item.id}}</router-link>
        <p>{{ item.content }}</p>
      </li>
    </ul>
  </div>
</template>
