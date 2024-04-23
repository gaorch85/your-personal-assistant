import Mock from 'mockjs'

const base_url = 'http://mock'

Mock.mock(base_url + '/login', 'post',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success',
        data:{
            token:'mocktoken'
        }
    }
});

Mock.mock(base_url + '/signup', 'post',(req)=> {
    return {
        success: true,
        code: 20000,
        message: 'success'
    }
});


