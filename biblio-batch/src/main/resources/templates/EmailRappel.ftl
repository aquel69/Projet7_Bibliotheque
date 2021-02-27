<!DOCTYPE HTML>
<head>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="/css/styleEmail.css">
</head>
<body>

<table>
    <tr>
        <td>
            <a href="https://localhost:8443"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-cPHM9g72sUuXwYWDfIOTyqNKMfOQFfT3Dg&usqp=CAU" alt="Logo Bibliothèque"/></a>
        </td>
    </tr>
    <tr>
        <td>
            <p>Chèr(e) abonné(e),</p>
            <p><br>Nous vous rapellons que vous avez emprunté '${pret.ouvragePret.livre.titre}' en date du <strong>${(pret.dateDEmprunt).format('dd-MM-yyyy')}</strong>.</p>
            <p>La période d'emprunt arrive bientôt à son terme le <strong>${(pret.dateDeRestitution).format('dd-MM-yyyy')}</strong>.</p>
            <p>Pensez à le prolonger ou à nous le ramener.</p>
            <p><br>Merci de votre compréhension et à bientôt.</p>
        </td>
    </tr>
    <tr>
        <td>
            <p><br>Bibliothèque Bruce Wayne<br>Rue du manoir<br>NJ 12345 Gotham</p>
        </td>
    </tr>
</table>

</body>
</html>