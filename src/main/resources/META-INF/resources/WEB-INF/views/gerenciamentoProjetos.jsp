<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Projetos</title>
</head>
<body>
    <h1>Gerenciamento de Projetos</h1>
    <a href="/gerenciamentoMembros">Gerenciamento de Membros</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
    <a href="/gerenciamentoProjetos">Gerenciamento Projetos</a>

    <br/><br/>

    <!-- Link para o formulário de cadastro de projeto -->
    <a href="/formularioProjetos">Cadastrar Novo Projeto</a>
    <br/><br/>

    <!-- Tabela de Projetos -->
    <table border="1" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Data Início</th>
                <th>Previsão Fim</th>
                <th>Data Fim</th>
                <th>Status</th>
                <th>Risco</th>
                <th>Orçamento</th>
                <th>Gerente</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${projetos}" var="projeto">
                <tr>
                    <td>${projeto.id}</td>
                    <td>${projeto.nome}</td>
                    <td>${projeto.dataInicio}</td>
                    <td>${projeto.dataPrevisaoFim}</td>
                    <td>${projeto.dataFim}</td>
                    <td>${projeto.status}</td>
                    <td>${projeto.risco}</td>
                    <td>${projeto.orcamento}</td>
                    <td>${projeto.gerente != null ? projeto.gerente.nome : 'Não atribuído'}</td>
                    <td>
                        <!-- Links para Editar e Remover -->
                        <a href="/formularioProjetos?id=${projeto.id}">Editar</a>&nbsp;
                        <a href="/projetos?id=${projeto.id}" onclick="return confirm('Deseja realmente excluir este projeto?');">Remover</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
