<script setup lang="ts">

import { ref } from 'vue';
import {write} from "node:fs";
import {useRouter} from "vue-router";

import axios from "axios";
const useRoute = useRouter();

//const axios = require("axios");

const title = ref("");
const content = ref("");

const write = () => {
  console.log(title.value);
  console.log(content.value);

  //alert(title.value + ' ' + content.value);
  axios.post('/api/post', {
    title: title.value,
    content: content.value
  }).then((response) => {
    console.log(response);
    useRoute.replace('/');
  }).catch((error) => {
    console.log(error);
  });
}
</script>
<template>
  <div>
    <h1>글 등록</h1>
    <div>
      <label for="title">제목</label>
      <el-input type="text" id="title" name="title" v-model="title" />
    </div>
    <div class="mt-3">
      <label for="content">내용</label>
      <el-input type="textarea" rows="20" id="content" name="content" v-model="content" />
    </div>
    <div class="align-baseline mt-3">
      <el-button type="primary" @click="write">등록</el-button>
    </div>
  </div>
</template>

<style scoped>

</style>
