<script setup lang="ts">

import { ref } from 'vue';
import {write} from "node:fs";

import axios from "axios";

//const axios = require("axios");

const title = ref("");
const content = ref("");



const write = () => {

  console.log(title.value);
  console.log(content.value);

  axios.post("http://localhost:8082/api/member", {
    name: title.value,
    age: content.value,
  }).then((res) => {
    console.log("성공=====================");
    console.log(res);

  }).catch((err) => {
    console.log("에러발생=====================");
    console.log(err);
  });

/*  fetch("http://localhost:8082/api/post/write", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      title: title.value,
      content: content.value,
    }),
  }).then((res) => {
    console.log(res);
  });*/
};

</script>

<template>
<!--<h1>작성화면</h1>
  <div>
    <input type="text" v-model="cnt" />
    <button @click="cnt++">증가</button>
    <button @click="cnt&#45;&#45;">감소</button>
  </div>-->

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
