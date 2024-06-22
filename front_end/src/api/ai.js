import request from '@/utils/request'

export function api_predict(data) 
{
    return request({
      url: '/ai',
      method: 'post',
      data: data
    })
}