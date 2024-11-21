<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Membro</title>
</head>
<body>
    <h1>Cadastro de Membro</h1>
    <a href="/gerenciamentoProjetos">Home - Projetos</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
    <a href="/gerenciamentoMembros">Voltar</a>


<form action="/membros/salvar" method="post">
    <!-- ID do Membro -->
    <input type="hidden" name="id" value="${membro.id}" />

    <!-- Seleção de Projeto -->
    <label for="projeto">Projeto:</label>
    <select name="projeto.id" required>
        <c:forEach items="${projetos}" var="projeto">
            <option value="${projeto.id}" ${membro.projeto != null && projeto.id == membro.projeto.id ? 'selected' : ''}>
                ${projeto.nome}
            </option>
        </c:forEach>
    </select><br/><br/>

    <!-- Seleção de Pessoa -->
    <label for="pessoa">Pessoa:</label>
    <select name="pessoa.id" required>
        <c:forEach items="${pessoas}" var="pessoa">
            <option value="${pessoa.id}" ${membro.pessoa != null && pessoa.id == membro.pessoa.id ? 'selected' : ''}>
                ${pessoa.nome} - CPF: ${pessoa.cpf}
            </option>
        </c:forEach>
    </select><br/><br/>

    <!-- Dados do Membro -->
    <label for="cargo">Cargo:</label>
    <input type="text" name="cargo" value="${membro.cargo}" required maxlength="100" /><br/>

    <br/><br/>

    <button type="submit">Salvar</button>
</form>



</body>
</html>
