import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    /** AccompanyRegist */
    getAccompanyRegistList() {
        return apiServer.get('/api/accompanyRegist/');
    },
    getAccompanyRegistListAdmin() {
        return apiServer.get('/api/accompanyRegistAdmin/');
    },
    getAccompanyRegistById(id) {
        return apiServer.get('/api/accompanyRegist/' + id);
    },
    insertAccompanyRegist(accompanyRegist) {
        return apiServer.post('/api/accompanyRegist/', accompanyRegist);
    },
    updateAccompanyRegist(accompanyRegist) {
        return apiServer.put('/api/accompanyRegist/', accompanyRegist);
    },
    successAccompanyRegist(id) {
        return apiServer.get('/api/accompanyRegistSuccess/' + id);
    },

    /** Accompany */
    getAccompanyByCid(cid) {
        return apiServer.get('/api/accompany/' + cid);
    },
    insertAccompany(accompany) {
        return apiServer.post('/api/accompany/', accompany);
    },
    endAccompany(id) {
        return apiServer.put('/api/accompany/', { id: id });
    }



}