// File Upload Service
import axios from 'axios';

const baseUrl = 'http://localhost:9000/api';

export default {
  uploadFiles(files, additionalData = {}) {
    const formData = new FormData();

    // Append files to the FormData object
    files.forEach((file, index) => {
      formData.append(`file_${index}`, file);
    });

    // Append any additional data
    Object.keys(additionalData).forEach(key => {
      formData.append(key, additionalData[key]);
    });

    // Make the POST request to upload files
    return axios.post(`${baseUrl}/upload`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  }
};
