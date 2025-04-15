#!/bin/bash

echo "1. Enviando requisição de login..."
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}')

echo "Resposta completa:"
echo "$LOGIN_RESPONSE"

TOKEN=$(echo "$LOGIN_RESPONSE" | grep -o '"token":"[^"]*' | cut -d'"' -f4)
echo "Token obtido: $TOKEN"

echo -e "\n2. Tentando acessar /api/user/me com o token..."
curl -v -X GET http://localhost:8080/api/user/me \
  -H "Authorization: Bearer $TOKEN"

echo -e "\n\n3. Realizando logout..."
LOGOUT_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/logout \
  -H "Authorization: Bearer $TOKEN")

echo "Resposta do logout:"
echo "$LOGOUT_RESPONSE"

echo -e "\n4. Tentando acessar /api/user/me após o logout..."
curl -v -X GET http://localhost:8080/api/user/me \
  -H "Authorization: Bearer $TOKEN"

echo -e "\n\n5. Tentando acessar /hello após o logout..."
curl -v -X GET http://localhost:8080/hello \
  -H "Authorization: Bearer $TOKEN" 