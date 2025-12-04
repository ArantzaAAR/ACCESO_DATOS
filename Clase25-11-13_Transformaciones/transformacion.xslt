<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/"> <!--indico que quiero buscar desde la raiz usuarios-->
        <html>
            <head>
                <title>Listado Usuario</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous" />
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
                        crossorigin="anonymous"/>
            </head>
            <body class="container">
                <h1>Listado de usuarios</h1>
                <ul class="list-group"><!--para que me haga una lista por cada usuario, tengo que hacer un for each-->
                    <xsl:for-each select="/usuarios/usuario [Telefono>2020]"> <!--[] sirve como XPath, para identificar un filtro de búsqueda. Se hacen querys de búsqueda-->
                        <!--en el caso de quiere realizar una query de búsqueda con un atributo es necesario [@id<20]-->
                        <li class="list-group-item">
                            <xsl:value-of select="@id"/> - <!--como es un atributo tengo que poner @-->
                            <xsl:value-of select="nombre"/> -
                            <xsl:value-of select="mail"/> -
                            <xsl:value-of select="Telefono"/>
                        </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>