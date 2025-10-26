# **Analizador FEN (FenParser)**

Un analizador sintáctico y visualizador de posiciones de ajedrez basado en la Notación Forsyth-Edwards (FEN), creado para el curso de Lenguajes de Programación.

## **Integrantes**

* Thomas Serna Saldarriaga
* David Alzate Monroy

## **1\. Finalidad del Proyecto**

Este proyecto es un analizador sintáctico (parser) para la **Notación Forsyth-Edwards (FEN)**. La finalidad principal es crear una aplicación de escritorio en Java que cumpla con los siguientes objetivos:

1. **Validación de Sintaxis:** Recibir una cadena FEN introducida por el usuario y validarla rigurosamente contra la gramática formal de la notación.
2. **Visualización Gráfica:** Si la cadena FEN es válida, la aplicación debe "pintar" o dibujar la posición de ajedrez correspondiente en un tablero gráfico.
3. **Manejo de Errores:** Si la cadena FEN es inválida, la aplicación debe informar al usuario con un mensaje de error claro, especificando la naturaleza del error sintáctico encontrado.

### **Arquitectura**

El proyecto está diseñado siguiendo una estricta **separación de responsabilidades** (Separation of Concerns), dividiendo la aplicación en dos capas principales:

* **Capa Lógica (`FenParser.java`):** Contiene toda la lógica de validación y análisis. No tiene conocimiento ni dependencia de la interfaz gráfica.
* **Capa de Vista (`ControladorVisual.java`):** Maneja toda la interacción con el usuario (ventana, botones, dibujo del tablero) y es responsable de mostrar los resultados del análisis o los mensajes de error.
* **Excepción Personalizada (`FenParseException.java`):** Sirve como un puente de comunicación claro para que la capa lógica informe de errores específicos a la capa de vista.

## **2\. Lenguaje Utilizado**

* **Lenguaje:** Java
* **Framework UI:** Java Swing

## **3\. Requisitos Mínimos de Operación**

* **Java Development Kit (JDK)**: Versión 11 o superior.
* **Java Runtime Environment (JRE)**: Versión 11 o superior.