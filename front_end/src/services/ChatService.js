import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
})

export default {
    getChats(roomNumber) {
        return apiServer.get('/api/chatAll/'+roomNumber);
    },
    getPrivateChats(uid1, uid2) {
        return apiServer.get('/api/chat/private/'+uid1 + '/' + uid2);
    },
    insertChat(chat) {
        return apiServer.post('/api/chat', chat);
    }
}