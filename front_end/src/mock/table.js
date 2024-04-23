import Mock from 'mockjs'

const base_url = 'http://mock'

Mock.mock(base_url + '/employee', 'get', {

        success: true,
        code: 20000,
        message: 'success',
        data:{
            'items|5': [{
                'id|+1': 1,
                'name': 'mock',
                'gender': '男',
                'birthday': '2000-01-01',
                'address': 'mock address',
              }]
            }
});


Mock.mock(base_url + '/employee', 'post',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});

Mock.mock(base_url + '/employee', 'put',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});

// const urlRegex = new RegExp(`^${base_url}/employee/\\d+$`);

Mock.mock(RegExp(base_url + '/employee.*'), 'delete', {
    success: true,
    code: 20000,
    message: 'success'
});


