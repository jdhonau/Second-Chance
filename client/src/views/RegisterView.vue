<template>
  <!-- <HeaderNav /> -->
  <div class="signup-container">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div id="fields">
        <label for="username">Username: </label>
        <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
        <br><br>
          <label for="name">Name: </label>
          <input type="text" id="name" placeholder="Name" v-model="user.name" required />
          <br><br>
          <label for="password">Password: </label>
          <input type="password" id="password" placeholder="Password" v-model="user.password" required />
          <br>
          <br>
          <label for="confirmPassword">Confirm password: </label>
          <input type="password" id="confirmPassword" placeholder="Confirm Password" v-model="user.confirmPassword"
            required />
          <br>
          <br>
          <div>
            <button type="submit">Create Account</button>
          </div>
      </div>
      <hr />
      Have an account?
      <router-link v-bind:to="{ name: 'login' }">Sign in!</router-link>
    </form>
  </div>
</template>

<script>
// import HeaderNav from "../components/HeaderNav.vue";
import authService from "../services/AuthService";

export default {
  name: 'RegisterView',
  components: {
    // HeaderNav
  },
  data() {
    return {
      user: {
        username: "",
        name: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                // Show the validation errors
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
.signup-container {
  height: 75vh;
  margin-top: 2%;
  padding-left: 30%;
  padding-right: 30%;
}
</style>
