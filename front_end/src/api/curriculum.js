import request from '@/utils/request'


export function api_getCurriculum() {
    return request({
      url: `/curriculum`,
      method: 'get'
    })
}

export function api_changeCurriculum(data) {
    return request({
      url: `/curriculum`,
      method: 'post',
      data: data
    })
}

export function api_getCourses() {
    return request({
      url: `/curriculum/course`,
      method: 'get'
    })
}

export function api_addCourse(course) {
    return request({
      url: `/curriculum/course`,
      method: 'post',
      data: course
    })
}

export function api_deleteCourse(id) {
    return request({
      url: `/curriculum/course/${id}`,
      method: 'delete'
    })
}