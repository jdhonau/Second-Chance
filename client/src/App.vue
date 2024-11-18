<template>
  <div id="app">
    <HeaderNav />
    <main :class="{ 'dark-mode': isDarkMode }">
      <router-view />
    </main>
    <FooterBar :isDarkMode="isDarkMode" @toggle-dark-mode="toggleDarkMode" />
  </div>
</template>

<script>
import HeaderNav from './components/HeaderNav.vue';
import FooterBar from './components/FooterBar.vue';

export default {
  components: {
    HeaderNav,
    FooterBar
  },
  data() {
    return {
      isDarkMode: false
    };
  },
  methods: {
    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode;
    }
  }
};

</script>


<style>
@import './assets/base.css';

main.dark-mode {
  background-color: #333;
  color: #bfbfbf;
  padding-bottom: 10px;
}

main.dark-mode .section-title {
  color: #bfbfbf;
}

.animal-cards-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.5rem;
}


@media (max-width: 425px) {
  * {
    font-size: 12px;
  }

  .animal-cards-grid {
    grid-template-columns: 1fr; 
  }

  main.dark-mode {
    background-color: #444;
    color: #eee;
  }

.card {
    grid-template-columns: 1fr;
  }
  .card-body {
    font-size: 12px;
    margin: 10px;
  }

  .nav-links {
    flex-direction: column;
    align-items: center;
  }

  .nav-links li {
    margin: 0.5rem 0;
  }
  .btn-outline-secondary {
    display: inline-block;
    vertical-align: ;
    font-size: 12px;
    margin: 10px;
    padding-left: 10px;
    padding-right: 10px;  
    padding-top: 6px;
    padding-bottom: 6px;
  }
  .btn-outline-secondary bi bi-share {
  
  vertical-align: middle;
  box-sizing: border-box;
  text-align: center;
  font: inherit;
  font-size: 12px; 
  padding-left: 10px;
  padding-right: 10px;  

}
}
</style>
