import request from "@/utils/request"

export function api_login(data) {
    return request({
      url: '/login',
      method: 'post',
      data
    })
}

export function api_signup(data) {
    return request({
      url: '/signup',
      method: 'post',
      data
    })
}

