<!DOCTYPE HTML>
<head>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link #include rel="stylesheet" type="text/css" href="/css/styleEmail.css">

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
            <p>${.now?time?string('HH:mm')}</p>
            <p>Chère abonné,</p>
            <p>nous vous rapellons que vous avez emprunté '${pret.ouvragePret.livre.titre}' en date du : ${pret.dateDEmprunt}</p>
            <p>La période d'emprunt étant arrivé à terme le ${pret.dateDeRestitution}.</p>
            <p>Nous vous invitons à ramener l'ouvrage rapidement afin que d'autres lecteurs puissent en bénéficier.</p>
            <p>Merci de votre compréhension et à bientôt.</p>
        </td>
    </tr>
    <tr>
        <td>
            <p>Bibliothèque Bruce Wayne</p>
            <p>Rue du manoir</p>
            <p>NJ 12345 Gotham</p>
        </td>
    </tr>
</table>

</body>
</html>