import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getUserById(id) {
        return apiServer.get('/api/user/' + id);
    }
}