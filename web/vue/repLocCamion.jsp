<%-- 
    Document   : repLocCamion
    Created on : 14 mars 2017, 11:10:27
    Author     : haddad1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TODO supply a title</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="/vue/style.css"/>
	</head>
	<body>
		<header>
			<nav>
				<a class='but but-primary' href="?p=localiser">Localiser camion</a>
				<a class='but' href="?p=deplacer">Déplacer camion</a>
				<a class='but' href="?p=consulter">Consulter distance</a>
			</nav>
		</header>

		<div class='bloc'>
			Le camion [${c.immat}] se situe à ${c.villeLoc}.
			<div class="erreur">${err}</div>
		</div>
	</body>
</html>
