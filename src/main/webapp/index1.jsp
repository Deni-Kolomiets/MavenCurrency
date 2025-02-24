<%--
  Created by IntelliJ IDEA.
  User: deni0
  Date: 08.01.2025
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Курсы валют</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1, h2 {
            color: #333;
        }
        .section {
            margin-bottom: 30px;
        }
        .section input, .section button {
            margin: 5px 0;
            display: block;
        }
        #currency-table {
            width: 100%;
            border-collapse: collapse;
        }
        #currency-table th, #currency-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        #currency-table th {
            background-color: #f2f2f2;
        }
        #message {
            color: red;
        }
    </style>
</head>
<body>

<h1>Управление валютами</h1>

<div class="section">
    <h2>Получить все валюты</h2>
    <form action="/MavenCurrencies/currencies" method="get">
        <button type="submit">Показать все валюты</button>
    </form>
    <table id="currency-table">
        <thead>
        <tr>
            <th>Код</th>
            <th>Название</th>
            <th>Курс</th>
        </tr>
        </thead>
        <tbody id="currency-table-body">
        <!-- Данные будут добавлены здесь -->
        </tbody>
    </table>
</div>

<div class="section">
    <h2>Получить валюту по коду</h2>
    <form action="/getCurrency" method="get">
        <label for="code">Currency Code:</label>
        <input type="text" id="code" name="code">
        <input type="submit" value="Submit">
    </form>
    <!--
    <form action="/productController" method="get">
          <button type="submit">Контроллер продуктов</button>
      </form>
    -->
    <p id="currency-details"></p>
</div>

<div class="section">
    <h2>Добавить новую валюту</h2>
    <input type="text" id="new-code" name="code" placeholder="Код валюты">
    <input type="text" id="new-name" name="name" placeholder="Название валюты">
    <input type="number" id="new-rate" name="rate" placeholder="Курс валюты" step="0.01">  <!-- Тут rate проверить, назв другое -->
    <button onclick="/addCurrency">Добавить валюту</button>
    <p id="add-message"></p>
</div>

<div class="section">
    <h2>Изменить существующую валюту</h2>
    <input type="text" id="edit-code" placeholder="Код валюты для изменения">
    <input type="text" id="edit-name" placeholder="Новое название (необязательно)">
    <input type="number" id="edit-rate" placeholder="Новый курс (необязательно)" step="0.01">
    <form action="/MavenCurrency/updateCurrency" method="get">
        <button type="submit">Изменить валюту</button>
    </form>
    <p id="edit-message"></p>
</div>

</body>
</html>
