import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://192.168.100.49:9090/"
})

export default {
    getChatList(uid) {
        return apiServer.get('/api/chatList/'+uid);
    },
    getNotificationList(uid) {
        return apiServer.get('/api/chatNoti/' + uid);
    },
    async getNotification(uid, from){
        let chatList = await apiServer.get('/api/chatNoti/' + uid + '/' + from);
        return apiServer.put('/api/chatNoti/' + uid + '/' + from);
    }
    ,
    async insertOrUpdateNotification(uid, target, nickname) {
        let chatList = await apiServer.get('/api/chatNoti/' + uid + '/' + target);
        if(chatList.data.length == 0){
            chatList = { 'uid' : uid, 'target' : target, 'notification' : 1, 'nickname' : nickname};
            return apiServer.post('api/chatList', chatList)
        }else{
            chatList = chatList.data[0];
            chatList['notification']++;
            return apiServer.put('api/chatList', chatList);
        }
    },

    async checkNotification(uid, target){
        let chatList = await apiServer.get('/api/chatNoti/' + uid + '/' + target);
        if(chatList.data.length == 0){
            return;
        }else{
            console.log(chatList);
            chatList['data'][0]['notification'] = 0;
            return apiServer.put("api/chatList/",chatList.data[0])
        }
    }
}