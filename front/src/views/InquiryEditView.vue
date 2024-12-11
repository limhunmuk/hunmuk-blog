<script lang="ts" setup>

import {onMounted, defineProps, ref} from 'vue';
import {useRouter} from "vue-router";

const route = useRouter();
const inquiry = ref<{ id?: number; title?: string; content?: string }>({}); // 초기 상태 정의

import apiClient from "@/stores/authStore";

const props = defineProps<{
  inquiryId: number | string;
}>();

onMounted(() => {
  console.log("수정화면입니다 >>>" + props.inquiryId);

  apiClient.get('/inquiry/' + props.inquiryId)
    .then((response) => {
      console.log(response);
      inquiry.value = response.data;
    });
});

const editInquiry = () => {

  apiClient.put('/inquiry/'+ props.inquiryId, {
      title: inquiry.value.title,
      content: inquiry.value.content
    }).then((response) => {
    console.log(response);

    alert("수정 되었습니다.");
    route.replace('/inquiry');
  });

}

const cancelInquiry = () => {
  route.replace('/inquiry');
}

</script>

<template>
  <el-row class="my-3 mt-3">
    <h2>문의 수정</h2>
  </el-row>
  <el-form :model="inquiry" label-width="auto" style="max-width: 600px">
    <el-form-item label="제목">
      <el-input v-model="inquiry.title" />
    </el-form-item>
    <el-form-item label="내용">
      <el-input v-model="inquiry.content" type="textarea" rows="15" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="editInquiry">저장</el-button>
      <el-button @click="cancelInquiry">취소</el-button>
    </el-form-item>
  </el-form>
</template>

