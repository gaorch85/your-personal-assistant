import request from '@/utils/request'

export function api_getAll() 
{
    return request({
      url: '/employee',
      method: 'get'
    })
}

export function api_insert(data) 
{
    return request({
      url: '/employee',
      method: 'post',
      data
    })
}

export function api_update(data) 
{
    return request({
      url: '/employee',
      method: 'put',
      data
    })
}

export function api_delete(id) {
    return request({
        url: `/employee/${id}`, // 将 ID 参数添加到 URL 中
        method: 'delete'
    })
}