import axios from "axios";

export default {
    getAccompanyList(callback) {
        var result = [
            {
                num: "1",
                region: "유럽",
                title: "프라하 파티 구함 프라하 파티 구함 프라하 파티 구함 프라하 파티 구함",
                writer: "참치참치chamchi",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "2",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "3",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "4",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "5",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "6",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "7",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "8",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "9",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "10",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "11",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "12",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "13",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "14",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "15",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "16",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "17",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "18",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "19",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "20",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "21",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "22",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "23",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "31",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "32",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "33",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "34",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "35",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "36",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "37",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "38",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "39",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "310",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "311",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "312",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "313",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "314",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "315",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "316",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "137",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "318",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }, {
                num: "319",
                region: "유럽",
                title: "프라하 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "320",
                region: "아시아",
                title: "방콕 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "321",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "322",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            },
            {
                num: "323",
                region: "북미",
                title: "벤쿠버 파티 구함",
                writer: "참치",
                date: "2019-10-19",
                views: "30"
            }
        ]
        callback(result);
    },
    getAccompany(callback){
        var result = {
            num: "323",
            region: "북미",
            title: "벤쿠버 파티 구함",
            writer: "참치",
            date: "2019-10-19",
            views: "30"
        };
        callback(result);
    }
}