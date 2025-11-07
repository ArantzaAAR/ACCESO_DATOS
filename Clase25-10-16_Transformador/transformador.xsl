<?xml version="1.0" encoding="UTF-8" ?>
<stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!--COMENTARIO: Siempre fuera de los donos-->
    <xsl:template match="/"><!--pongo la raiz para que extraiga la información-->
        <!--Incluyo el contenido que voy a transformar-->
        <html>
            <head>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                      crossorigin="anonymous"/>
               <title>Películas transformadas</title>
            </head>
            <body>
                <div class="container mt-4">
                    <h1>Listado Películas</h1>
                    <!--COPIO DE BOOSTRAP EL EJEMPLO DE CARD-->
                    <div class="row"><!--hago filas para las diferentes películas-->
                        <xsl:for-each select="peliculas/pelicula"><!--en cada película que me haga un card independiente-->
                            <div class="col-md-4 md-4"><!--en cada película me hace una columna dentro de la fila-->
                                <div class="card" style="width: 18rem;">
                                    <div class="card-body">
                                        <xsl:variable name="imagen" select="@poster"/>
                                        <img class="card-img-top" src="{@poster}" alt="{@titulo}"
                                             style="height: 350px; object-fit: cover;"/>
                                        <h5 class="card-title">
                                            <xsl:value-of select="@titulo"/><!--Incluyo el atributo con @-->
                                        </h5>
                                        <p class="card-text">
                                            <xsl:value-of select="sinopsis"/><!--incluyo contenido-->
                                        </p>
                                        <h5>Actores</h5><!--incluyo los actores, como son varios tengo que meter una lista con un for each-->
                                        <ul class="list-group"><!--utilizo componente de bootstrap para que agrupe-->
                                            <xsl:for-each select="personajes/personaje"><!--bucle para que busque los actores-->
                                                <li class="list-group-item"><!--utilizo componente de bootstrap para que agrupe-->
                                                    <xsl:value-of select="@actor"/>
                                                </li>
                                            </xsl:for-each>
                                        </ul>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                        </xsl:for-each>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</stylesheet>