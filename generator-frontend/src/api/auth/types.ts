import { validateOptions } from 'ant-design-vue/lib/form/useForm'

/**
 * 用户名密码登录所需参数
 */
export interface AccountLoginParam {
  username: string
  password: string
  captchaId?: string
}

/**
 * 手机号登录所需参数
 */
export interface MobileLoginParam {
  mobile?: string
  captcha?: string
}

/**
 * OAuth2 登录需要的参数
 */
export type OAuth2LoginParam = {
  grant_type: string
} & (AccountLoginParam | MobileLoginParam)

/**
 * 登录成功时的返回结果
 */
export interface LoginResult {
  access_token: string
  expires_in: number
  refresh_token: string
  scope: string
  token_type: string
}

declare type namesType = string | string[]

export interface LoginFormInstance {
  validate: <T = any>(names?: namesType, option?: validateOptions) => Promise<T>
  doLogin: (captchaId?: string) => Promise<LoginResult>
}
