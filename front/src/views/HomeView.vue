<script setup lang="ts">
import axios from "axios";
import {useRouter} from "vue-router";
const route = useRouter();

axios.post('https://drugfree.whz.co.kr/api/v1.1/courses/search',
  {
    "search": {
      "keyword": '%EB%A7%88%EC%95%BD%20',
    }
  },
  {
    headers: {
      'Content-Type': 'application/json',
      'x-api-key': 'Ijhdk3hbZtO2R24sRV09G2345AZ91tIgonpR8N56Q44s7hm'
    }
  }
).then((response) => {
  console.log(response);


  console.log("==================================");
  console.log(response.data);
  console.log("==================================");

  route.push("/post");

}).catch((error) => {
  console.log(error);
});
</script>
<template>
  <el-container style="width: 1280px;">
    <el-main>
      <el-row>
        <el-col>
          <router-link to="/login">로그인</router-link>
          <a href="javascript:void(0)" id="test">로그아웃</a>
        </el-col>
      </el-row>
    </el-main>
    <el-footer></el-footer>
  </el-container>
</template>

<style>

</style>
