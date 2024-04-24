import Mock from 'mockjs'

const base_url = 'http://mock'

Mock.mock(base_url + '/getInfo', 'get', {

        success: true,
        code: 20000,
        message: 'success',
        data:{
            'items': {
                'id|+1': 1,
                'username': 'mock',
                'phone_name': '110',
                'email': '110.eamail',
                'isVip': false
              }
            }
});

Mock.mock(base_url + '/user', 'delete', {

        success: true,
        code: 20000,
        message: 'success',
});

Mock.mock(base_url + '/user/update', 'put',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});

Mock.mock(base_url + '/changePassword', 'put',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});