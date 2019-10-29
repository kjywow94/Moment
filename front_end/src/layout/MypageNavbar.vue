<template>
   
                <nav class="nav nav-pills nav-fill">
                    <router-link class="nav-item nav-link" :to="{ name: 'mywallet' }">
                    <button type="button" class="btn btn-outline-info">내 지갑</button>   
                    </router-link>
                    <router-link class="nav-item nav-link" :to="{ name: 'myreview' }">
                    <button type="button" class="btn btn-outline-info">내가 작성한 후기</button>   
                    </router-link>
                    <router-link class="nav-item nav-link" :to="{ name: 'userinfo' }">
                    <button type="button" class="btn btn-outline-info">내 정보 수정</button>   
                    </router-link>
                    <router-link class="nav-item nav-link" :to="{ name: 'passwordchange' }">
                    <button type="button" class="btn btn-outline-info">비밀번호 변경</button>   
                    </router-link>
                </nav>

</template>

<script>
export default {
    data() {
        return {
      
        }
    },
    mounted: function(){
    
    }       
}
</script>

<style>

</style>