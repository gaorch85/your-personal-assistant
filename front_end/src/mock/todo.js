import Mock from 'mockjs'

const base_url = 'http://mock'


Mock.mock(base_url + '/todo', 'get', {

        success: true,
        code: 20000,
        message: 'success',
        data:{
            'items|5': [{
                'id|+1': 1,
                'topic': 'mock',
                'details': 'mockc details',
                'deadline': '2000-01-01 00:00:00',
                'status': false
              }]
        }
});


Mock.mock(base_url + '/todo', 'post',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});

Mock.mock(base_url + '/todo', 'put',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});


Mock.mock(RegExp(base_url + '/todo.*'), 'delete', {
    success: true,
    code: 20000,
    message: 'success'
});


