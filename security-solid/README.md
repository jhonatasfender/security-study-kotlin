# Security SOLID Project

Este projeto é uma implementação de segurança seguindo os princípios SOLID e Clean Architecture em Kotlin, demonstrando boas práticas de desenvolvimento e arquitetura de software.

## 🏗️ Arquitetura

O projeto está estruturado em módulos seguindo os princípios da Clean Architecture:

- **core**: Contém as entidades de domínio e regras de negócio centrais
- **usecases**: Implementa os casos de uso da aplicação
- **dataproviders**: Contém as implementações de acesso a dados e integrações externas
- **presentation**: Camada de apresentação e APIs REST

## 🛠️ Tecnologias

- Kotlin 1.9.22
- Java 21
- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- JUnit 5.10.1
- MockK 1.13.8

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
cd security-solid
```

3. Compile o projeto:
```bash
./mvnw clean install
```

4. Execute a aplicação:
```bash
./mvnw spring-boot:run -pl presentation
```

## 📦 Estrutura de Módulos

### Core
Contém as entidades de domínio e regras de negócio fundamentais do sistema.

### Usecases
Implementa os casos de uso da aplicação, seguindo os princípios SOLID:
- Single Responsibility Principle (SRP)
- Open/Closed Principle (OCP)
- Liskov Substitution Principle (LSP)
- Interface Segregation Principle (ISP)
- Dependency Inversion Principle (DIP)

### Dataproviders
Implementações concretas de repositórios e serviços externos.

### Presentation
APIs REST e configurações de segurança da aplicação.

## 🧪 Testes

Para executar os testes:

```bash
./mvnw test
```

## 📚 Documentação

A documentação detalhada da API e dos componentes está disponível em cada módulo do projeto.

## 🔐 Segurança

O projeto implementa as melhores práticas de segurança, incluindo:
- Autenticação
- Autorização
- Proteção contra ataques comuns
- Gestão segura de sessões

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença [MIT](LICENSE). 