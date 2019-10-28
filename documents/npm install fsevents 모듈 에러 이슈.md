## npm install fsevents 모듈 에러

~~~
> npm install
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.9 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.9: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
~~~



### 원인

- fsevents 모듈은 osx(맥)용이므로 윈도우에서 경고메시지가 발생한 것



### 해결방법

- package.json 에 다음 코드 추가

~~~
    "optionalDependencies": {
        "fsevents": "*"
    }
~~~

- 다음 명령어로 인스톨 실행

~~~
npm install --no-optional
~~~



 https://codens.info/m/1697?category=795359 