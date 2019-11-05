import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
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
    },
    getReviewByEmail(email) {
        return apiServer.post('/api/reviewByEmail/' + email);
    }
}