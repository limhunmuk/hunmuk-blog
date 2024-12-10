<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";
const route = useRouter();
import { useCounterStore } from '@/stores/counter'

const counterStore = useCounterStore()

// 데이터와 메서드 사용
const { count, doubleCount, increment } = counterStore

</script>

<template>
  <div>
    <h1>Pinia Counter Example</h1>
    <p>Count: {{ counterStore.count }}</p>
    <p>Double Count: {{ counterStore.doubleCount }}</p>
    <button @click="counterStore.increment">Increment</button>
  </div>
</template>
<style scoped>
button {
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>
