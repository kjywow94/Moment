import axios from "axios";

const apiServer = axios.create({
    baseURL: API_BASE_URL
})

export default {
    getLocation(callback) {
        navigator.geolocation.getCurrentPosition(pos => {
            // 위도
            var latitude = pos.coords.latitude;
            // 경도
            var longitude = pos.coords.longitude;
            callback(latitude, longitude);
        });
    },


    computeDistance(startCoords, destCoords) {
        var startLatRads = degreesToRadians(startCoords.latitude);
        var startLongRads = degreesToRadians(startCoords.longitude);
        var destLatRads = degreesToRadians(destCoords.latitude);
        var destLongRads = degreesToRadians(destCoords.longitude);

        var Radius = 6371; //지구의 반경(km)
        var distance = Math.acos(Math.sin(startLatRads) * Math.sin(destLatRads) +
            Math.cos(startLatRads) * Math.cos(destLatRads) *
            Math.cos(startLongRads - destLongRads)) * Radius;

        return distance;
    },

    degreesToRadians(degrees) {
        radians = (degrees * Math.PI) / 180;
        return radians;
    }
}