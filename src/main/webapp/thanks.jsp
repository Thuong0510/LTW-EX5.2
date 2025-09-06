<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Thanks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css"/>
</head>
<body>
<div class="container thanks-container">
    <h1>THANKS FOR JOINING OUR EMAIL LIST</h1>

    <p class="description">Here is the information that you entered:</p>

    <div class="info-grid">
        <div><strong>Email:</strong></div><div>${user.email}</div>
        <div><strong>First Name:</strong></div><div>${user.firstName}</div>
        <div><strong>Last Name:</strong></div><div>${user.lastName}</div>
    </div>

    <form action="${pageContext.request.contextPath}/emailList" method="get">
        <input type="hidden" name="action" value="join">
        <input id="submit" type="submit" value="Return">
    </form>
</div>

</body>
</html>
