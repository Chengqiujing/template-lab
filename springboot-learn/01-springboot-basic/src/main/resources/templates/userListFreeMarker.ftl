<html>
    <head>
        <title>展示用户数据</title>
        <meta charset="UTF-8"/>
    </head>
    <body>
        <table border="1" align="center" width="50%">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>AGE</th>
            </tr>
            <#list list as user>
                <tr>
                    <td>${user.userid}</td>
                    <td>${user.userName}</td>
                    <td>${user.userAge}</td>
                </tr>
            </#list>
        </table>
    </body>
</html>