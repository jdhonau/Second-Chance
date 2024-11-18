<template>
  <section
    class="position-relative py-4 py-xl-5"
    style="--bs-primary: #66023c; --bs-primary-rgb: 102, 2, 60"
  >
    <div class="container position-relative">
      <div
        class="row d-flex justify-content-center"
        style="color: var(--bs-bt n-border-color)"
      >
        <div class="col-md-8 col-lg-6 col-xl-5 col-xxl-4">
          <div class="card mb-5">
            <div class="card-body p-sm-5" style="border-color: #222222">
              <h2 class="text-center mb-4">Adoption Application</h2>
              <form @submit.prevent="handleSubmit" data-bss-recipient="177e4c93a581994eee856c93244def21">
                <div class="mb-3">
                  <input
                    id="fullName"
                    class="form-control" v-model="fullName"
                    type="text"
                    name="Full Name"
                    placeholder="Full Name"
                  />
                </div>
                <div class="mb-3">
                  <input
                    id="phoneNumber"
                    class="form-control" v-model="phoneNumber"
                    type="text"
                    name="Phone Number"
                    placeholder="Phone Number"
                  />
                </div>
                <div class="mb-3">
                  <input
                    id="streetAddress"
                    class="form-control" v-model="streetAddress"
                    type="text"
                    name="Street Address"
                    placeholder="Street Address"
                  />
                </div>
                <div class="mb-3">
                  <input
                    id="cityStateZip"
                    class="form-control" v-model="cityStateZip"
                    type="text"
                    name="City State and Zip"
                    placeholder="City State and Zip"
                  />
                </div>
                <input
                  id="residentName"
                  class="form-control" v-model="residentName"
                  type="text"
                  name="Shelter Resident Name"
                  placeholder="Shelter Resident Name"
                />
                <div class="mb-3"></div>
                <input
                  id="rentOrOwn"
                  class="form-control" v-model="rentOrOwn"
                  type="text"
                  name="Rent or Own"
                  placeholder="Rent or Own"
                />
                <div class="mb-3"></div>
                <input
                  id="otherPets"
                  class="form-control" v-model="otherPets"
                  type="text"
                  name="Other Pets in the Home"
                  placeholder="Other Pets in the Home"
                />
                <div class="mb-3"></div>
                <input
                  id="petInsurance"
                  class="form-control" v-model="petInsurance"   
                  type="text"
                  name="Pet Insurance Provider"
                  placeholder="Pet Insurance Provider"
                />
                <div class="mb-3"></div>
                <div></div>
              </form>
              <button
                class="btn btn-primary d-block w-100"
                type="button"
                @click="handleSubmit"
                style="
                  --bs-primary: #66023c;
                  --bs-primary-rgb: 102, 2, 60;
                  background: #66023c;
                  border-style: solid;
                  border-color: var(--bs-body-color);
                "
              >
                Send Application
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "AdoptionForm",
  props: ["petName"],
  data() {
    return {
      fullName: "",
      phoneNumber: "",
      streetAddress: "",
      cityStateZip: "",
      residentName: "",
      rentOrOwn: "",
      otherPets: "",
      petInsurance: "",
    };
  },
  mounted() {
    this.residentName = this.petName;
    this.loadSmartFormsScript();
  },
  methods: {
    loadSmartFormsScript() {
      const script = document.createElement('script');
      script.src = '@/assets/js/smart-forms.min.js';
      script.async = true;
      script.onload = () => {
        // Initialize or use the smart forms functionality here
        console.log('Smart Forms script loaded');
        // Example: Initialize the form if needed
        // this.initializeSmartForm();
      };
      document.body.appendChild(script);
    },
    // initializeSmartForm() {
    //   // Code to initialize or use the smart form
    // }
    handleSubmit() {
      const subject = `Adoption Application from ${this.fullName}`;
      const body = `Full Name: ${this.fullName}\nPhone Number: ${this.phoneNumber}\nStreet Address: ${this.streetAddress}\nCity, State, Zip: ${this.cityStateZip}\nShelter Resident Name: ${this.residentName}\nRent or Own: ${this.rentOrOwn}\nOther Pets: ${this.otherPets}\nPet Insurance Provider: ${this.petInsurance}`;
      const mailtoLink = `mailto:secondchanceanimalrescue@gmail.com?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;
      
      window.location.href = mailtoLink;
    }
  }
};
</script>

<style scoped>
/* Your styles here */
</style>
