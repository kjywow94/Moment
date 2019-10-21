import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getMyAccompanyList(id) {

    }
    // getAccompanyRegistList() {
    //     return apiServer.get('/api/accompanyRegist/');
    // },
    // getAccompanyRegistListAdmin() {
    //     return apiServer.get('/api/accompanyRegistAdmin/');
    // },
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