const ABI = [
    {
        "constant": true,
        "inputs": [],
        "name": "getStartCount",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": false,
        "inputs": [
            {
                "name": "userId",
                "type": "uint256"
            },
            {
                "name": "_startDate",
                "type": "uint256"
            },
            {
                "name": "latitude",
                "type": "string"
            },
            {
                "name": "longitude",
                "type": "string"
            }
        ],
        "name": "setStart",
        "outputs": [],
        "payable": false,
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "startUserList",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [],
        "name": "isEnd",
        "outputs": [
            {
                "name": "",
                "type": "bool"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "startDate",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "endLongitude",
        "outputs": [
            {
                "name": "",
                "type": "string"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "endDate",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "endUserList",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "startLatitude",
        "outputs": [
            {
                "name": "",
                "type": "string"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [],
        "name": "accompanyId",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [],
        "name": "getEndCount",
        "outputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": false,
        "inputs": [
            {
                "name": "userId",
                "type": "uint256"
            },
            {
                "name": "_startDate",
                "type": "uint256"
            },
            {
                "name": "latitude",
                "type": "string"
            },
            {
                "name": "longitude",
                "type": "string"
            }
        ],
        "name": "setEnd",
        "outputs": [
            {
                "name": "",
                "type": "bool"
            }
        ],
        "payable": false,
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [],
        "name": "allStartUser",
        "outputs": [
            {
                "name": "",
                "type": "uint256[]"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "endLatitude",
        "outputs": [
            {
                "name": "",
                "type": "string"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [],
        "name": "allEndUser",
        "outputs": [
            {
                "name": "",
                "type": "uint256[]"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "startLongitude",
        "outputs": [
            {
                "name": "",
                "type": "string"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "inputs": [
            {
                "name": "_accompanyId",
                "type": "uint256"
            },
            {
                "name": "ownerId",
                "type": "uint256"
            },
            {
                "name": "_startDate",
                "type": "uint256"
            },
            {
                "name": "latitude",
                "type": "string"
            },
            {
                "name": "longitude",
                "type": "string"
            }
        ],
        "payable": false,
        "stateMutability": "nonpayable",
        "type": "constructor"
    }
];

const FACTORY_ABI = [
    {
        "constant": true,
        "inputs": [
            {
                "name": "",
                "type": "uint256"
            }
        ],
        "name": "accompanys",
        "outputs": [
            {
                "name": "",
                "type": "address"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": false,
        "inputs": [
            {
                "name": "_accompanyId",
                "type": "uint256"
            },
            {
                "name": "ownerId",
                "type": "uint256"
            },
            {
                "name": "_startDate",
                "type": "uint256"
            },
            {
                "name": "latitude",
                "type": "string"
            },
            {
                "name": "longitude",
                "type": "string"
            }
        ],
        "name": "createAccompany",
        "outputs": [
            {
                "name": "",
                "type": "address"
            }
        ],
        "payable": false,
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [],
        "name": "allAccompany",
        "outputs": [
            {
                "name": "",
                "type": "address[]"
            }
        ],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    }
];
import Web3 from "web3";
import { Transaction } from "ethereumjs-tx";
// var Tx = require('ethereumjs-tx');

import fs from "fs";

const TravelMakerNet = "http://13.209.9.195:3300";
const FACTORY_CONTRACT_ADDRESS = "0xa3E5b010Ce92f20562196c16f743db7026470463";
const web3 = new Web3(new Web3.providers.HttpProvider(TravelMakerNet));
const FactoryContract = new web3.eth.Contract(FACTORY_ABI, FACTORY_CONTRACT_ADDRESS);
const walletAddress = "0x1e5b2735b89EF05298bc18A92dBFDec7174Beb60";
const privateKey = '0x' + "2BE25D87F453C7A9357A2B9C4E0A98991025D45CF5200BF0EC8D1A28D67ABC80";
export default {
    /** 동행참가 처음 누르면 컨트랙트 배포 */
    deployContract(_accompanyId, ownerId, _startDate, latitude, longitude, callback) {
        var encodedABI = FactoryContract.methods.createAccompany(_accompanyId, ownerId, _startDate, latitude, longitude).encodeABI();
        var tx = {
            from: walletAddress,
            to: FACTORY_CONTRACT_ADDRESS,
            gas: "3000000",
            data: encodedABI,
            chainId: 30507
        }
        web3.eth.accounts.signTransaction(tx, privateKey).then(response => {
            web3.eth.sendSignedTransaction(response.rawTransaction).then(response => {
                FactoryContract.methods.allAccompany().call().then(response => {
                    var responseAddress = response[response.length - 1];
                    callback(responseAddress);
                });
            });
        });
    },
    /** 컨트랙트 생성 함수 */
    AccompanyContract(contractAddress) {
        var Contract = new web3.eth.Contract(ABI, contractAddress);
        return Contract;
    },
    /** 이미 동행 컨트랙트가 만들어져있으면 컨트랙트에 새 여행자 정보 추가하는 함수 */
    startAccompany(contractAddress, userId, _startDate, latitude, longitude, callback) {
        var contract = this.AccompanyContract(contractAddress);
        var encodedABI = contract.methods.setStart(userId, _startDate, latitude, longitude).encodeABI();

        var tx = {
            from: walletAddress,
            to: contractAddress,
            gas: "3000000",
            data: encodedABI,
            chainId: 30507
        }

        web3.eth.accounts.signTransaction(tx, privateKey).then(response => {

            web3.eth.sendSignedTransaction(response.rawTransaction).then(response => {
                callback();
            });
        });
    },
    /** 동행 종료 */
    endAccompany(contractAddress, userId, _endDate, latitude, longitude, callback) {
        var contract = this.AccompanyContract(contractAddress);
        contract.methods.setEnd(userId, _endDate, latitude, longitude).call().then(isEnd => {
            callback(isEnd);
        })
    },

    getStartUserList(contractAddress, callback) {
        var contract = this.AccompanyContract(contractAddress);
        contract.methods.allStartUser().call().then(startUserList => {
            callback(startUserList);
        })
    },
    getEndUserList(contractAddress, callback) {
        var contract = this.AccompanyContract(contractAddress);
        contract.methods.allEndUser().call().then(endUserList => {
            callback(endUserList);
        })
    },
    getUserInfo(contractAddress, userId, callback) {
        var contract = this.AccompanyContract(contractAddress);
        contract.methods.startDate(userId).call().then(startDate => {
            contract.methods.startLatitude(userId).call().then(startLatitude => {
                contract.methods.startLongitude(userId).call().then(startLongitude => {
                    contract.methods.endDate(userId).call().then(endDate => {
                        contract.methods.endLatitude(userId).call().then(endLatitude => {
                            contract.methods.endLongitude(userId).call().then(endLongitude => {
                                callback({
                                    startDate: startDate,
                                    endDate: endDate,
                                    startLatitude: startLatitude,
                                    startLongitude: startLongitude,
                                    endLatitude: endLatitude,
                                    endLongitude: endLongitude
                                })
                            })
                        })
                    })
                })
            })
        })
    }
}