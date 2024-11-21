INSTALL:
 -apache-maven-3.8.6


Executar Teste:
mvn test -X

Exemplo endpoint public:

curl -X POST "http://localhost:8081/membros/cadastro-publico" \
-H "Content-Type: application/x-www-form-urlencoded" \
-d "projetoId=7" \
-d "nome=João da Silva" \
-d "cpf=12345678900" \
-d "dataNascimento=1990-01-01T00:00:00" \
-d "cargo=Desenvolvedor"


TODO

PESSOA
- Cadastro pessoa: gerente ou funcionario - ok
- Tela Gerenciamento - ok

- Tela Cadastro Projeto - ok
--Gerenciamento Projeto - ok

- Tela Membros - ok
--Tela Gerenciamento de Membros - ok

-- Desassociar membro do projeto - ok


--Aplicar Regras - ANDAMENTO

--Aplicar testes - pendente
-- Estilização (bootstrap) - pendente
-- Validar CPF -  pendente
Pagina default erro - pendenete



