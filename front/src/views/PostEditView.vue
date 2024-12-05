<script lang="ts" setup>

import {onMounted, defineProps, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";

const route = useRouter();
const post = ref({});

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
    <!--     <el-form-item label="Activity zone">
          <el-select  placeholder="please select your zone">
            <el-option label="Zone one" value="shanghai" />
            <el-option label="Zone two" value="beijing" />
          </el-select>
        </el-form-item>
       <el-form-item label="Resources">
        <el-form-item label="Activity type">
          <el-checkbox-group>
            <el-checkbox value="Online activities" name="type">
              Online activities
            </el-checkbox>
            <el-checkbox value="Promotion activities" name="type">
              Promotion activities
            </el-checkbox>
            <el-checkbox value="Offline activities" name="type">
              Offline activities
            </el-checkbox>
            <el-checkbox value="Simple brand exposure" name="type">
              Simple brand exposure
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
    <el-radio-group v-model="form.resource">
        <el-radio value="Sponsor">Sponsor</el-radio>
        <el-radio value="Venue">Venue</el-radio>
      </el-radio-group>
    </el-form-item>-->
    <el-form-item label="내용">
      <el-input v-model="post.content" type="textarea" rows="15" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="edit">저장</el-button>
      <el-button @click="cancle">취소</el-button>
    </el-form-item>
  </el-form>
</template>

