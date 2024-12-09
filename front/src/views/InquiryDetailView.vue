<script setup lang="ts">
import {onMounted, defineProps, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";
import apiClient from "@/stores/authStore";


const props = defineProps({
  inquiryId: {
    type: [Number, String],
    required: true
  }
});

const route = useRouter();
const inquiry = ref({});

const comments = ref([]); // 댓글 리스트 상태

const commentsVisible = ref(false); // 댓글 표시 상태

const toggleComments = () => {
  commentsVisible.value = !commentsVisible.value;
};


onMounted(() => {
  console.log("상세화면입니다 >>>" + props.inquiryId);

  apiClient.get('/inquiry/' + props.inquiryId)
  .then((response) => {
    console.log(response);
    inquiry.value = response.data;
  }).catch((error) => {
  });

  fetchComments();

});

// 댓글 목록 가져오기
const fetchComments = () => {
  apiClient.get(`/inquiry/${props.inquiryId}/comment`)
    .then((response) => {
      comments.value = response.data;
    })
    .catch((error) => {
      console.error(error);
      alert("댓글 목록을 불러오는 중 오류가 발생했습니다.");
    });
};

const deleteInquiry = () => {

  apiClient.delete('/inquiry/' + props.inquiryId)
    .then((response) => {
      console.log(response);
      route.push("/");
    }).catch((error) => {
    console.log(error);
  });
}

const formatDate = (dateString: string) => {
  const date = new Date(dateString);
  return `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`;
};

// 수정 요청 핸들러
const editComment = (commentId: number, currentContent: string) => {
  const newContent = prompt("댓글을 수정하세요:", currentContent);
  if (newContent !== null) {
    apiClient.put(`/inquiry/comment/${commentId}`, { content: newContent })
      .then(() => {
        alert("댓글이 수정되었습니다.");
        // 댓글 목록 새로고침
        fetchComments();
      })
      .catch((error) => {
        console.error(error);
        alert("댓글 수정 중 오류가 발생했습니다.");
      });
  }
};

// 삭제 요청 핸들러
const deleteComment = (commentId: number) => {
  if (confirm("댓글을 삭제하시겠습니까?")) {
    apiClient.delete(`/inquiry/comment/${commentId}`)
      .then(() => {
        alert("댓글이 삭제되었습니다.");
        // 댓글 목록 새로고침
        fetchComments();
      })
      .catch((error) => {
        console.error(error);
        alert("댓글 삭제 중 오류가 발생했습니다.");
      });
  }
};
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

      <!-- 댓글 리스트
      <el-row v-if="comments.length === 0">
        <el-col>
          <p>댓글이 없습니다.</p>
        </el-col>
      </el-row>
      <el-row v-for="comment in comments" :key="comment.id" class="comment-item">
        <el-col>
          <p>{{ comment.content }}</p>
        </el-col>
      </el-row>

      -->

      <el-row>
        <el-col>
          <div class="comment-toggle" @click="toggleComments">
            <span>댓글 {{ comments.length }}개</span>
            <el-icon :name="commentsVisible ? 'arrow-up' : 'arrow-down'" />
          </div>
        </el-col>
      </el-row>
      <el-row v-if="commentsVisible" class="comment-list">
        <el-row v-for="comment in comments" :key="comment.id" class="comment-item">
          <el-col>
            <div class="comment-header">
              <span class="comment-author">{{ comment.regId }}</span>
              <span class="comment-date">{{ formatDate(comment.regDt) }}</span>
            </div>
            <p class="comment-content">{{ comment.content }}</p>
            <div class="comment-actions">
              <el-button
                type="default"
                @click="editComment(comment.id, comment.content)">
                수정
              </el-button>
              <el-button
                type="danger"
                @click="deleteComment(comment.id)">
                삭제
              </el-button>
            </div>
          </el-col>
        </el-row>
      </el-row>

    </div>

    <el-row class="button-display">
      <el-col>
        <el-button type="primary" @click="route.push({name: 'editInquiry', params: {inquiryId: inquiry.id}})">수정</el-button>
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

.comment-author {
  font-weight: bold;
  color: #333;
  margin-right: 10px;
}

.comment-date {
  font-style: italic;
  color: #999;
}

.comment-content {
  font-size: 16px;
  color: #333;
  line-height: 1.5;
}

.comment-toggle {
  cursor: pointer;
  display: flex;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
}

.comment-toggle el-icon {
  margin-left: 5px;
}

.comment-list {
  margin-top: 10px;
}

.comment-item {
  float: none;
  display: block;
  width: 100%; /* 필요한 경우 */
}

.comment-item button {
  font-size: 12px; /* 글자 크기 */
  padding: 4px 8px; /* 버튼 내부 여백 */
  height: auto; /* 높이 자동 조정 */
  line-height: 1.2; /* 텍스트 줄 높이 */
  border-radius: 4px; /* 버튼 모서리 둥글게 */
}

.button-display button {
  float: left;
  margin-left: 10px; /* 간격 추가 */
  font-size: 20px; /* 글자 크기 */
  padding: 8px 16px; /* 버튼 내부 여백 */

}

</style>
