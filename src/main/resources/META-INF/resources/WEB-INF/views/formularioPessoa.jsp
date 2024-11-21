<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pessoa</title>
</head>
<body>
    <h1>Cadastro de Pessoa</h1>
    <a href="/gerenciamentoProjetos">Home - Projetos</a>
    <a href="/gerenciamentoPessoa">Voltar</a>

    <form action="/pessoas/salvar" method="post">
        <input type="hidden" name="id" value="${pessoa.id}" />

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${pessoa.nome}" required maxlength="100" /><br/>

        <label for="dataNascimento">Data de Nascimento:</label>
        <input type="date" id="dataNascimento" name="dataNascimento" value="${pessoa.dataNascimento}" required /><br/>

        <!-- CPF -->
        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" value="${pessoa.cpf}" required maxlength="14" /><br/>

        <label for="funcionario">É Funcionário?</label>
        <input type="checkbox" id="funcionario" name="funcionario" ${pessoa.funcionario ? 'checked' : ''} /><br/>

        <label for="gerente">É Gerente?</label>
        <input type="checkbox" id="gerente" name="gerente" ${pessoa.gerente ? 'checked' : ''} /><br/><br/>

        <button type="submit">Salvar</button>
    </form>
</body>
</html>
