<script lang="ts" setup>

import {onMounted, defineProps, ref} from 'vue';
import {useRouter} from "vue-router";

const route = useRouter();
const post = ref<{ id?: number; title?: string; content?: string }>({}); // 초기 상태 정의

import apiClient from "@/stores/authStore";

const props = defineProps({
  postId: {
    type: [Number, String],
    required: true
  }
});

onMounted(() => {
  console.log("수정화면입니다 >>>" + props.postId);

  apiClient.get('/post/' + props.postId)
    .then((response) => {
      console.log(response);
      post.value = response.data;
    });
});

const edit = () => {

  apiClient.put('/post/'+ props.postId, {
      title: post.value.title,
      content: post.value.content
    }).then((response) => {
    console.log(response);

    alert("수정 되었습니다.");
    route.replace('/post');
  });

}

const cancle = () => {
  route.replace('/post');
}

</script>

<template>
  <el-row class="my-3 mt-3">
<!--    <h2>수정</h2>-->
  </el-row>
  <el-form :model="post" label-width="auto" style="max-width: 600px">
    <el-form-item label="제목">
      <el-input v-model="post.title" />
    </el-form-item>
    <el-form-item label="내용">
      <el-input v-model="post.content" type="textarea" rows="15" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="edit">저장</el-button>
      <el-button @click="cancle">취소</el-button>
    </el-form-item>
  </el-form>
</template>

