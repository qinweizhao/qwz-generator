import { defineStore } from 'pinia'
import { StorageSerializers, useLocalStorage } from '@vueuse/core'
import type { LoginUserInfo } from '@/api/auth/types'

export interface UserInfo extends LoginUserInfo {
  roleCodes: string[]
  permissions: string[]
}

const accessTokenKey = 'StorageKey-access-token'
const userInfoKey = 'StorageKey-user-info'

export const useUserStore = defineStore('userStore', {
  // 其他配置...
  state: () => ({
    accessToken: useLocalStorage<string | undefined>(accessTokenKey, undefined, {
      writeDefaults: false
    }),
    userInfo: useLocalStorage<UserInfo | undefined>(userInfoKey, undefined, {
      writeDefaults: false,
      serializer: StorageSerializers.object
    })
  }),
  actions: {
    clean() {
      // 不能调用 reset，因为初始值可能是从 localStorage 加载的
      this.accessToken = undefined
      this.userInfo = undefined
    }
  }
})
