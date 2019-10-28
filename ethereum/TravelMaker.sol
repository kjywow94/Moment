pragma solidity ^0.4.24;

contract TravelMakerFactory {
    
    address[] public accompanys;
    
        constructor() public {
    }

    
    function createAccompany(uint _accompanyId, uint ownerId, uint _startDate, string latitude, string longitude) public returns (address) {
        
        TravelMaker accompany = new TravelMaker(_accompanyId, ownerId, _startDate, latitude, longitude);
        accompanys.push(accompany);
        return accompany;
    }
        function allAccompany() public constant returns (address[]) {
        return accompanys;
    }
}


contract TravelMaker {

    uint[] public startUserList;
    uint[] public endUserList;
    uint public accompanyId;
    mapping(uint => string)  public startLatitude;
    mapping(uint => string)  public startLongitude;
    mapping(uint => string)  public endLatitude;
    mapping(uint => string)  public endLongitude;
    mapping(uint => uint) public startDate;
    mapping(uint => uint) public endDate;
    bool public isEnd;

  constructor(uint _accompanyId, uint ownerId, uint _startDate, string latitude, string longitude) public {
        accompanyId = _accompanyId;
        startUserList.push(ownerId);
        startDate[ownerId] = _startDate;
        startLatitude[ownerId] = latitude;
        startLongitude[ownerId] = longitude;
        isEnd = false;
    }

  function setStart(uint userId, uint _startDate, string latitude, string longitude) public{
        startUserList.push(userId);
        startDate[userId] = _startDate;
        startLatitude[userId] = latitude;
        startLongitude[userId] = longitude;
    }
    
  function endStart(uint userId, uint _startDate, string latitude, string longitude) public returns(bool){
        startUserList.push(userId);
        startDate[userId] = _startDate;
        startLatitude[userId] = latitude;
        startLongitude[userId] = longitude;
        if(startUserList.length == endUserList.length){
            isEnd = true;
            return true;
        }else{
            return false;
        }
    }
    
    function getStartUser() public constant returns (uint[]) {
        return startUserList;
    }
    
    function getEndUser() public constant returns (uint[]) {
        return endUserList;
    }
}