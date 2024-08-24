<template>
    <div class="login-container">
        <h2>登入</h2>
        <form @submit.prevent="login">
            <div>
                <label for="username">姓名:</label>
                <input type="text" v-model="voterName" required />
            </div>
            <div>
                <label for="password">密碼:</label>
                <input type="password" v-model="password" required />
            </div>
            <button type="submit">登入</button>
        </form>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </div>
</template>

<script setup>
import { ref, inject } from 'vue';
import { useRouter } from 'vue-router';
import axiosapi from '@/plugins/axios.js'; // 假設你已經配置了axios

const voterName = ref('');
const password = ref('');
const errorMessage = ref('');
const router = useRouter();
const setCurrentUser = inject('setCurrentUser');

function login() {
    axiosapi.post('/login', { voterName: voterName.value, password: password.value })
        .then(response => {
            if (response.data.success) {
                console.log(response.data);
                const user = { username: voterName.value };
                sessionStorage.setItem('currentUser', JSON.stringify(voterName.value));

                setCurrentUser(user.username);

                router.push('/userVote');
            } else {
                errorMessage.value = response.data.message || '登入失敗，請檢查您的姓名和密碼';
            }
        })
        .catch(error => {
            console.error('Login error:', error);
            errorMessage.value = '登入失敗，請稍後再試';
        });
}
</script>

<style>
.login-container {
    width: 300px;
    margin: 100px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.error {
    color: red;
    margin-top: 10px;
}
</style>