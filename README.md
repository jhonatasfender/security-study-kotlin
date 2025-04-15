# Estudo de Implementações de Segurança em Kotlin

Este repositório contém dois projetos demonstrativos de implementação de segurança usando Kotlin e Spring Security, cada um com uma abordagem diferente:

## 🏗️ Estrutura do Repositório

```
.
├── security-simple/    # Implementação básica de segurança
├── security-solid/     # Implementação seguindo SOLID e Clean Architecture
└── README.md          # Este arquivo
```

## 📚 Projetos

### [Security Simple](./security-simple)

Uma implementação direta e prática de segurança usando Spring Security, ideal para:
- Projetos pequenos e médios
- Prototipagem rápida
- Aprendizado dos conceitos básicos de segurança
- Demonstrações e POCs

**Características principais:**
- Spring Security com configuração básica
- Autenticação e autorização
- Documentação via Swagger/OpenAPI
- Estrutura simples e direta

### [Security SOLID](./security-solid)

Uma implementação robusta seguindo princípios SOLID e Clean Architecture, adequada para:
- Projetos empresariais
- Sistemas complexos
- Aplicações que requerem alta manutenibilidade
- Exemplos de arquitetura limpa

**Características principais:**
- Arquitetura modular
- Princípios SOLID
- Clean Architecture
- Separação clara de responsabilidades

## 🔄 Comparação entre as Abordagens

| Característica          | Security Simple | Security SOLID |
|------------------------|-----------------|----------------|
| Complexidade           | Baixa           | Alta           |
| Curva de Aprendizado   | Suave          | Íngreme        |
| Manutenibilidade       | Moderada        | Alta           |
| Testabilidade          | Boa            | Excelente      |
| Escalabilidade         | Moderada        | Alta           |
| Tempo de Setup         | Rápido         | Mais longo     |
| Adequado para         | Projetos pequenos/médios | Projetos grandes/empresariais |

## 🚀 Como Começar

1. Clone o repositório:
```bash
git clone [url-do-repositorio]
```

2. Escolha o projeto que melhor se adequa às suas necessidades:
   - Para uma abordagem simples: [security-simple](./security-simple)
   - Para uma arquitetura robusta: [security-solid](./security-solid)

3. Siga as instruções específicas no README.md de cada projeto.

## 📋 Pré-requisitos Gerais

- JDK 21
- Maven 3.x
- Git
- Kotlin 1.9.x

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Reportar bugs
2. Sugerir novas features
3. Abrir pull requests

## 📝 Licença

Este projeto está sob a licença [MIT](LICENSE).

## 📚 Recursos Adicionais

- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [SOLID Principles](https://web.archive.org/web/20150906155800/http://www.objectmentor.com/resources/articles/Principles_and_Patterns.pdf) 