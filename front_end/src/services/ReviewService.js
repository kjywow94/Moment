import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

/** Review */
export default {

    getReviewListByLocation(location) {
        return apiServer.post('/api/review/', location);
    },
    getReview(id) {
        return apiServer.get('/api/review/' + id);
    },
    getReviewByUser(uid) {
        return apiServer.get('/api/reviewByUser/' + uid);
    },
    getAllReview() {
        return apiServer.get('/api/review');
    },
    writeReview(review) {
        return apiServer.post('/api/writeReview/', review);
    },
    likeIt(id) {
        return apiServer.get('/api/likeIt/' + id);
    },
    uploadImage(image) {
        return apiServer.post('api/reviewImg/', image);
    }
}