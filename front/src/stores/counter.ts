import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  console.log('useCounterStore')
  const count = ref(0)
  const doubleCount = computed(() => count.value * 2)
  function increment() {
    console.log('increment')
    count.value++

  }

  return { count, doubleCount, increment }
})
