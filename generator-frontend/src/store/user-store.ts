import { defineStore } from 'pinia'
import { useLocalStorage } from '@vueuse/core'

const accessTokenKey = 'StorageKey-access-token'

export const useUserStore = defineStore('userStore', {
  // 其他配置...
  state: () => ({
    accessToken: useLocalStorage<string | undefined>(accessTokenKey, undefined, {
      writeDefaults: false
    })
  }),
  actions: {
    clean() {
      // 不能调用 reset，因为初始值可能是从 localStorage 加载的
      this.accessToken = undefined
    }
  }
})
