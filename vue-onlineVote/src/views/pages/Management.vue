<template>
    <h2>後台畫面</h2>
    <div>
        <button @click="addItem">新增按鈕</button>
    </div>

    <!-- 模態窗口 -->
    <div v-if="showModal" class="modal">
        <div class="modal-content">
            <span class="close" @click="closeModal">&times;</span>
            <h2>{{ isEditMode ? '修改投票項目' : '新增投票項目' }}</h2>
            <input v-model="newVoteItemName" placeholder="輸入投票項目名稱" />
            <button @click="isEditMode ? updateItem() : saveItem()">
                {{ isEditMode ? '更新' : '保存' }}
            </button>
        </div>
    </div>

    <div>
        <table>
            <tr>
                <th>投票項目編號</th>
                <th>投票項目名稱</th>
                <th>修改</th>
            </tr>
            <tr v-for="(item, index) in voteItems" :key="item.voteItemNo">
                <td>{{ item.voteItemNo }}</td>
                <td>{{ item.voteItemName }}</td>
                <td>
                    <button @click="editItem(item)">修改</button>
                </td>
            </tr>
        </table>
    </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import axiosapi from '@/plugins/axios.js';

const voteItems = ref([]);
const showModal = ref(false);
const newVoteItemName = ref('');
const isEditMode = ref(false);  // 判斷當前是否為修改模式
let currentVoteItem = null;  // 當前要修改的投票項目

function callTable() {
    axiosapi.get("/findAll").then(function (response) {
        if (response.data) {
            voteItems.value = response.data.list || [];
        }
    }).catch(function (error) {
        console.error('Error fetching table:', error);
    });
}

onMounted(() => {
    callTable();
});

// 打開新增模態窗口
function addItem() {
    isEditMode.value = false;
    newVoteItemName.value = '';
    showModal.value = true;
}

// 打開修改模態窗口
function editItem(item) {
    isEditMode.value = true;
    currentVoteItem = item;
    newVoteItemName.value = item.voteItemName;
    showModal.value = true;
}

// 關閉模態窗口
function closeModal() {
    showModal.value = false;
}

// 保存新項目
function saveItem() {
    if (newVoteItemName.value.trim() === '') {
        alert('請輸入投票項目名稱');
        return;
    }

    axiosapi.post('/create', { voteItemName: newVoteItemName.value })
        .then(response => {
            callTable();
            closeModal();
        })
        .catch(error => {
            console.error('Error saving item:', error);
        });
}

// 更新項目
async function updateItem() {
    if (newVoteItemName.value.trim() === '') {
        alert('請輸入投票項目名稱');
        return;
    }

    await axiosapi.put('/modify', {
        voteItemNo: currentVoteItem.voteItemNo,
        voteItemName: newVoteItemName.value
    })
        .then(response => {
            callTable();  // 重新加載表格資料
            closeModal();
        })
        .catch(error => {
            console.error('Error updating item:', error);
        });
}
</script>

<style scoped>
.modal {
    display: block;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 500px;
    border-radius: 8px;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}
</style>