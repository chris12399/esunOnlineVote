<template>
    <NavBar></NavBar>
    <div class="centered-content">
        <RouterView></RouterView>
    </div>
</template>
    
<script setup>
import NavBar from '@/layout/NavBar.vue';
import { RouterView } from 'vue-router';
import { ref, provide } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const currentUser = ref(JSON.parse(sessionStorage.getItem('currentUser')) || null);

provide('currentUser', currentUser);

function setCurrentUser(user) {
    currentUser.value = user;
    sessionStorage.setItem('currentUser', JSON.stringify(user));
}

function logout() {
    currentUser.value = null;
    sessionStorage.removeItem('currentUser');
    router.push('/'); 
}
provide('currentUser', currentUser);
provide('setCurrentUser', setCurrentUser);
provide('logout', logout);
</script>
    
<style>
.centered-content {

    justify-content: center;
    align-items: center;
    text-align: center; 
}
</style>