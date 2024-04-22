import request from "@/utils/request"

export function api_deactivateAccount() {
    return request({
      url: `/user`,
      method: 'delete'
    })
}

export function api_getInfo() {
    return request({
      url: `/getInfo`,
      method: 'get'
    })
}

export function api_update(data) {
    return request({
      url: `/user/update`,
      method: 'post',
      data
    })
}

export function api_changePassword(data) {
  return request({
    url: `/changePassword`,
    method: 'put',
    data
  })
}
    



