<template>
        <div class="row sub-nav">
            <div class="col-md-6">
                <nav class="nav nav-pills nav-fill">
                    <router-link class="nav-item nav-link" v-bind:class="{ active: viewName == 'mypage.wallet.info' }" :to="{ name: 'mypage.wallet.info' }">지갑</router-link>
                    <router-link class="nav-item nav-link" v-bind:class="{ active: viewName == 'mypage.artwork' }" :to="{ name: 'mypage.artwork' }">동행정보</router-link>
                    <router-link class="nav-item nav-link" v-bind:class="{ active: viewName == 'mypage.update' }" :to="{ name: 'mypage.update' }">프로필 변경</router-link>
                    <router-link class="nav-item nav-link" v-bind:class="{ active: viewName == 'mypage.change_password' }" :to="{ name: 'mypage.change_password' }">비밀번호 변경</router-link>
                </nav>
            </div>
        </div>
</template>

<script>
export default {
    data() {
        return {
            sharedState: store.state,
            viewName: ""
        }
    },
    mounted: function(){
        this.viewName = this.$route.name;
    }       
}
</script>

<style>

</style>