import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
})

export default {
    getAllReview() {
        return apiServer.get('/api/travelReviewAdmin/');
    },
    getReviews() {
        return apiServer.get('/api/travelReview/');
    },
    getReview(id) {
        return apiServer.get('/api/travelReview/' + id);
    },
    updateReview(review) {
        return apiServer.put('/api/travelReview/', review);
    },
    deleteReview(id){
        return apiServer.delete('/api/travelReview/' + id);
    },
    insertReview(review){
        return apiServer.post('/api/travelReview/', review);
    },
}