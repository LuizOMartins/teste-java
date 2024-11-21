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
    <a href="/gerenciamentoProjetos">Home - Projetos</a>
    <a href="/gerenciamentoMembros">Gerenciamento de Membros</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
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
                        <button onclick="removerProjeto('${projeto.id}')">Remover</button>
                        <a href="/formularioProjetos?id=${projeto.id}">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <p id="demo"></p>
    </table>
<script>

function removerProjeto(id) {
    console.log(id);
    document.getElementById("demo").innerHTML = "Para remover: " + id + ".";

    if (!id) {
        console.error('ID não fornecido!');
        return;
    }

    if (confirm('Deseja realmente excluir este projeto?')) {
        fetch('/projetos/removerProjeto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({ id }),
        })
        .then(response => {
            if (response.ok) {
                alert('Projeto removido com sucesso!');
                window.location.reload();
                document.querySelector(`button[data-id="${id}"]`).closest('tr').remove();
            } else {
                console.error('Erro na resposta do servidor:', response.status);
                alert('Erro ao remover o projeto!');
            }
        })
        .catch(error => {
            console.error('Erro na requisição:', error);
            alert('Erro ao remover o projeto!');
        });
    }
}

</script>
</body>
</html>
