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
    <a href="/gerenciamentoProjetos">Home</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
    <a href="/gerenciamentoProjetos">Gerenciamento Projetos</a>
    <a href="gerenciamentoMembros">Voltar</a>


    <form action="/membros/salvar" method="post">
        <input type="hidden" name="id" value="${membro.id}" />

        <br/><br/>


        <!-- Dados do Projeto -->
        <label for="projeto">Projeto:</label>
        <select name="projeto.id" required>
            <c:forEach items="${projetos}" var="projeto">
                <option value="${projeto.id}" ${membro.projeto != null && projeto.id == membro.projeto.id ? 'selected' : ''}>
                    ${projeto.nome}
                </option>
            </c:forEach>
        </select><br/><br/>

        <!-- Dados da Pessoa -->
        <h3>Dados da Pessoa</h3>

        <label for="nome">Nome:</label>
        <input type="text" name="pessoa.nome" value="${membro.pessoa != null ? membro.pessoa.nome : ''}" required maxlength="100" /><br/>

        <label for="datanascimento">Data de Nascimento:</label>
        <input type="date" name="pessoa.datanascimento" value="${membro.pessoa != null && membro.pessoa.datanascimento != null ? membro.pessoa.datanascimento : ''}" required /><br/>

        <label for="cpf">CPF:</label>
        <input type="text" name="pessoa.cpf" value="${membro.pessoa != null ? membro.pessoa.cpf : ''}" required maxlength="14" /><br/>

        <label for="funcionario">É Funcionário?</label>
        <input type="checkbox" name="pessoa.funcionario" ${membro.pessoa != null && membro.pessoa.funcionario ? 'checked' : ''} /><br/>

        <label for="gerente">É Gerente?</label>
        <input type="checkbox" name="pessoa.gerente" ${membro.pessoa != null && membro.pessoa.gerente ? 'checked' : ''} /><br/><br/>


        <h3>Dados do Membro</h3>

        <label for="cargo">Cargo:</label>
        <input type="text" name="cargo" value="${membro.cargo != null ? membro.cargo : ''}" required maxlength="100" /><br/>

        <label for="dataAssociacao">Data de Associação:</label>
        <input type="datetime-local" name="dataAssociacao" value="${membro.dataAssociacao != null ? membro.dataAssociacao : ''}" /><br/><br/>

        <button type="submit">Salvar</button>
    </form>
</body>
</html>
