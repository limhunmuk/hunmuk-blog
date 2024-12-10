// src/store/authStore.ts
import axios from "axios";

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: '/api',
  headers: {
    "Content-Type": "application/json",
  },
});

// Axios 요청 인터셉터
apiClient.interceptors.request.use((config) => {
  const accessToken = localStorage.getItem("accessToken");
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

// Axios 응답 인터셉터
apiClient.interceptors.response.use(
  (response) => response, // 응답 성공
  async (error) => {
    const originalRequest = error.config;

    // 401 Unauthorized 발생 시 리프레시 토큰 사용
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true; // 무한 루프 방지
      const refreshToken = localStorage.getItem("refreshToken");

      if (refreshToken) {
        try {
          const response = await axios.post('/api/auth/refresh', { token: refreshToken });
          const newAccessToken = response.data.accessToken;

          // 새로운 액세스 토큰 저장
          localStorage.setItem("accessToken", newAccessToken);

          // 헤더에 새 토큰 설정 후 원래 요청 재시도
          originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
          return apiClient(originalRequest);
        } catch (refreshError) {
          console.error("리프레시 토큰 만료:", refreshError);
          localStorage.removeItem("accessToken");
          localStorage.removeItem("refreshToken");

          alert("세션이 만료되었습니다. 다시 로그인해주세요.[로그인 페이지 이동]");
          window.location.href = "/login"; // 로그인 페이지로 리다이렉트
        }
      }
    }
    return Promise.reject(error); // 다른 에러는 그대로 반환
  }
);

export default apiClient;
// src/store/index.ts
