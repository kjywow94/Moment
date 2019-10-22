import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getLog(id) {
        return apiServer.get('/api/log/');
    }
}