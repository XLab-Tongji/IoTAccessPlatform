<template>
    <el-form style="padding : 0" label-position="left" label-width="100px">
        <el-form-item label="peak val(kA)">
        </el-form-item>
        <el-form-item label="duration(ms)">
            <el-input size="mini" v-model="peak" :disabled="!scope.row.active" placeholder="请输入持续时间"></el-input>
        </el-form-item>
        <el-form-item label="polar   (+-)">
            <el-input size="mini" v-model="polar" :disabled="!scope.row.active" placeholder="请输入电荷极性"></el-input>
        </el-form-item>
    </el-form>
</template>

<script>
    name : "client-msg"
    export default {
        data() {
            return {

            }
        },
        props : ["peak", "duration", "polar"]
    }
</script>