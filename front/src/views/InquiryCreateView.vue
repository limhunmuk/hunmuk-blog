<script setup lang="ts">
import {ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";

const route = useRouter();
const inquiry = ref<{ id?: number; title?: string; content?: string }>({}); // 초기 상태 정의

const create = () => {

  axios.post('/api/inquiry', {
    title: inquiry.value.title,
    content: inquiry.value.content
  },
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`
      }
    }).then((response) => {
    console.log(response);

    alert("등록되었습니다.");

    route.push({name: 'inquiryDetail', params: {inquiryId: response.data.id}});
  }).catch((error) => {
    console.log(error);
  });

}
</script>

<template>
  <el-row class="my-3">
    <h1>문의 등록</h1>
  </el-row>
  <el-row>
    <el-col class="mb-2">
      <el-input type="text" v-model="inquiry.title" placeholder="제목을 입력해주세요"/>
    </el-col>
    <el-col class="mb-2">
      <el-input type="textarea" v-model="inquiry.content" rows="10" placeholder="내용을 입력해주세요"/>
    </el-col>
    <el-row>
      <el-col>
        <el-button type="primary" @click="create">등록</el-button>
      </el-col>
    </el-row>
  </el-row>
</template>

<style>

</style>
