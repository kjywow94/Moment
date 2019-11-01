# Docker 배포

- AWS IP : 13.209.9.195



## 백엔드 배포

- https://galid1.tistory.com/428 블로그를 참고

- 스프링부트 프로젝트를 JAR File로 압축

- AWS

  - ~~~
     docker pull rtfpessoa/ubuntu-jdk8
    ~~~

    - 도커허브에서 이미지 다운로드

  - 폴더 하나 생성 후(ex. BackEnd)

  - Dockerfile 생성

    - vi ./Dockerfile

      ~~~ 
      FROM rtfpessoa/ubuntu-jdk8
      ADD haribo-1.0.0.jar boraCoin.jar
      ENV JAVA_OPTS=""
      ENTRYPOINT ["java","-jar","/boraCoin.jar"]
      ~~~

  - Dockerfile이 생성된 곳에서 명령어 입력

    ~~~ 
    docker build --tag boraCoin:0.1 ./
    ~~~

    - Dockerfile로 이미지 빌드
    - ./ : 현재 명령어가 실행되는 곳의 기준으로 Dockerfile의 위치

  - docker image 실행

    ~~~ 
    docker run -d -p 8888:8888 boracoin:0.1
    ~~~

- 컨테이너에 도커 이미지 폴더 추가

  - 도커 컨테이너 접속
    - docker exec 이미지주소 ls /
  - docker cp /home/ubuntu/boraCoin/worksImage 5f72dd244e8b:/
    - docker cp 우분투 안 이미지폴더 이미지주소:/
    - 참고 :  https://www.reddit.com/r/docker/comments/6xvk7o/how_do_we_copy_a_folder_from_host_to_container/ 

  

  -  docker run -v [도커볼륨명]:[컨테이너 디렉터리 경로]

## 프론트엔드 배포

- Node.js 애플리케이션을 Docker 컨테이너에 넣는 방법
- Node.js 웹 애플리케이션을 만든 후에 이 애플리케이션을 위한 Docker 이미지를 만들어서 컨테이너로 실행할 거임.

----------------------------

- 우분투에 node.js와 npm 설치 // 이건 꼭 안해도 될 듯.

  -  [https://itstory.tk/entry/Ubuntu-1604-nodejs-%EC%99%80-npm-%EC%84%A4%EC%B9%98](https://itstory.tk/entry/Ubuntu-1604-nodejs-와-npm-설치) 

- AWS 폴더 생성 후 FrontEnd를 옮김.

- 뷰 앱을 Dockerizing

  (https://mherman.org/blog/dockerizing-a-vue-app/ )

- Dockerfile을 프로젝트 루트에 추가.

  ~~~ 
  FROM node:10.16.2
  
  WORKDIR /app
  
  
  ENV PATH /app/node_modules/.bin:$PATH
  
  COPY package.json /app/package.json
  RUN npm install
  RUN npm install -g @vue/cli@3.9.2 -g
  
  CMD ["npm","run","serve"]
   
  
  ~~~

-  docdocignore도  추가

  ```
  node_modules
  .git
  .gitignore
  ```

  -  로컬 의존성과 git repo가 Docker 데몬으로 전송되지 않으므로 Docker 빌드 프로세스 속도가 빨라집니다. --> 무슨말이지?

-  Docker 이미지를 빌드하고 태그를 지정 

  ~~~ 
  docker build --tag newapp:0.1 ./
  ~~~

-  그런 다음 빌드가 완료되면 컨테이너를 위로 올리십시오. 

  ```
  docker run -v ${PWD}:/app -v /app/node_modules -d -p 80:80 newapp:0.1
  ```



### 번외)  Node.js와 NPM 개념

- Node.js 공식 사이트 정의

  >   **Chrome V8 JavaScript 엔진으로 빌드된 JavaScript 런타임입니다. Node.js는 이벤트 기반, Non 블로킹 I/O 모델을 사용해 가볍고 효율적입니다. Node.js의 패키지 생태계인 npm은 세계에서 가장 큰 오픈 소스 라이브러리 생태계이기도 합니다.** 

  - node.js는 자바스크립트를 이용해서 컴퓨터 자체를 제어. 파이썬, 자바, php, 루비 같이.(자바스크립트는 웹브라우져를 제어)
  - 런타임 :  컴퓨터 과학에서 컴퓨터 프로그램이 실행되고 있는 동안의 동작
  -  `npm`은 `Node Package Manager` 또는 `Node Package Modules` 