import Mock from 'mockjs'

Mock.mock('http://localhost:8090/todo', 'get', {

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

Mock.mock('http://localhost:8090/login', 'post',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success',
        data:{
            token:'mocktoken'
        }
    }
});

Mock.mock('http://localhost:8090/employee', 'get', {

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


Mock.mock('http://localhost:8090/getInfo', 'get', {

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

