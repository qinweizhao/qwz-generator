import axios from 'axios'
import type { InternalAxiosRequestConfig, AxiosResponse } from 'axios'
import notification from 'ant-design-vue/es/notification'
import type { BAxiosError } from '@/utils/axios/types'
import { useUserStore } from '@/store/user-store'
import { R } from '@/utils/axios/types'

// 创建 axios 实例
const axiosInstance = axios.create({
  baseURL: '/api', // api base_url
  timeout: 6000 // 请求超时时间
})

// 请求失败处理函数
const onRejected = (error: BAxiosError) => {
  if (error.response) {
    const data = error.response.data as unknown as R
    if (error.response.status === 403) {
      notification.error({
        message: 'Forbidden',
        description: data.message
      })
      error.resolved = true
    }
    if (error.response.status === 401) {
      notification.error({
        message: 'Unauthorized',
        description: 'Authorization verification failed'
      })
      error.resolved = true
    }
  }
  return Promise.reject(error)
}

// 将 token 放进请求头
function setTokenToHeader(config: InternalAxiosRequestConfig) {
  const headers = config.headers || {}

  // token
  const { accessToken } = useUserStore()
  // Authorization 请求头不存在再进行追加
  if (accessToken && !headers['Authorization']) {
    // 让每个请求携带自定义 token 请根据实际情况自行修改
    headers['Authorization'] = 'Bearer ' + accessToken
  }
}

// request interceptor
axiosInstance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  setTokenToHeader(config)
  return config
}, onRejected)

// response interceptor
axiosInstance.interceptors.response.use((response: AxiosResponse) => {
  const headers = response.headers
  if (
    headers != null &&
    headers['content-type'] &&
    headers['content-type'].startsWith('application/json')
  ) {
    return response.data
  } else {
    return response
  }
}, onRejected)

export default axiosInstance
