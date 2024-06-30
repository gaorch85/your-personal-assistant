import request from '@/utils/request'

export function api_predict(data) 
{
    return request({
      url: '/ai/request',
      method: 'post',
      data: data
    })
}


export function api_getTimes() 
{
    return request({
      url: '/ai/times',
      method: 'get'
    })
}