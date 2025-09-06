<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Join our email list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<div class="container">
    <h1>JOIN OUR EMAIL LIST</h1>

    <form action="${pageContext.request.contextPath}/emailList" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-row">
            <label for="firstName">First Name:</label>
            <input id="firstName" name="firstName" type="text"
                   value="${not empty requestScope.firstName ? requestScope.firstName : sessionScope.firstName}"
                   required>
        </div>

        <div class="form-row">
            <label for="lastName">Last Name:</label>
            <input id="lastName" name="lastName" type="text"
                   value="${not empty requestScope.lastName ? requestScope.lastName : sessionScope.lastName}"
                   required>
        </div>

        <div class="form-row">
            <label for="email">Email:</label>
            <input id="email" name="email" type="email"
                   value="${not empty requestScope.email ? requestScope.email : sessionScope.email}"
                   required>
        </div>

        <button id="submit" type="submit">JOIN NOW</button>
    </form>
</div>
</body>
</html>
