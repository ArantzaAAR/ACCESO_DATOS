<?xml version="1.0" encoding="UTF-8" ?>
<stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">//indica la versión correspondiente

    <xsl:template match="/"><!--se crea una plantilla. Se añade match="/" para que indique que parta de la raiz para que realice la transformación en el xml-->
            <html><!--metemos etiqueta HTML, porque queremos que la info se meta en una web-->
                    <head><!--se pone estructura HTML. Se puede poner CSS, una libreria...-->
                        <title>Listin Usuario</title>
                    </head>
                <body>
                    <h1>Listado de usuarios</h1>
                    <ul>
                        <!--para que me permita meter lógica en la transformación utilizo: if, for, for-each, when-->
                        <xsl:for-each select="personas/persona">//creo for-each para que seleccione del usuario los datos de los nodos
                            <!--for-each va iterando con cada una de personas/persona para quedarse con un elemento-->
                            <li>
                                <xsl:value-of select="nombre"/><!--Con value-of se llama al dato del nodo nombre-->
                                <xsl:value-of select="apellido"/>
                                DNI: <xsl:value-of select="@dni"/><!--Es un atributo del nodo persona y se  llama con @-->
                            </li>
                        </xsl:for-each>
                    </ul>
                </body>
            </html>
    </xsl:template>
</stylesheet>