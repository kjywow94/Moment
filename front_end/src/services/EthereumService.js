import Web3 from "web3";

const TravelMakerNet = "http://13.209.9.195:3300";
const web3 = new Web3(new Web3.providers.HttpProvider(TravelMakerNet));

export default {
    // EthereumService.write(
    //   "0x1e5b2735b89ef05298bc18a92dbfdec7174beb60",
    //   "ssafy",
    //   data,
    //   receipt => {
    //     console.log("트랜잭션 주소 : ", receipt.transactionHash);
    //   }
    // );
    write(walletAddress, password, data, callback) {
        var tx = {
            from: walletAddress,
            to: walletAddress,
            value: '10',
            data: web3.utils.utf8ToHex(data)
        }

        web3.eth.personal.unlockAccount(walletAddress, password, 600, () => {
            web3.eth.sendTransaction(tx).then(receipt => {
                callback(receipt)
            });
        });
    },
    charge(walletAddress, callback) {
        let admin = "0x1e5b2735b89ef05298bc18a92dbfdec7174beb60";
        let adminPw = "ssafy";
        var tx = {
            from: admin,
            to: walletAddress,
            value: web3.utils.toWei('10', 'ether'),
        }
        web3.eth.personal.unlockAccount(admin, adminPw, 600, () => {
            web3.eth.sendTransaction(tx).then(receipt => {
                callback(receipt)
            });
        });
    },

    sendPoint(walletAddress, point, callback) {
        let admin = "0x1e5b2735b89ef05298bc18a92dbfdec7174beb60";
        let adminPw = "ssafy";
        var tx = {
            from: admin,
            to: walletAddress,
            value: web3.utils.toWei(String(point), 'ether'),
        }
        web3.eth.personal.unlockAccount(admin, adminPw, 600, () => {
            web3.eth.sendTransaction(tx).then(receipt => {
                callback(receipt)
            });
        });
    },

    // EthereumService.read(
    //   "0xb841fb885e2b2f9ada4f9a42c95616bef9785913c6a779633c001daec9f715ca",
    //   data => {
    //     console.log("read Data");
    //     console.log(data);
    //   }
    // );
    read(transactionHash, callback) {
        web3.eth.getTransaction(transactionHash).then(transaction => {
            var input = transaction.input;
            var data = web3.utils.hexToUtf8(input);
            callback(data);
        })
    },
    // EthereumService.newAccount("ssafy", walletAddress => {
    //   console.log("지갑 생성");
    //   console.log(walletAddress);
    // });
    newAccount(password, callback) {
        web3.eth.personal.newAccount(password).then(walletAddress => {
            this.charge(walletAddress, receipt => {
            });
            callback(walletAddress);
        });
    }
}