// src/store/authStore.ts
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem("accessToken") || '', // 초기화 시 localStorage에서 토큰을 가져옴
  }),
  actions: {
    setToken(token: string) {
      this.token = token;
      localStorage.setItem("accessToken", token); // 토큰 저장
    },
    clearToken() {
      this.token = '';
      localStorage.removeItem("accessToken"); // 토큰 삭제
    },
    isTokenExpired(): boolean {
      if (!this.token) return true;
      const payloadBase64 = this.token.split('.')[1];
      const decodedPayload = JSON.parse(atob(payloadBase64));
      const exp = decodedPayload.exp * 1000;
      return Date.now() > exp;
    }
  }
});
// src/store/index.ts
