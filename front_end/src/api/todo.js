import request from '@/utils/request'

export function api_getAll() 
{
    return request({
      url: '/todo',
      method: 'get'
    })
}

export function api_insert(data) 
{
    return request({
      url: '/todo',
      method: 'post',
      data
    })
}

export function api_update(data) 
{
    return request({
      url: '/todo',
      method: 'put',
      data
    })
}

export function api_delete(id) {
    return request({
        url: `/todo/${id}`, // 将 ID 参数添加到 URL 中
        method: 'delete'
    })
}