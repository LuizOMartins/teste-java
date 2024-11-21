<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Projeto</title>
</head>
<body>
    <h1>Cadastro de Projeto</h1>
    <a href="/gerenciamentoProjetos">Home - Projetos</a>
    <a href="/gerenciamentoProjetos">Voltar</a>
    <a href="/gerenciamentoPessoa">Gerenciamento de Pessoas</a>
    <br/><br/>

    <form id="projetoForm">
        <!-- Campo oculto para o ID do projeto -->
        <input type="hidden" name="id" value="${projeto.id}" />

        <!-- Nome -->
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${projeto.nome}" required maxlength="200" />
        <br/><br/>

        <!-- Data Início -->
        <label for="dataInicio">Data Início:</label>
        <input type="date" id="dataInicio" name="dataInicio" value="${projeto.dataInicio}" />
        <br/><br/>

        <!-- Data Previsão Fim -->
        <label for="dataPrevisaoFim">Data Previsão Fim:</label>
        <input type="date" id="dataPrevisaoFim" name="dataPrevisaoFim" value="${projeto.dataPrevisaoFim}" />
        <br/><br/>

        <!-- Data Fim -->
        <label for="dataFim">Data Fim:</label>
        <input type="date" id="dataFim" name="dataFim" value="${projeto.dataFim}" />
        <br/><br/>

        <!-- Descrição -->
        <label for="descricao">Descrição:</label>
        <textarea id="descricao" name="descricao" maxlength="5000">${projeto.descricao}</textarea>
        <br/><br/>

        <!-- Status -->
        <label for="status">Status:</label>
       <select id="status" name="status" required>
           <option value="">Selecione</option>
           <option value="Em Análise" ${projeto.status == 'Em Análise' ? 'selected' : ''}>Em Análise</option>
           <option value="Análise Realizada" ${projeto.status == 'Análise Realizada' ? 'selected' : ''}>Análise Realizada</option>
           <option value="Análise Aprovada" ${projeto.status == 'Análise Aprovada' ? 'selected' : ''}>Análise Aprovada</option>
           <option value="Iniciado" ${projeto.status == 'Iniciado' ? 'selected' : ''}>Iniciado</option>
           <option value="Planejado" ${projeto.status == 'Planejado' ? 'selected' : ''}>Planejado</option>
           <option value="Em Andamento" ${projeto.status == 'Em Andamento' ? 'selected' : ''}>Em Andamento</option>
           <option value="Encerrado" ${projeto.status == 'Encerrado' ? 'selected' : ''}>Encerrado</option>
           <option value="Cancelado" ${projeto.status == 'Cancelado' ? 'selected' : ''}>Cancelado</option>
       </select>

        <br/><br/>

        <!-- Orçamento -->
        <label for="orcamento">Orçamento:</label>
        <input type="number" id="orcamento" name="orcamento" step="0.01" value="${projeto.orcamento}" />
        <br/><br/>

        <!-- Risco -->
        <label for="risco">Risco:</label>
        <select id="risco" name="risco" required>
            <option value="">Selecione</option>
            <option value="Baixo" ${projeto.risco == 'Baixo' ? 'selected' : ''}>Baixo</option>
            <option value="Médio" ${projeto.risco == 'Médio' ? 'selected' : ''}>Médio</option>
            <option value="Alto" ${projeto.risco == 'Alto' ? 'selected' : ''}>Alto</option>
        </select>
        <br/><br/>

        <!-- Gerente -->
        <label for="gerente">Gerente:</label>
        <select name="gerente.id" required>
            <c:forEach items="${gerentes}" var="gerente">
                <option value="${gerente.id}" ${projeto.gerente != null && gerente.id == projeto.gerente.id ? 'selected' : ''}>
                    ${gerente.nome} - ${gerente.cpf}
                </option>
            </c:forEach>
        </select>

        <br/><br/>

        <!-- Botões -->
        <button type="button" onclick="submitForm()">Salvar</button>
        <button type="reset">Limpar</button>
    </form>
    <script>
        function submitForm() {
            const form = document.getElementById('projetoForm');
            const formData = new FormData(form);

            const jsonData = {};
            formData.forEach((value, key) => {
                jsonData[key] = value;
            });

            jsonData['gerenteId'] = jsonData['gerente.id'];
            delete jsonData['gerente.id'];

            fetch('/projetos/salvar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(jsonData)
            }).then(response => {
                if (response.ok) {
                    alert('Projeto salvo com sucesso!');
                    window.location.href = '/gerenciamentoProjetos';
                } else {
                    alert('Erro ao salvar projeto!');
                }
            }).catch(error => {
                console.error('Erro:', error);
                alert('Erro ao salvar projeto!');
            });
        }
    </script>
</body>
</html>
