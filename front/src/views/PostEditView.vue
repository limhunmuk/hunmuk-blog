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
  console.log("수정화면입니다 >>>" + props.postId);

  axios.get('/api/post/' + props.postId)
    .then((response) => {
      console.log(response);
      post.value = response.data;
    });
});

const edit = () => {

  axios.put('/api/post/' + props.postId, {
    title: post.value.title,
    content: post.value.content
  }).then((response) => {
    console.log(response);
    route.push({name: 'detail', params: {postId: props.postId}});
  }).catch((error) => {
    console.log(error);
  });

}

</script>

<template>
  <el-row class="my-3">
    <h1>수정</h1>
  </el-row>
  <el-row>
    <el-col>
      <el-input type="text" v-model="post.title" placeholder="제목을 입력해주세요"/>
    </el-col>
    <el-col>
      <el-input type="textarea" v-model="post.content" rows="10" placeholder="내용을 입력해주세요"/>
    </el-col>
    <el-row>
      <el-col>
        <el-button type="primary" @click="edit">수정</el-button>
      </el-col>
    </el-row>
  </el-row>
</template>

<style>
</style>
