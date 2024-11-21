<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Pessoas</title>
</head>
<body>
    <h1>Gerenciamento de Pessoas</h1>
    <a href="/formularioPessoa">Cadastrar Nova Pessoa</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
    <a href="/gerenciamentoProjetos">Gerenciamento Projetos</a>

    <br/><br/>
    <%-- Verifica se a lista de pessoas está chegando --%>
    <c:if test="${not empty pessoas}">
        <p>Lista de pessoas carregada com sucesso!</p>
    </c:if>
    <c:if test="${empty pessoas}">
        <p>Não há pessoas cadastradas ou a lista não foi carregada!</p>
    </c:if>


    <table border="1" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Data de Nascimento</th>
                <th>CPF</th>
                <th>Funcionário</th>
                <th>Gerente</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pessoas}" var="pessoa">
                <tr>
                    <td>${pessoa.id}</td>
                    <td>${pessoa.nome}</td>
                    <td>${pessoa.dataNascimento}</td>
                    <td>${pessoa.cpf}</td>
                    <td>${pessoa.funcionario ? 'Sim' : 'Não'}</td>
                    <td>${pessoa.gerente ? 'Sim' : 'Não'}</td>
                    <td>
                        <a href="/formularioPessoa?id=${pessoa.id}">Editar</a> |
                        <a href="/pessoas/deletar?id=${pessoa.id}" onclick="return confirm('Tem certeza que deseja excluir esta pessoa?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
