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
    getUserByEmail(email) {
        return apiServer.get('/api/user/email/' + email);
    },
    getAllUsers() {
        return apiServer.get('/api/userAllAdmin')
    },
    signUp(user) {
        return apiServer.post('/api/user', user);
    },
    uploadImage(userImage) {
        return apiServer.post('api/user/image', userImage);
    },
    Login(email, password) {
        var body = {
            email : email,
            password : password
        }

        return apiServer.post('/api/login', body)
    },
    update(password, body){
        
        var temp = body;
        temp.password = password;
        return apiServer.put("/api/user", temp);
    }
}
