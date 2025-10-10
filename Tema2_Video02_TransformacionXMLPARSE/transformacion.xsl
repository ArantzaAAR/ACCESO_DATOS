<?xml version="1.0" encoding="UTF-8" ?>
<stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
            <html>
                    <head>
                        <title>Listin Usuario</title>
                    </head>
                <body>
                    <h1>Listado de usuarios</h1>
                    <ul>
                        <xsl:for-each select="personas/persona">
                            <li>
                                <xsl:value-of select="nombre"/>
                                <xsl:value-of select="apellido"/>
                                <xsl:value-of select="@dni"/>
                            </li>
                        </xsl:for-each>
                    </ul>
                </body>
            </html>
    </xsl:template>
</stylesheet>