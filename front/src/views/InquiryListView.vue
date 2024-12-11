<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const route = useRouter();

import apiClient from "@/stores/authStore";

interface inquiry {
  id: number;
  title: string;
  regId: string;
  regDt: string;
}

const inquiries = ref<inquiry[]>([]);

const getInquiry = async () => {

  await apiClient.get('/inquiry')
    .then((res) => {
      console.log(res);
      inquiries.value = res.data;

      console.log("=============================");
      console.log(res.data[0].title);
      console.log(res.data[0].content);
      console.log("=============================");
  });
}

onMounted(() => {
  getInquiry();
});

const create = () => {
  route.push({name: 'inquiryCreate'});
}
</script>

<template>
  <el-row class="my-3">
    <h1>공지사항</h1>
  </el-row>
  <el-row>
    <el-col>
      <ul class="inquiry-list">
        <li class="ui-menu">
          <span>번호</span>
          <span>제목</span>
          <span>작성자</span>
          <span>작성일</span>
        </li>
        <li v-for="item in inquiries" :key="item.id">
          <span>{{ item.id }}</span>
  <!--        <span>{{ item.title }}</span>-->
          <router-link :to="{name: 'inquiryDetail', params:{inquiryId : item.id}}">{{ item.title }}</router-link>
          <span>{{ item.regId }}</span>
          <span>{{ item.regDt }}</span>
        </li>
      </ul>
    </el-col>
  </el-row>
  <el-row class="button-row">
    <el-col>
      <el-button class="el-button" type="primary" @click="create">등록</el-button>
    </el-col>
  </el-row>
</template>

<style scoped>
h1 {
  text-align: left;
  padding-left: 100px;
  font-size: 24px;
  margin-top: 30px;
  margin-bottom: 15px;
  color: #2c3e50;
}
.inquiry-list {
  list-style: none;
  padding: 0;
}

.inquiry-list li.ui-menu {
  display: grid;
  grid-template-columns: 5% auto 25% 25%; /* 5%, auto, 10%, 15%로 비율 설정 */
  padding: 10px;
  border-bottom: 1px solid #7d7d7d;
}

.inquiry-list li {
  display: grid;
  grid-template-columns: 5% auto 25% 25%; /* 5%, auto, 10%, 15%로 비율 설정 */
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.inquiry-list li span,
.inquiry-list li a {
  flex: 1;
  text-align: center;
}

.inquiry-list li a:hover {
  text-decoration: underline;
}

.button-row {
  text-align: right;
  margin-top: 15px;
}

.el-button {
  margin-top: 15px;
  margin-right: 100px;
}
</style>
