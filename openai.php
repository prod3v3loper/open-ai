<?php
$api_key = 'your-api-key';

$product_name = "Smartphone XYZ";
$product_features = "6.5-inch display, 128GB storage, dual-camera";

$url = 'https://api.openai.com/v1/completions';
$data = array(
    'model' => 'text-davinci-003',
    'prompt' => "Write a compelling product description for a new smartphone with the following features: $product_features.",
    'max_tokens' => 150,
    'temperature' => 0.7,
);

$headers = array(
    'Content-Type: application/json',
    'Authorization: Bearer ' . $api_key,
);

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$response = curl_exec($ch);
curl_close($ch);

$response_data = json_decode($response, true);

if (isset($response_data['choices'][0]['text'])) {
    echo trim($response_data['choices'][0]['text']);
} else {
    echo "API request error";
}
