
# Analizador FEN (FenParser)

Aplicación de escritorio en **Java** que permite **validar** y **visualizar** posiciones de ajedrez descritas mediante la notación **FEN (Forsyth–Edwards Notation)**.

---

## Integrantes

- Thomas Serna Saldarriaga
- David Alzate Monroy

---

## 1. Objetivo del proyecto

El programa recibe una **cadena FEN** escrita por el usuario y:

1. **Valida rigurosamente la sintaxis** de cada uno de los campos de la notación.
2. **Dibuja gráficamente el tablero** de ajedrez correspondiente si la cadena es válida.
3. **Informa los errores** mediante mensajes claros cuando la cadena es inválida.

### Validaciones implementadas

Se verifica:

1. **Campo 1 – Disposición de piezas**
    - 8 filas separadas por `/`.
    - Cada fila suma exactamente **8 casillas** (piezas + números).
    - Solo se permiten caracteres válidos:  
      `PNBRQKpnbrqk` y dígitos `1–8`.
    - No se permiten dos números consecutivos que excedan el tamaño de la fila.

2. **Campo 2 – Color al turno**
    - Solo se acepta `'w'` (blancas) o `'b'` (negras).

3. **Campo 3 – Derechos de enroque**
    - Cualquier combinación de `KQkq`, o `-` si no hay enroques disponibles.
    - Se rechazan caracteres repetidos o no válidos.

4. **Campo 4 – Casilla de captura al paso**
    - `-` si no hay captura al paso posible.
    - O una casilla válida en notación algebraica (`a3`, `h6`, etc.).
    - Se valida que la casilla tenga **columna entre `a` y `h`** y **fila entre `1` y `8`**.

5. **Campo 5 – Reloj de medias jugadas**
    - Número entero **≥ 0** (para regla de las 50 jugadas).

6. **Campo 6 – Número de jugadas completas**
    - Número entero **≥ 1**.

---

## 2. Arquitectura del proyecto

El proyecto se organiza en los paquetes `gui` y `parse`, además de una clase principal:

```text
src/
├─ gui/
│  ├─ PanelControl.java
│  ├─ PanelTablero.java
│  └─ VentanaPrincipal.java
├─ parse/
│  ├─ FenParseException.java
│  └─ FenParser.java
└─ FenParserMain.java
```

### Capa lógica – `parse`

- **`FenParser.java`**  
  Contiene toda la lógica de análisis y validación de la cadena FEN.
    - Divide la cadena en sus 6 campos.
    - Aplica cada una de las reglas de validación mencionadas.
    - Construye un **arreglo `int[8][8]`** con la posición de las piezas para ser usada por la capa gráfica.
    - Lanza excepciones específicas cuando encuentra un error.

- **`FenParseException.java`**  
  Excepción personalizada utilizada para reportar errores de parseo.
    - Incluye mensajes descriptivos (por ejemplo: *“Campo 1: fila 3 no suma 8 casillas”*).
    - Es capturada por la interfaz gráfica para mostrar el error al usuario.

### Capa de interfaz gráfica – `gui`

- **`VentanaPrincipal.java`**
    - Ventana principal de la aplicación.
    - Contiene el tablero, el panel de control y la zona de mensajes.

- **`PanelControl.java`**
    - Panel superior con:
        - Campo de texto para introducir la cadena FEN.
        - Botón **“Cargar FEN”**.
        - Botón **“Limpiar tablero”**.
    - Invoca a `FenParser` cuando se pulsa *Cargar FEN* y envía el resultado al tablero o muestra el mensaje de error.

- **`PanelTablero.java`**
    - Recibe el arreglo `int[8][8]` generado por el parser.
    - Dibuja un tablero de 8x8 con casillas alternadas.
    - Representa las piezas en cada casilla (según el convenio interno: valores positivos/negativos para piezas blancas/negras).

### Clase principal

- **`FenParserMain.java`**
    - Contiene el método `main`.
    - Inicializa el entorno gráfico (crea `VentanaPrincipal`) y lanza la aplicación.

---

## 3. Tecnologías utilizadas

- **Lenguaje:** Java
- **Versión recomendada:** JDK 21 o superior
- **Interface gráfica:** Java Swing

---

## 4. Uso de la aplicación

1. Abrir la aplicación (`FenParserMain`).
2. En el campo de texto superior, escribir una **cadena FEN** completa.
3. Pulsar **“Cargar FEN”**:
    - Si la cadena es válida:
        - El tablero se actualiza mostrando la posición.
    - Si la cadena es inválida:
        - Aparece un **mensaje de error** indicando el motivo.
4. Pulsar **“Limpiar tablero”** para reiniciar el tablero y el campo de texto.

### Ejemplos de cadenas FEN válidas

- Posición inicial estándar:

  ```text
  rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
  ```

- Posición con captura al paso y sin enroques:

  ```text
  rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPPP1PPP/RNBQKBNR b - e6 0 4
  ```

---

## 5. Manejo de errores

Cuando la entrada es incorrecta, `FenParser` lanza un `FenParseException` con un mensaje descriptivo. Algunos ejemplos de errores detectados:

- Número incorrecto de filas en el primer campo.
- Fila que no suma 8 casillas.
- Caracteres no válidos en los campos.
- Casilla de captura al paso fuera de rango.
- Campos numéricos no enteros o fuera de rango.

Estos mensajes se muestran en la interfaz gráfica, facilitando al usuario corregir la cadena FEN.