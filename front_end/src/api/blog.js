import request from '@/utils/request'

export function api_getAll() 
{
    return request({
      url: '/blog',
      method: 'get'
    })
}

export function api_insert(data) 
{
    return request({
      url: '/blog',
      method: 'post',
      data: data
    })
}