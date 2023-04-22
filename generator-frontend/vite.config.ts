import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite'
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers'
import { createStyleImportPlugin, AndDesignVueResolve } from 'vite-plugin-style-import'
import { resolve } from 'path'

// 自动导入 vue 组件，无需手动 import
import AutoImport from 'unplugin-auto-import/vite'

const serverAddress = 'http://127.0.0.1:7777'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src')
    }
  },
  plugins: [
    // 自动导入 vue
    AutoImport({
      // global imports to register
      imports: [
        // presets
        'vue',
        'vue-router',
        'pinia'
      ],
      // Generate corresponding .eslintrc-auto-import.json file.
      // eslint globals Docs - https://eslint.org/docs/user-guide/configuring/language-options#specifying-globals
      eslintrc: {
        enabled: true, // Default `false`
        filepath: './.eslintrc-auto-import.json', // Default `./.eslintrc-auto-import.json`
        globalsPropValue: true // Default `true`, (true | false | 'readonly' | 'readable' | 'writable' | 'writeable')
      },
      // Filepath to generate corresponding .d.ts file.
      // Defaults to './auto-imports.d.ts' when `typescript` is installed locally.
      // Set `false` to disable.
      dts: './types/auto-imports.d.ts'
    }),

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
