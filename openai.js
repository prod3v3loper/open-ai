const fetch = require('node-fetch');

const apiKey = 'your-api-key';
const productFeatures = "6.5-inch display, 128GB storage, dual-camera";

const data = {
    model: 'text-davinci-003',
    prompt: `Write a compelling product description for a new smartphone with the following features: ${productFeatures}.`,
    max_tokens: 150,
    temperature: 0.7,
};

fetch('https://api.openai.com/v1/completions', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${apiKey}`
    },
    body: JSON.stringify(data),
})
    .then(response => response.json())
    .then(responseData => {
        if (responseData.choices && responseData.choices.length > 0) {
            console.log(responseData.choices[0].text.trim());
        } else {
            console.log("API request error");
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
