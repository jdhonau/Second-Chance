<template>
    
    <div class="contact-container">
    <section class="position-relative py-4 py-xl-5">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
                <div class="col-md-8 col-lg-6 col-xl-7 col-xxl-4 d-block">
                    <div class="card mb-5">
                        <div class="card-body p-sm-5">
                            <h2 class="text-center mb-4">Contact us</h2>
                            <form @submit.prevent="handleSubmit">
                                <div class="mb-3">
                                    <input class="form-control" type="text" v-model="firstName"
                                        placeholder="First Name">
                                </div>
                                <div class="mb-3">
                                    <input class="form-control" type="text" v-model="lastName" placeholder="Last Name">
                                </div>
                                <div class="mb-3">
                                    <input class="form-control" type="email" v-model="email"
                                        placeholder="Email Address">
                                </div>
                                <div class="mb-3">
                                    <input class="form-control" type="tel" v-model="phone" placeholder="Phone Number">
                                </div>
                                <div class="mb-3">
                                    <input class="form-control" type="text" v-model="referralSource"
                                        placeholder="How Did You Hear About Us?">
                                </div>
                                <div class="mb-3">
                                    <textarea class="form-control" v-model="message" rows="6"
                                        placeholder="Message"></textarea>
                                </div>
                                <div>
                                    <button class="btn btn-primary d-block w-100" type="submit">Send</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </section>
    </div>
</template>

<script>
export default {
    name: 'ContactForm',
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            referralSource: '',
            message: ''
        };
    },
    methods: {
        handleSubmit() {
            const subject = `Contact from ${this.firstName} ${this.lastName}`;
            const body = `Name: ${this.firstName} ${this.lastName}\nEmail: ${this.email}\nPhone: ${this.phone}\nReferral Source: ${this.referralSource}\n\nMessage:\n${this.message}`;
            const mailtoLink = `mailto:secondchanceanimalrescue@gmail.com?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;
            
            window.location.href = mailtoLink;
        }
    }
}
</script>

<style>
.btn-primary {
    background-color: #66023c;
    border-color: #bfbfbf;
}
.btn-primary:hover {
    background-color: #66023c;
    border-color: #66023c;
}
.contact-container {
    height: 90vh;
}   

/* Add any component-specific styles here */
</style>
