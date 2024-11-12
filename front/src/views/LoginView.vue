<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";
const route = useRouter();

const login = ref({
  loginId: "admin",
  password: "1234"
});

const loginProc = () => {

  if(login.value.loginId == null || login.value.loginId == "" || login.value.loginId == undefined){
    alert("아이디를 입력해주세요");
    return;
  }

  if(login.value.password == null || login.value.password == "" || login.value.password == undefined){
    alert("비밀번호를 입력해주세요");
    return;
  }

  axios.post('/api/login', {
    loginId: login.value.loginId,
    password: login.value.password
  }).then((response) => {
    console.log(response);
    alert("로그인 성공");

    console.log(response.data);
    localStorage.setItem("token", response.data.token);


    //route.push("/post");

  }).catch((error) => {
    console.log(error);
  });
}
</script>

<template>
  <el-row class="my-3">
    <h1>로그인</h1>
  </el-row>
  <br/>
    <div>
        <label for="loginId" class="">아이디</label>
        <el-input type="text" id="loginId" v-model="login.loginId" class="mt-1"/>
    </div>
    <div>
      <label for="password">비밀번호</label>
      <el-input type="password" id="password" v-model="login.password" class="mt-1 mb-2" />
    </div>
    <div>
      <el-button type="success" @click="loginProc">로그인</el-button>
    </div>
</template>
