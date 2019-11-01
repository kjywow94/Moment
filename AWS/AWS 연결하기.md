## AWS 연결하기

https://thekoguryo.github.io/oci/chapter03/5/3/

https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/putty.html



1. putty 다운 받기

   **PuTTY**(**퍼티**, /ˈpʌti/[[3\]](https://ko.wikipedia.org/wiki/PuTTY#cite_note-3))는 [SSH](https://ko.wikipedia.org/wiki/시큐어_셸), [텔넷](https://ko.wikipedia.org/wiki/텔넷), [rlogin](https://ko.wikipedia.org/wiki/Rlogin), [raw TCP](https://ko.wikipedia.org/wiki/전송_제어_프로토콜)를 위한 [클라이언트](https://ko.wikipedia.org/wiki/클라이언트)로 동작하는 [자유 및 오픈 소스](https://ko.wikipedia.org/wiki/자유_및_오픈_소스_소프트웨어) [단말 에뮬레이터](https://ko.wikipedia.org/wiki/단말_에뮬레이터) 응용 프로그램

2. winSCP 다운 받기

   **WinSCP**(**Win**dows **S**ecure **C**o**P**y)는 [마이크로소프트 윈도](https://ko.wikipedia.org/wiki/마이크로소프트_윈도)용으로 개발된 [자유 및 오픈 소스](https://ko.wikipedia.org/wiki/자유_및_오픈_소스) [SFTP](https://ko.wikipedia.org/wiki/SSH_파일_전송_프로토콜), [SCP](https://ko.wikipedia.org/wiki/시큐어_카피) 및 [FTP](https://ko.wikipedia.org/wiki/파일_전송_프로토콜) 클라이언트

3. PuTTY Key Generator(puttygen) 다운받기

4. PuTTY Key Generator로 ppk파일 만들기

   1. 우선 pem파일을 준비 (메모장에 키 입력후 pem으로 확장자 변환)
   2. import key후 save private key

5. Linux 인스턴스에 연결

   1. ubuntu@public_ip
   2. connection-SSH-AUTH에 ppk로드
   3. session에 설정 저장하면 매우 편리
   4. port : 22, connective type : SSH 확인하기

6. open후 접속 허용 여부 확인후 커맨드 라인 여러개 뜨면 성공

   

   

   