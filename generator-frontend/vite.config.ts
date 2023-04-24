import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite'
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers'
import { createStyleImportPlugin, AndDesignVueResolve } from 'vite-plugin-style-import'
import { resolve } from 'path'

const serverAddress = 'http://127.0.0.1:7777'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src')
    }
  },
  plugins: [
    vue(),
    // 组件按需引入
    Components({
      resolvers: [
        AntDesignVueResolver({
          importStyle: 'less'
        })
      ]
    }),
    // 样式按需引入
    createStyleImportPlugin({
      resolves: [AndDesignVueResolve()],
      libs: [
        {
          libraryName: 'ant-design-vue',
          esModule: true,
          resolveStyle: name => {
            return `ant-design-vue/es/${name}/style/index`
          }
        }
      ]
    })
  ],
  server: {
    proxy: {
      '/api': {
        target: serverAddress,
        changeOrigin: true
      }
    }
  },
  css: {
    preprocessorOptions: {
      less: {
        javascriptEnabled: true,
        modifyVars: {
          'primary-color': '#9f6ae0',
          'border-radius-base': '10px',
          'btn-border-radius-base': '30px',
          'checkbox-border-radius': '3px'
        }
      }
    }
  },
  optimizeDeps: {
    include: ['ant-design-vue', 'ant-design-vue/es', '@ant-design/icons-vue']
  },
  build: {
    outDir: 'target/dist',
    assetsDir: 'static'
  }
})
