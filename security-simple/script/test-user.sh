#!/bin/bash

echo "Enviando requisição de login..."
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}')

echo "Resposta completa:"
echo "$LOGIN_RESPONSE"

TOKEN=$(echo "$LOGIN_RESPONSE" | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo "Token obtido: $TOKEN"

echo "Obtendo informações do usuário..."
curl -v -X GET http://localhost:8080/api/user/me \
  -H "Authorization: Bearer $TOKEN" 