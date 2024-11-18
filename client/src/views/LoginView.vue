<template>
  <!-- <HeaderNav /> -->
  <div class="login-container">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <br>
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <br>
        <div><button type="submit">Sign in</button></div>
      </div>
      <hr/>
      <div class="register-link">
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
      </div>
    </form>
  </div>
  <!-- Remove FooterBar from here if present -->
</template>

<script>
import authService from "../services/AuthService";
// import HeaderNav from "../components/HeaderNav.vue";
export default {
  name: 'LoginView',
  components: {
    // HeaderNav
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            console.log('Login response:', response);
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
.login-container {
    height: 71vh;
    margin-top: 5%; 
    padding-left: 30%;
    padding-right: 30%;
}
#fields {
  margin-top: 5%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
h1 {
  text-align: center;
}
.register-link {
  text-align: center;
  margin-top: 1rem;
}

</style>
