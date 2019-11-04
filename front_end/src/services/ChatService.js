import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://192.168.100.49:9090/"
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