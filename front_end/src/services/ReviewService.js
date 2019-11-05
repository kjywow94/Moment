import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
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
    uploadImage(image) {
        return apiServer.post('api/reviewImg/', image);
    },
    likeIt(like) {
        return apiServer.post('/api/likeIt/', like);
    },
    unLike(like) {
        return apiServer.post('/api/unLike/', like);
    },
    isLike(like) {
        return apiServer.post('/api/isLike', like);
    }
}