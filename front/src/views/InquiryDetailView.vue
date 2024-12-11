<script setup lang="ts">
import {onMounted, defineProps, ref} from 'vue';
import {useRouter} from "vue-router";
import apiClient from "@/stores/authStore";


const props = defineProps<{
  inquiryId: number | string;
}>();

interface Comment {
  id: number;
  regId: string;
  regDt: string;
  content: string;
}

const route = useRouter();
const inquiry = ref<{ id?: number; title?: string; content?: string }>({}); // 초기 상태 정의
const comments = ref<Comment[]>([]); // 댓글 리스트는 배열로 초기화
const commentsVisible = ref(false); // 댓글 표시 상태
const toggleComments = () => {
  commentsVisible.value = !commentsVisible.value;
};

const newComment = ref<string>(""); // 댓글 입력 상태

onMounted(() => {
  console.log(`상세화면입니다 >>> ${props.inquiryId}`);
  loadInquiry();
  loadComments();
});

// 상세 데이터 로드
const loadInquiry = () => {
  apiClient.get(`/inquiry/${props.inquiryId}`).then((response) => {
    inquiry.value = response.data;
  }).catch((error) => {
    console.error('상세 데이터를 불러오는 중 오류 발생:', error);
  });
};

// 댓글 목록 로드
const loadComments = () => {
  apiClient.get(`/inquiry/${props.inquiryId}/comment`).then((response) => {
    comments.value = response.data;
  }).catch((error) => {
    console.error('댓글 목록을 불러오는 중 오류 발생:', error);
  });
};

const deleteInquiry = () => {

  apiClient.delete('/inquiry/' + props.inquiryId)
    .then((response) => {
      console.log(response);
      route.push("/inquiry");
    }).catch((error) => {
    console.log(error);
  });
}

const formatDate = (dateString: string) => {
  const date = new Date(dateString);
  return `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`;
};

const addComment = () => {
  if (newComment.value === "") {
    alert("댓글을 입력하세요.");
    return;
  }

  apiClient.post(`/inquiry/${props.inquiryId}/comment`, {inquiryId: `${props.inquiryId}`, content: newComment.value })
    .then(() => {
      alert("댓글이 등록되었습니다.");
      // 댓글 목록 새로고침
      loadComments();
      newComment.value = "";
    })
    .catch((error) => {
      console.error(error);
      alert("댓글 등록 중 오류가 발생했습니다.");
    });
};


// 수정 요청 핸들러
const editComment = (commentId: number, currentContent: string) => {
  const newContent = prompt("댓글을 수정하세요:", currentContent);
  if (newContent !== null) {
    apiClient.put(`/inquiry/comment/${commentId}`, { content: newContent })
      .then(() => {
        alert("댓글이 수정되었습니다.");
        // 댓글 목록 새로고침
        loadComments();
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
        loadComments();
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
    <h1>문의 상세</h1>
  </el-row>
  <div>
    <el-row>
      <el-col>
        <pre class="title-display">{{ inquiry.title || '제목 없음' }}</pre>
      </el-col>
    </el-row>
    <el-row>
      <el-col class="content-display">
        {{ inquiry.content || '내용 없음' }}
      </el-col>
    </el-row>

    <!-- 댓글 섹션 -->
    <div>
      <el-row>
        <el-input
          v-model="newComment"
          placeholder="댓글을 입력하세요"
          class="comment-input"
        />
        <el-button type="primary" @click="addComment">등록</el-button>
      </el-row>
      <el-row>
        <el-col>
          <div class="comment-toggle" @click="toggleComments">
            <span>댓글 {{ comments.length }}개</span>
            <el-icon :name="commentsVisible ? 'arrow-up' : 'arrow-down'" />
          </div>
        </el-col>
      </el-row>
      <el-row v-if="commentsVisible" class="comment-list">
        <el-col v-for="cmmt in comments" :key="cmmt.id" class="comment-item">
          <div class="comment-header">
            <span class="comment-author">{{ cmmt.regId }}</span>
            <span class="comment-date">{{ formatDate(cmmt.regDt) }}</span>
          </div>
          <p class="comment-content">{{ cmmt.content }}</p>
          <div class="comment-actions">
            <el-button type="default" @click="editComment(cmmt.id, cmmt.content)">수정</el-button>
            <el-button type="danger" @click="deleteComment(cmmt.id)">삭제</el-button>
          </div>
        </el-col>
        <el-col v-if="comments.length === 0">
          <p>등록된 댓글이 없습니다.</p>
        </el-col>
      </el-row>
    </div>

    <!-- 버튼 섹션 -->
    <el-row class="button-display">
      <el-col>
        <el-button type="primary" @click="route.push({ name: 'editInquiry', params: { inquiryId: inquiry.id } })">수정</el-button>
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

.content-display {
  white-space: pre-wrap;
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
  margin-bottom: 20px;
}

.comment-actions {
  display: flex;
  gap: 10px;
  margin-top: 5px;
}

.comment-input {
  width: 25%;
  margin-right: 10px;
  margin-bottom: 10px;
}
</style>
