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
        return apiServer.post('/api/user/image', userImage);
    },
    delteImage(email) {
        return apiServer.delete('/api/user/image/' + email);
    },
    getImage(email){
        return apiServer.get('/api/user/image/' + email);
    },
    Login(email, password) {
        var body = {
            email : email,
            password : password
        }

        return apiServer.post('/api/login', body)
    },
    passwordupdate(password, body){
        
        var temp = body;
        temp.password = password;
        return apiServer.put("/api/user", temp);
    },
    findid(userName, phone){
        var body = {
            userName : userName,
            phone : phone
        }

        return apiServer.post("/api/user/findId", body)
    },
    findpassword(email, phone){
        var body = {
            email : email,
            phone : phone
        }

        return apiServer.post("/api/user/findPass", body)
    },
    userupdate(name, phone, body){
        var temp = body;
        temp.name = name;
        temp.phone = phone;
        return apiServer.put("/api/user", temp);
    },
    userprofile(aboutme, instagram, facebook, body){
        var temp = body;
        temp.about = aboutme;
        temp.sns1 = instagram;
        temp.sns2 = facebook;
        return apiServer.put("/api/user", temp);
    }
}
