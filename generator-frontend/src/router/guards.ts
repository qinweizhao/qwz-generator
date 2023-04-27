// 路由守卫
import { useUserStore } from '@/store/user-store'
import { Router } from 'vue-router'

const routerGuards = (router: Router) => {
  router.beforeEach(async to => {
    const userStore = useUserStore()
    if (userStore.accessToken) {
      if (to.path === '/login') {
        return { path: '/' }
      }
    } else {
      if (to.path !== '/login') {
        return {
          path: 'login',
          query: {
            redirect: to.fullPath
          }
        }
      }
    }

    return true
  })
}

export default routerGuards
