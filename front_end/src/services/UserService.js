import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getUserById(id) {
        return apiServer.get('/api/user/' + id);
    },
    getAllUsers() {
        return apiServer.get('/api/userAllAdmin')
    },
    signUp(user) {
        return apiServer.post('/api/user', user);
    },
    uploadImage(userImage) {
        return apiServer.post('api/user/image', userImage);
    }
}