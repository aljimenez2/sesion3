#language: es
Característica: Blog
	@wip
	Escenario: Visitar blog
		Dado el usuario "Juan" con email "userx@gmail.com" con password "userx" 
		Cuando se loguea en el blog y entre a la seccion entradas
		Entonces se encuentra en la vista de "http://localhost:5000/entries/"