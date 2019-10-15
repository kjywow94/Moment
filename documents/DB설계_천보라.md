- # DB설계

    ### DB 생성
    
    ```
    create database boracoin;	
    ```
    
    
    
    ### 유저생성
    
    ```
    create user 'boracoin1' @'%' identified by 'ssafy';
  ```
  
   
  
   ### 유저권한
   
   - 권한보기
  
   ```
   show grants for 'boracoin1'@'%';
  ```
   
   
   
   - 권한주기
   
   ```
   grant all privileges on *.* to 'boracoin1'@'%';
   ```
   
   
   
   ### 테이블 생성
   
   - user 테이블 생성
   
     ```
     create table user(
         id int auto_increment primary key ,
         birthday date not null,
         gender enum('M','W','X') default 'X' comment '고객 성별 m은 남성 w은 여성 x는 선택하지 않음',
         nickname varchar(50) not null,
         user_name  varchar(50) not null,
         phone varchar(50) not null,
         email varchar(30) not null,
         companion_count int default 0,
         companion_grade int default 0,
         password varchar(100) not null,
         sns_1 varchar(100),
     	sns_2 varchar(100),
     	taste_1 int,
         taste_2 int,
     	taste_3 int,
         taste_4 int,
      	taste_5 int,
         taste_6 int,
     	taste_7 int,
         about varchar(2000),
         authority enum('A','U') default 'U' comment 'A:admin, U:user',
     	walletAddress varchar(200)
         );
     ```
   
     
   
     
   
      