<script setup lang="ts">
import {onMounted, defineProps, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";

const route = useRouter();
const inquiry = ref({});

const comments = ref([]); // 댓글 리스트 상태
const newComment = ref(""); // 입력된 댓글 내용


const props = defineProps({
  inquiryId: {
    type: [Number, String],
    required: true
  }
})
onMounted(() => {
  console.log("상세화면입니다 >>>" + props.inquiryId);

  axios.get('/api/inquiry/' + props.inquiryId, {
    headers: {
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        refreshToken : localStorage.getItem("refreshToken")

  }})
.then((response) => {
    console.log(response);
    inquiry.value = response.data;
  }).catch((error) => {
    console.log(error);
    alert("로그인이 필요합니다.");
    route.push({name: 'login'});
  });

});

const deleteInquiry = () => {

  axios.delete('/api/inquiry/' + props.inquiryId, {
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

</script>

<template>
  <el-row class="my-3">
    <h1>상세</h1>
  </el-row>
  <div>
    <el-row>
      <el-col>
        <pre class="title-display">{{ inquiry.title }}</pre>
      </el-col>
    </el-row>
    <el-row>
      <el-col class="content-display">
        {{ inquiry.content }}
      </el-col>
    </el-row>
    <div>
      <!-- 댓글 작성
      <el-row>
        <el-input
          v-model="newComment"
          placeholder="댓글을 입력하세요"
          class="comment-input"
        />
        <el-button type="primary" @click="addComment">등록</el-button>
      </el-row>
      -->

      <!-- 댓글 리스트 -->
      <el-row v-if="comments.length === 0">
        <el-col>
          <p>댓글이 없습니다.</p>
        </el-col>
      </el-row>
      <el-row v-for="comment in comments" :key="comment.id" class="comment-item">
        <el-col>
          <p>{{ comment.content }}</p>
          <el-button type="danger" size="mini" @click="deleteComment(comment.id)">삭제</el-button>
        </el-col>
      </el-row>
    </div>

    <el-row class="button-display">
      <el-col>
        <el-button type="primary" @click="route.push({name: 'edit', params: {inquiryId: inquiry.id}})">수정</el-button>
        <el-button type="info" @click="deleteInquiry">삭제</el-button>
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

.comment-input {
  margin-bottom: 10px;
  width: 80%;
}

.comment-item {
  margin-bottom: 15px;
  padding: 10px;
  border: 1px solid #eee;
  border-radius: 5px;
}


</style>
