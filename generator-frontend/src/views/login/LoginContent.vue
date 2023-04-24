<template>
  <div :class="getCls('container')">
    <div :class="getCls('top')">
      <div :class="getCls('header')">
        <!-- 标题 -->
        <span :class="getCls('title')"> Sign in </span>
      </div>
      <!-- 描述 -->
      <div :class="getCls('desc')">代码生成器</div>
    </div>

    <div :class="getCls('main')" style="width: 368px">
      <a-tabs>
        <a-tab-pane key="account" tab="登录"></a-tab-pane>
      </a-tabs>

      <!-- 错误提示信息 -->
      <a-alert
        v-if="isLoginError"
        style="margin-bottom: 24px"
        :message="loginErrorMessage"
        type="error"
        show-icon
      />

      <!-- 账户密码登录 -->
      <account-login-form ref="accountLoginFormRef" @try-submit="handleLogin" />

      <div style="margin-bottom: 24px">
        <a-checkbox v-model:checked="rememberMe" no-style name="autoLogin"> 记住密码</a-checkbox>
      </div>

      <a-button
        size="large"
        type="primary"
        style="width: 100%"
        :loading="loginLoading"
        @click="handleLogin"
      >
        登陆
      </a-button>

      <!-- 扩展部分 -->
      <div :class="getCls('other')">
        <a style="float: right"> SSO 登陆</a>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  import AccountLoginForm from '@/views/login/AccountLoginForm.vue'
  import type { LoginResult, LoginFormInstance } from '@/api/auth/types'

  import { useUserStore } from '@/store/user-store'
  const prefixCls = 'ant'
  const baseClassName = 'pro-login-content'

  function getCls(className: string) {
    return `${prefixCls}-${baseClassName}-${className}`
  }

  // 登录的加载状态
  const loginLoading = ref(false)
  // 登陆错误
  const isLoginError = ref(false)
  // 登录错误信息
  const loginErrorMessage = ref('')
  // 自动登录（记住我）
  const rememberMe = ref(false)

  // 当前登录类型，以及对应的登录组件
  let loginFormRef = ref<LoginFormInstance>()
  const accountLoginFormRef = ref<LoginFormInstance>()
  loginFormRef = accountLoginFormRef

  console.log('loginFormRef')
  console.log(loginFormRef)
  console.log('accountLoginFormRef')
  console.log(accountLoginFormRef)

  /** 存储登录信息 */
  function store(res: LoginResult) {
    const userStore = useUserStore()
    // 存储 token
    userStore.accessToken = res.access_token
  }

  function handleLogin() {
    const loginFormInstance = loginFormRef.value!
    loginFormInstance.validate().then(() => {
      handleSubmit()
    })
  }

  const router = useRouter()

  function handleSubmit(captchaId?: string) {
    const loginFormInstance = loginFormRef.value!
    loginLoading.value = true
    return loginFormInstance
      .doLogin(captchaId)
      .then(res => {
        isLoginError.value = false
        store(res)
        const nextPath = (router.currentRoute.value.query.redirect as string) || '/'
        router.push(nextPath)
      })
      .catch(err => {
        isLoginError.value = true
        loginErrorMessage.value =
          ((err.response || {}).data || {}).message || '请求出现错误，请稍后再试'
      })
      .finally(() => {
        loginLoading.value = false
      })
  }
</script>

<style lang="less">
  @import (reference) '../../../node_modules/ant-design-vue/es/style/themes/index.less';

  .login-tabs .ant-tabs-tab {
    padding: 12px 16px !important;
  }

  @pro-form-login-prefix-cls: ~'@{ant-prefix}-pro-login-content';

  .@{pro-form-login-prefix-cls}-container {
    display: flex;
    flex: 1;
    flex-direction: column;
    height: 100%;
    padding: 32px 0;
    overflow: auto;
    background: inherit;
  }

  @media (min-width: @screen-md-min) {
    .@{pro-form-login-prefix-cls}-container {
      padding: 32px 0 24px;
      background-repeat: no-repeat;
      background-position: center 110px;
      background-size: 100%;
    }
  }

  .@{pro-form-login-prefix-cls}-top {
    text-align: center;
  }

  .@{pro-form-login-prefix-cls}-header {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 44px;
    line-height: 44px;
    a {
      text-decoration: none;
    }
  }

  .@{pro-form-login-prefix-cls}-title {
    position: relative;
    top: 2px;
    color: @heading-color;
    font-weight: 600;
    font-size: 33px;
  }

  .@{pro-form-login-prefix-cls}-logo {
    width: 44px;
    height: 44px;
    margin-right: 16px;
    vertical-align: top;

    img {
      width: 100%;
    }
  }

  .@{pro-form-login-prefix-cls}-desc {
    margin-top: 12px;
    margin-bottom: 40px;
    color: @text-color-secondary;
    font-size: @font-size-base;
  }

  .@{pro-form-login-prefix-cls}-main {
    min-width: 328px;
    max-width: 500px;
    margin: 0 auto;

    .@{ant-prefix}-tabs-nav-list {
      margin: 0 auto;
      font-size: 16px;
    }

    .@{pro-form-login-prefix-cls}-other {
      margin-top: 24px;
      line-height: 22px;
      text-align: left;
    }
  }
</style>
