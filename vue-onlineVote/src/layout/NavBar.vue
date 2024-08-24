<template>
    <nav>
        <ul>
            <li>
                <router-link to="/management">管理頁面</router-link>
            </li>
            <li>
                <router-link to="/userVote">投票頁面</router-link>
            </li>
            <li class="user-info">
                歡迎, {{ currentUser ? currentUser : 'Guest' }}
                <button @click="logout">登出</button>
            </li>
        </ul>
    </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const username = ref(''); // 預設使用者名稱為空
const router = useRouter();
import { inject } from 'vue';
const currentUser = inject('currentUser');
onMounted(() => {
    callUser();
});

function callUser() {
    const user = JSON.parse(sessionStorage.getItem('currentUser')); 
    if (user) {
        username.value = user; // 設定使用者名稱
    }
}


// function logout() {
//     sessionStorage.removeItem('currentUser');
//     callUser();
//     router.push('/login');
// }

const logout = inject('logout');
</script>

<style>
nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: rgb(203, 201, 201);
    color: white;
}

ul {
    display: flex;
    list-style-type: none;
}

li {
    margin-right: 20px;
}

.user-info {
    margin-left: auto;
    color: blue;
}
</style>