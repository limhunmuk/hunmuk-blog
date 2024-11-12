<script setup lang="ts">
import {onMounted, defineProps, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";

const route = useRouter();
const post = ref({});

const props = defineProps({
  postId: {
    type: [Number, String],
    required: true
  }
})
onMounted(() => {
  console.log("상세화면입니다 >>>" + props.postId);
  const token = localStorage.getItem("token");

  axios.get('/api/post/' + props.postId, {
    headers: {
      Authorization: `Bearer ${token}`

  }})
.then((response) => {
    console.log(response);


    post.value = response.data;
  }).catch((error) => {
    console.log(error);
  });

});


const deletePost = () => {

  axios.delete('/api/post/' + props.postId)
    .then((response) => {
      console.log(response);
      route.push("/");
    }).catch((error) => {
    console.log(error);
  });

}

</script>

<template>
  <el-row class="my-3">
    <h1>상세</h1>
  </el-row>
  <div>
    <el-row>
      <el-col>
        <p>{{post.title}}</p>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <p>{{post.content}}</p>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-button type="primary" @click="route.push({name: 'edit', params: {postId: post.id}})">수정</el-button>
        <el-button type="info" @click="deletePost">삭제</el-button>
      </el-col>
    </el-row>
  </div>

</template>

<style>
</style>
