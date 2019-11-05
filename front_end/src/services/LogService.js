import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
})

export default {
    getLog(id) {
        return apiServer.get('/api/log/');
    }
}