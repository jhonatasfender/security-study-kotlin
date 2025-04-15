#!/bin/bash

echo "Enviando requisição de login..."
RESPONSE=$(curl -v -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}')

echo "Resposta completa:"
echo "$RESPONSE" | jq '.'

TOKEN=$(echo "$RESPONSE" | jq -r '.token')
echo "Token obtido: $TOKEN"

if [ -z "$TOKEN" ]; then
    echo "Erro: Token vazio!"
    exit 1
fi

echo "Acessando endpoint /hello com o token..."
HELLO_RESPONSE=$(curl -v -X GET http://localhost:8080/hello \
  -H "Authorization: Bearer $TOKEN")

echo "Resposta do endpoint /hello:"
echo "$HELLO_RESPONSE"
