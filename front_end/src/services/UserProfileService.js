import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getUserProfile(id) {
        return apiServer.get('/api/user/' + id);
    },
    getUserReview(id) {
        return apiServer.get('/api/userReviewUid/' + id);
    },
    getUserImage(email) {
        return apiServer.get('api/user/image/' + email);
    }
}