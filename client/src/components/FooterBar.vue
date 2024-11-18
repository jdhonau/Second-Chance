<template>
    <footer>
    <div class="footer">
        <div class="text-center p-3" style="background-color: #66023c;">
            © 2024 Copyright | <a href="https://www.brandiva.com">Brandiva</a>
            <button @click="toggleDarkMode">
                {{ isDarkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode' }}
            </button>
        </div>
    </div>
    </footer>
</template>

<script>
export default {
    name: 'FooterBar',
    props: {
        isDarkMode: Boolean
    },
    methods: {
        toggleDarkMode() {
            this.$emit('toggle-dark-mode');
        }
    }
};
</script>

<style scoped>
.text-center {
    color: #fff;
}
.footer a {
    text-decoration: none;
    color: #fff;
}
button {
    margin-left: 10px;
    background-color: #fff;
    color: #66023c;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
}
button:hover {
    background-color: #ddd;
}
</style>
