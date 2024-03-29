<template>
  <div :class="['top-nav-header', theme]">
    <div class="top-nav-header-main">
      <div class="top-nav-header-main-left">
        <div class="top-nav-header-logo">
          <a>
            <!-- <img src="../assets/logo.svg" alt="logo" /> -->
            <h1>Generator</h1>
          </a>
        </div>
      </div>
      <div :style="{ flex: 1 }" class="top-nav-header-menu">
        <router-menu mode="horizontal" />
      </div>

      <div class="top-nav-header-right-content" style="padding-right: 6px">
        <a-space class="right" :size="0">
          <span class="action" @click="loginOut">
            <LogoutOutlined />
            <span>退出</span>
          </span>
          <!--          <span class="action" @click="jumpToGitee">-->
          <!--            <img src="../assets/gitee.svg" alt="gitee" />-->
          <!--            <span>Gitee</span>-->
          <!--          </span>-->
        </a-space>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
  import RouterMenu from '@/components/menu'
  import { Modal } from 'ant-design-vue'
  import { useRouter } from 'vue-router'
  import { useUserStore } from '@/store/user-store'
  import { logout } from '@/api/auth'

  defineProps<{
    theme: 'dark' | 'light'
  }>()

  const jumpToGithub = () => {
    window.open('https://github.com/qinweizhao/qwz-generator')
  }

  const jumpToGitee = () => {
    window.open('https://gitee.com/qinweizhao/qwz-generator')
  }

  const router = useRouter()

  const userStore = useUserStore()

  function loginOut() {
    Modal.confirm({
      title: '提示',
      content: '确定要退出登录吗 ?',
      okText: '确认',
      cancelText: '取消',
      onOk: () => {
        // 没有 accessToken 的话，直接登出
        if (!userStore.accessToken) {
          setTimeout(() => {
            router.push('/login')
          }, 200)
          return
        }
        // 有 accessToken 的话，就先执行登出操作
        logout().then(() => {
          userStore.clean()
          setTimeout(() => {
            router.push('/login')
          }, 200)
        })
      }
    })
  }
</script>

<style scoped lang="less">
  @import (reference) 'ant-design-vue/es/style/themes/index.less';
  @top-nav-header-prefix-cls: ~'top-nav-header';
  @pro-header-hover-bg: rgba(0, 0, 0, 0.025);

  .@{top-nav-header-prefix-cls} {
    position: relative;
    width: 100%;
    height: 100%;
    box-shadow: 0 1px 4px 0 rgba(0, 21, 41, 0.12);
    transition: background 0.3s, width 0.2s;

    .@{ant-prefix}-menu {
      background: transparent;
    }

    &.light {
      background-color: @component-background;
      .@{top-nav-header-prefix-cls}-logo {
        h1 {
          color: #9f6ae0;
        }
      }
      .anticon {
        color: inherit;
      }
    }

    &-main {
      display: flex;
      height: 100%;
      padding-left: 16px;
      &-left {
        display: flex;
        min-width: 208px;
      }
    }

    .anticon {
      color: #9f6ae0;
    }

    &-logo {
      position: relative;
      min-width: 165px;
      height: 100%;
      overflow: hidden;

      img,
      a > svg {
        display: inline-block;
        height: 32px;
        vertical-align: middle;
        margin-bottom: 4px;
      }

      h1 {
        display: inline-block;
        margin: 0 0 0 8px;
        color: @btn-primary-color;
        font-size: 18px;
        vertical-align: top;
      }
    }

    &-menu {
      min-width: 0;
      .@{ant-prefix}-menu.@{ant-prefix}-menu-horizontal {
        height: 100%;
        border: none;
      }
    }
  }

  .right {
    display: flex;
    float: right;
    height: 48px;
    margin-left: auto;
    overflow: hidden;
    .action {
      color: #9f6ae0;
      display: flex;
      align-items: center;
      height: 48px;
      padding: 0 12px;
      cursor: pointer;
      transition: all 0.3s;
      > span {
        vertical-align: middle;
        font-weight: 600;
        padding-left: 5px;
      }
      &:hover {
        background: @pro-header-hover-bg;
      }
      &:global(.opened) {
        background: @pro-header-hover-bg;
      }
    }
  }
</style>
