import axios from "axios";
import { request } from "https";
import router from "../router";
import { async } from "q";

const UNAUTHORIZED = 401
const onUnauthorized = () => {
    router.push('/login?rPath=${encodeURIComponent(location.pathname)}')
}

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

const Request = async (method, url, data) =>{
    return axios({
        method,
        url: apiServer + url,
        data
    }).then(result => result.data)
        .catch(result => {
            const {status} = result.response
            if(status === UNAUTHORIZED) onUnauthorized()
            throw result.response
        })
}

export default {
    getUserById(id) {
        return apiServer.get('/api/user/' + id);
    },
    login(email, password) {
        var body = {
            "이메일": email,
            "비밀번호": password
        }
    return apiServer.post('/api/login', body);
}

}

export const auth = {

}
