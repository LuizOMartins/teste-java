<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pessoa</title>
</head>
<body>
    <h1>Cadastro de Pessoa</h1>
    <a href="/gerenciamentoPessoa">Voltar</a>

    <form action="/pessoas/salvar" method="post">
        <input type="hidden" name="id" value="${pessoa.id}" />

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${pessoa.nome}" required maxlength="100" /><br/>

        <label for="dataNascimento">Data de Nascimento:</label>
        <input type="date" id="dataNascimento" name="dataNascimento" value="${pessoa.dataNascimento}" required />

        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" value="${pessoa.cpf}" required maxlength="14" /><br/>

        <button type="submit">Salvar</button>
    </form>
</body>
</html>
