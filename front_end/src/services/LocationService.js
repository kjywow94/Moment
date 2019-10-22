import axios from "axios";

const apiServer = axios.create({
    baseURL: "http://localhost:9090"
})

export default {
    getLocation() {
        return navigator.geolocation.getCurrentPosition();
    },
    // 위도
    // var latitude = pos.coords.latitude;
    // // 경도
    // var longitude = pos.coords.longitude;

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