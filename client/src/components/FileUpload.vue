<template>
  <div>
    <div v-bind="getRootProps()">
      <input v-bind="getInputProps()" />
      <p v-if="isDragActive">Drop the files here ...</p>
      <p v-else>Drag 'n' drop some files here, or click to select files</p>
    </div>
    <button @click="open">open</button>
  </div>
</template>

<script>
import { useDropzone } from "vue3-dropzone";
import { defineEmits, ref } from 'vue';
import axios from "axios";

export default {
  name: "FileUpload",
  setup() {
    const emit = defineEmits(['files-uploaded']);
    const imgUrl = ref('');

    const cloudinaryName = 'dimtzu2cn';
    const url = `https://api.cloudinary.com/v1_1/${cloudinaryName}/images/upload`;

    const saveFiles = async (file) => {
      const formData = new FormData();
      formData.append(`file`, file);
      // formData.append("api_key", "826614987726444");
      formData.append("upload_preset", "vxaqd1cu");

      let img = await fetch(url, {
        method: "POST",
        body: formData
      }).then(res => {
        console.log(res); 
        imgUrl.value = res.url;
        emit('files-uploaded', imgUrl.value);
      }).catch(err => {
        console.error(err);
      })

      console.log(imgUrl.value)

      // axios
      //   .post(url, formData)
      //   .then((response) => {
      //     console.info(response.data);
      //     imgUrl.value = response.data.url;
      //     emit('files-uploaded', imgUrl.value);
      //   })
      //   .catch((err) => {
      //     console.error(err);
      //   });
    };

    function onDrop(acceptFiles, rejectReasons) {
      if (acceptFiles.length > 0) {
        saveFiles(acceptFiles[0]);
      }
      console.log(rejectReasons);
    }

    const { getRootProps, getInputProps, ...rest } = useDropzone({ onDrop });

    return {
      getRootProps,
      getInputProps,
      ...rest,
    };
  },
};
</script>
