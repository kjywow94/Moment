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
    }
    

    // getAccompanyRegistById(id) {
    //     return apiServer.get('/api/accompanyRegist/' + id);
    // },
    // insertAccompanyRegist(accompanyRegist) {
    //     return apiServer.post('/api/accompanyRegist/' , accompanyRegist);
    // },
    // updateAccompanyRegist(accompanyRegist) {
    //     return apiServer.put('/api/accompanyRegist/' , accompanyRegist);
    // }
}