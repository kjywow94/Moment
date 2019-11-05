import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
})

export default {
    getMyAccompanyListbyUid(uid) {
        return apiServer.get('/api/accompanyParti/' + uid);
    },
    getMyAccompanyListbyTid(tid) {
        return apiServer.get('/api/accompanyPartiByTid/' + tid);
    },
    insertAccompanyParti(accompanyParti) {
        return apiServer.post('/api/accompanyParti/', accompanyParti);
    },
    updateAccompanyParti(accompanyParti) {
        return apiServer.put('/api/accompanyParti/', accompanyParti);
    },
}