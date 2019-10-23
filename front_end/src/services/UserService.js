import axios from "axios";
import router from "../router";


const UNAUTHORIZED = 401
const onUnauthorized = () => {
    router.push('/login?rPath=${encodeURIComponent(location.pathname)}')
}

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getUserById(id) {
        return apiServer.get('/api/user/' + id);
    },
    Login(email, password) {
        var body = {
            email : email,
            password : password
        }

        return apiServer.post('/api/login', body)
    }
}

export const auth = {
    getAllUsers(){
        return apiServer.get('/api/userAllAdmin')
    },
    Login(email, password) {
        return Request('post', '/api/login', {email, password})
    }
}
