<template>
    <h2>使用者投票</h2>

    <div>
        <table>
            <thead>
                <tr>
                    <th>選擇</th>
                    <th>投票項目名稱</th>
                    <th>票數</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in voteItems" :key="item.voteItemNo">
                    <td>
                        <input 
                            type="checkbox" 
                            :value="item.voteItemNo" 
                            v-model="selectedVoteItems"
                        >
                    </td>
                    <td>{{ item.voteItemName }}</td>
                    <td>{{ item.voteCount }}</td>
                </tr>
            </tbody>
        </table>

        <button @click="submitVotes">提交投票</button>

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axiosapi from '@/plugins/axios.js';

const voteItems = ref([]);
const selectedVoteItems = ref([]);  // 用來保存選擇的投票項目

// 在組件掛載後獲取投票項目
onMounted(() => {
    fetchVoteItems();
});

// 獲取投票項目列表及其票數
function fetchVoteItems() {
    axiosapi.get('/itemsWithCounts').then(response => {
        voteItems.value = response.data;
    }).catch(error => {
        console.error('Error fetching vote items:', error);
    });
}

// 提交選中的投票項目
function submitVotes() {
    if (selectedVoteItems.value.length === 0) {
        alert('請選擇至少一個投票項目');
        return;
    }
    // 從 sessionStorage 獲取當前使用者名稱
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    if (!currentUser) {
        alert('找不到當前登入的使用者，請重新登入');
        return;
    }

        // 準備要發送的資料
        const dataToSubmit = {
        voterName: currentUser,
        voteItemNos: selectedVoteItems.value
    };

    axiosapi.post('/submitVotes', dataToSubmit)
        .then(response => {
            if (response.data.success) {
                alert('投票提交成功');
                fetchVoteItems(); // 更新投票項目列表
                selectedVoteItems.value = []; // 清空選擇的項目
            } else {
                alert(response.data.message);
            }
        })
        .catch(error => {
            console.error('Error submitting votes:', error);
            alert('投票提交失敗');
        });
}




</script>

<style scoped>
/* 簡單的樣式 */
button {
    margin: 5px;
}

table {
    border-collapse: collapse;
    width: 80%; 
    max-width: 600px; 
    margin: auto;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

th {
    background-color: #f2f2f2;
    color: black;
}
</style>
