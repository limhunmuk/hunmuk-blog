import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  console.log('useCounterStore')
  const count = ref(0)
  const doubleCount = computed(() => count.value * 2)
  function increment() {
    count.value++
    console.log('increment > count.value:', count.value)

  }

  return { count, doubleCount, increment }
})
