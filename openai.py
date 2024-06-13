import requests
import json

api_key = 'your-api-key'
product_features = "6.5-inch display, 128GB storage, dual-camera"

url = 'https://api.openai.com/v1/completions'
headers = {
    'Content-Type': 'application/json',
    'Authorization': f'Bearer {api_key}'
}
data = {
    'model': 'text-davinci-003',
    'prompt': f'Write a compelling product description for a new smartphone with the following features: {product_features}.',
    'max_tokens': 150,
    'temperature': 0.7,
}

response = requests.post(url, headers=headers, data=json.dumps(data))

if response.status_code == 200:
    response_data = response.json()
    if 'choices' in response_data and len(response_data['choices']) > 0:
        print(response_data['choices'][0]['text'].strip())
    else:
        print("API request error")
else:
    print(f"API request error: {response.status_code}")
