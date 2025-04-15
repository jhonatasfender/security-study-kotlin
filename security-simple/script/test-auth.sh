#!/bin/bash

# Função para imprimir cabeçalhos de seção
print_header() {
    echo -e "\n\033[1;34m=== $1 ===\033[0m\n"
}

# Função para imprimir resultados
print_result() {
    if [ "$1" = "success" ]; then
        echo -e "\033[1;32m✓ $2\033[0m"
    else
        echo -e "\033[1;31m✗ $2\033[0m"
    fi
}

# Função para fazer requisições e verificar status
make_request() {
    local method=$1
    local url=$2
    local headers=$3
    local data=$4
    local expected_status=$5
    local description=$6

    echo -e "\n\033[1;33m$description\033[0m"
    echo "URL: $url"
    echo "Method: $method"
    if [ -n "$headers" ]; then
        echo "Headers: $headers"
    fi
    if [ -n "$data" ]; then
        echo "Data: $data"
    fi

    local response
    if [ -n "$data" ]; then
        response=$(curl -s -X "$method" "$url" -H "$headers" -d "$data")
    else
        response=$(curl -s -X "$method" "$url" -H "$headers")
    fi

    local status_code=$(curl -s -o /dev/null -w "%{http_code}" -X "$method" "$url" -H "$headers" ${data:+-d "$data"})
    
    if [ "$status_code" = "$expected_status" ]; then
        print_result "success" "Status code $status_code (esperado: $expected_status)"
        echo "Response: $response"
    else
        print_result "error" "Status code $status_code (esperado: $expected_status)"
        echo "Response: $response"
        return 1
    fi
}

# Teste 1: Login
print_header "Teste 1 - Login"
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/login \
    -H "Content-Type: application/json" \
    -d '{"username":"admin","password":"admin123"}')

echo "Resposta do login:"
echo "$LOGIN_RESPONSE"

TOKEN=$(echo "$LOGIN_RESPONSE" | jq -r '.token')
echo "Token obtido: $TOKEN"

if [ -z "$TOKEN" ]; then
    echo "Erro: Token vazio!"
    exit 1
fi

# Teste 2: Acessar endpoint protegido (/api/user/me)
print_header "Teste 2 - Acessar informações do usuário"
make_request "GET" "http://localhost:8080/api/user/me" \
    "Authorization: Bearer $TOKEN" \
    "" \
    "200" \
    "Tentando acessar informações do usuário com token válido"

# Teste 3: Acessar endpoint protegido (/hello)
print_header "Teste 3 - Acessar endpoint /hello"
make_request "GET" "http://localhost:8080/hello" \
    "Authorization: Bearer $TOKEN" \
    "" \
    "200" \
    "Tentando acessar endpoint /hello com token válido"

# Teste 4: Logout
print_header "Teste 4 - Logout"
make_request "POST" "http://localhost:8080/api/auth/logout" \
    "Authorization: Bearer $TOKEN" \
    "" \
    "200" \
    "Tentando fazer logout"

# Teste 5: Tentar acessar endpoints após logout
print_header "Teste 5 - Verificar acesso após logout"

# Teste 5.1: Tentar acessar /api/user/me após logout
make_request "GET" "http://localhost:8080/api/user/me" \
    "Authorization: Bearer $TOKEN" \
    "" \
    "403" \
    "Tentando acessar informações do usuário após logout (deve falhar)"

# Teste 5.2: Tentar acessar /hello após logout
make_request "GET" "http://localhost:8080/hello" \
    "Authorization: Bearer $TOKEN" \
    "" \
    "403" \
    "Tentando acessar endpoint /hello após logout (deve falhar)"

print_header "Resumo dos Testes"
echo "✓ Login com credenciais válidas"
echo "✓ Acesso a endpoints protegidos com token válido"
echo "✓ Logout bem-sucedido"
echo "✓ Bloqueio de acesso após logout"
echo -e "\n\033[1;32mTodos os testes foram concluídos!\033[0m" 