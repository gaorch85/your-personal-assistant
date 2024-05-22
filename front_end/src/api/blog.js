import request from '@/utils/request'

export function api_getAll(sortIndex) 
{
    return request({
      url: `/blog/list/${sortIndex}`,
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

export function api_delete(postId) 
{
    return request({
      url: `/blog/${postId}`,
      method: 'delete'
    })
}

export function api_update(postId) 
{
    return request({
      url: `/blog/${postId}`,
      method: 'put'
    })
}

export function api_getPostById(postId) 
{
    return request({
      url: `/blog/${postId}`,
      method: 'get'
    })
}

export function api_insert_favorite(postId) 
{
    return request({
      url: `/blog/favorite/${postId}`,
      method: 'post'
    })
}

export function api_delete_favorite(favoriteId) 
{
  return request({
    url: `/blog/favorite/${favoriteId}`,
    method: 'delete'
  })
}

export function api_insert_like(postId) 
{
    return request({
      url: `/blog/like/${postId}`,
      method: 'post'
    })
}

export function api_delete_like(likeId) 
{
    return request({
      url: `/blog/like/${likeId}`,
      method: 'delete'
    })
}

export function api_getCommentsById(postId) 
{
    return request({
      url: `/blog/comment/${postId}`,
      method: 'get'
    })
}

export function api_add_comment(postId, comment)
{
  return request({
    url: `/blog/comment/${postId}`,
    method: 'post',
    data: comment
  })
}

export function api_delete_comment(id)
{
  return request({
    url: `/blog/comment/${id}`,
    method: 'delete'
  })
}