# Security Simple Project

Este é um projeto de demonstração que implementa funcionalidades básicas de segurança usando Spring Security e Kotlin, oferecendo uma abordagem direta e prática para autenticação e autorização.

## 🛠️ Tecnologias

- Kotlin 1.9.25
- Java 21
- Spring Boot 3.4.4
- Spring Security
- SpringDoc OpenAPI UI 2.3.0

## 📋 Pré-requisitos

- JDK 21
- Maven 3.x
- Git

## 🚀 Como executar

1. Clone o repositório:
```bash
git clone [url-do-repositorio]
```

2. Entre no diretório do projeto:
```bash
cd security-simple
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

## 📚 Documentação da API

A documentação da API está disponível através do Swagger UI. Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

## 🔐 Funcionalidades de Segurança

- Autenticação básica
- Autorização baseada em roles
- Proteção de endpoints
- Configurações de CORS e CSRF
- Integração com Spring Security

## 🧪 Testes

Para executar os testes:

```bash
./mvnw test
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/
│   │       └── study/
│   │           └── security/
│   │               ├── config/      # Configurações do Spring e Security
│   │               ├── controller/  # Controllers REST
│   │               ├── model/       # Modelos de dados
│   │               └── service/     # Serviços da aplicação
│   └── resources/
│       └── application.properties   # Configurações da aplicação
└── test/
    └── kotlin/                      # Testes unitários e de integração
```

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença [MIT](LICENSE).

## 🔍 Exemplos de Uso

### Autenticação

```kotlin
// Exemplo de configuração de autenticação
@Configuration
@EnableWebSecurity
class SecurityConfig {
    // Configurações de segurança
}
```

### Proteção de Endpoints

```kotlin
@RestController
@RequestMapping("/api")
class SecuredController {
    @GetMapping("/secured")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getSecuredResource(): String {
        return "Este é um recurso protegido"
    }
}
``` 