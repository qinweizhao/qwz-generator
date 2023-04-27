import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { BallcatRouteRecordRaw } from '@/router/types'
import routerGuards from '@/router/guards'

const menuRouters: Array<BallcatRouteRecordRaw> = [
  {
    path: '/codegen',
    name: 'CodeGen',
    meta: { title: '代码生成' },
    component: () => import('@/views/gen/codegen/index.vue')
  },
  {
    path: '/template/group',
    name: 'TemplateGroup',
    meta: { title: '模板管理' },
    component: () => import('@/views/gen/template-group/index.vue')
  },
  {
    path: '/datasouce',
    name: 'DataSource',
    meta: { title: '数据源管理' },
    component: () => import('@/views/gen/datasource-config/index.vue')
  },
  {
    path: '404',
    name: 'notFound',
    component: () => import('../views/404.vue'),
    meta: { hiddenInMenu: true }
  }
]
const routes: Array<BallcatRouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../layouts/BasicLayout.vue'),
    children: [...menuRouters],
    redirect: '/codegen'
  },
  {
    path: '/login',
    name: 'Login',
    // route level code-splitting
    // this generates a separate chunk (About.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    meta: { title: '登陆' },
    component: () => import('@/views/login/index.vue')
  },
  {
    path: '/:catchAll(.*)',
    name: '404',
    // hiddenInMenu: true,
    component: () => import('../views/404.vue')
  }
]

const router = createRouter({
  history: createWebHistory('/'),
  routes: routes as RouteRecordRaw[]
})

export { menuRouters }

routerGuards(router)

export default router
