<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gerenciamento de Membros</title>
</head>
<body>
    <h1>Gerenciamento de Membros</h1>
    <a href="/gerenciamentoProjetos" >Home</a>
    <a href="/formularioMembro">Adicionar Novo Membro</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Projeto</th>
                <th>Pessoa</th>
                <th>Cargo</th>
                <th>Data de Associação</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${membros}" var="membro">
                <tr>
                    <td>${membro.id}</td>
                    <td>${membro.projeto.nome}</td>
                    <td>${membro.pessoa.nome}</td>
                    <td>${membro.cargo}</td>
                    <td>${membro.dataAssociacao}</td>
                    <td>
                        <a href="/formularioMembro?id=${membro.id}">Editar</a>
                        <button onclick="removerMembro('${membro.id}')">Remover</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <p id="demo"></p>
    </table>
</body>
<script>
function removerMembro(id) {
    console.log(id);
    document.getElementById("demo").innerHTML = "Para remover o membro: " + id + ".";

    if (!id) {
        console.error('ID do membro não fornecido!');
        return;
    }

    if (confirm('Deseja realmente remover este membro?')) {
        fetch('/membros/desvincular', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({ id }),
        })
        .then(response => {
            if (response.ok) {
                alert('Membro removido com sucesso!');
                window.location.reload(); // Atualiza a página após a remoção
            } else {
                console.error('Erro na resposta do servidor:', response.status);
                alert('Erro ao remover o membro!');
            }
        })
        .catch(error => {
            console.error('Erro na requisição:', error);
            alert('Erro ao remover o membro!');
        });
    }
}

</script
</html>
