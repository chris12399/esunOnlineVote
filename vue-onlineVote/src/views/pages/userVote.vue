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

// 獲取投票項目列表
function fetchVoteItems() {
    axiosapi.get('/findAll')
    .then(response => {
        voteItems.value = response.data.list || [];
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

    axiosapi.post('/user/create', {
        voteItemNos: selectedVoteItems.value 
        
    })
        .then(response => {
            // 提交成功後重新獲取列表
            fetchVoteItems();
            selectedVoteItems.value = [];  // 清空選擇的項目
        })
        .catch(error => {
            console.error('Error submitting votes:', error);
        });
}
</script>


<style scoped>
/* 簡單的樣式 */
button {
    margin: 5px;
}
</style>
