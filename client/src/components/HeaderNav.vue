<template>
    <header class="navbar">
        <div class="container">
            <div><img src="/scpslogo.png" class="logo" /></div>
            <nav>
                <ul class="nav-links">
                    <li><router-link :to="{ name: 'home' }">Home</router-link></li>
                    <li><router-link :to="{ name: 'ContactView' }">Contact Us</router-link></li>
                    <li v-if="$store && $store.state && $store.state.token">
                        <router-link :to="{ name: 'logout' }">Logout</router-link>
                    </li>
                    <li v-else>
                        <router-link :to="{ name: 'login' }">Login</router-link>
                    </li>
                    <li><router-link :to="{ name: 'register' }">Register</router-link></li>
                </ul>
            </nav>
        </div>
    </header>
</template>
<script>
export default {
    name: 'HeaderNav'
}
</script>
<style scoped>
header {
    background-color: #b4b4b4;
    color: #fff;
    display: flex;
}
.container nav {
    width: 70%;
    justify-content: right;
    display: flex;
    align-self: flex-end;
}

.navbar {
    display: flex;
    align-items: center;
}

.logo {
    display: flex;
    width: 140px;
    justify-content: left;

}

.nav-links {
    list-style: none;
    display: flex;

}

.nav-links li {
    margin-left: 1rem;
    font-family: 'Oswald', sans-serif;
    font-size: 20px;
    text-transform: uppercase;
    padding-left: 80px;
}

.nav-links a {
    color: #66023c;
    text-decoration: none;
    font-size: 20px;
}
</style>
