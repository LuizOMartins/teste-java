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
                        <a href="/membros/excluir?id=${membro.id}" onclick="return confirm('Tem certeza que deseja excluir?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
