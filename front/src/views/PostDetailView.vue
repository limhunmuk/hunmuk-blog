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

  axios.get('/api/post/' + props.postId, {
    headers: {
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`
  }})
.then((response) => {
    console.log(response);


    post.value = response.data;
  }).catch((error) => {
    console.log(error);
  });

});

const deletePost = () => {

  axios.delete('/api/post/' + props.postId, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`
    }
  })
    .then((response) => {
      console.log(response);
      route.push("/");
    }).catch((error) => {
    console.log(error);
  });

}

const cancle = () => {
  route.replace('/post');
}

</script>

<template>
  <el-row class="my-3 mt-3">
<!--    <h2>상세</h2>-->
  </el-row>
  <div>
    <el-row>
      <el-col>
        <pre class="title-display">{{post.title}}</pre>
      </el-col>
    </el-row>
    <el-row>
      <el-col class="content-display">
        {{post.content}}
      </el-col>
    </el-row>
    <el-row class="button-display">
      <el-col>
        <el-button type="primary" @click="route.push({name: 'edit', params: {postId: post.id}})">수정</el-button>
        <el-button type="info" @click="deletePost">삭제</el-button>
        <el-button @click="cancle">뒤로</el-button>
      </el-col>
    </el-row>
  </div>

</template>

<style scoped>

.title-display {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

/* 상세 보기 스타일 */
.content-display {
  white-space: pre-wrap; /* 줄바꿈을 반영해 표시 */
  font-size: 16px;
  line-height: 1.5;
  color: #333;
  height: 300px;
}

.button-display {
  margin-top: 20px;
}

</style>
