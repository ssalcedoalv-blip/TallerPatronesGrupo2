# Borrador del informe (máx. 4 páginas) – Grupo 2

## 1. Contraste Builder vs. Template Method
**Qué comparten:** ambos manejan *procesos por pasos*; ambos separan "cómo se hace" de "qué se obtiene"; ambos eliminan código duplicado o frágil; en ambos el cliente deja de controlar los detalles internos.

**Dónde divergen:**
| | Builder | Template Method |
|---|---|---|
| Pregunta que responde | ¿Cómo construyo un objeto complejo con muchos parámetros, algunos opcionales? | ¿Cómo fijo el orden de un algoritmo y dejo variar algunos pasos? |
| Categoría | Creacional | Comportamiento |
| Mecanismo | Composición: un objeto Builder acumula estado y entrega el producto en `build()` | Herencia: la clase base define el método plantilla; las subclases redefinen pasos |
| Quién controla el orden | El cliente decide qué pasos invocar y en qué orden | La clase base fija el orden; el cliente no puede alterarlo |
| Resultado | Un objeto (producto) | La ejecución de un algoritmo |

**Frase para la diapositiva de contraste:** *Builder deja que el cliente elija los pasos para fabricar un objeto; Template Method le quita al cliente esa libertad y fija el esqueleto del algoritmo.*

## 2. UML (dibujar en draw.io / PlantUML)
Builder: `OrdenServicio` ◆— `Builder` (clase interna estática con `telefono()`, `urgente()`, `build()`), constructor privado de `OrdenServicio` que recibe `Builder`.
Template Method: `Reporte` (abstracta, `+generar() final`, `#encabezado()`, `#fila()`, `#pie()`) ◁— `ReporteCsv`, `ReporteHtml`, `ReporteMarkdown`.

## 3. Ítems tipo Saber Pro

### Ítem 1 (código – Builder)
Un taller de motos usa esta clase y su código cliente:
```java
new OrdenServicio("KLM98A", "Marta", "3109876543", "FRENOS", "Pedro", true, true, 0.5, "");
```
El equipo comete errores frecuentes al intercambiar los dos valores booleanos, y se aceptan órdenes con datos inconsistentes. Se pide una solución que permita construir la orden indicando solo los datos necesarios, con nombres legibles y validando al final. ¿Qué patrón resuelve mejor este defecto?

A. Factory Method  B. Builder  C. Prototype  D. Abstract Factory

### Ítem 2 (código – Template Method)
Un sistema genera reportes con este método:
```java
switch (formato) {
  case "CSV":  /* encabezado, filas, total */ break;
  case "HTML": /* encabezado, filas */ break;   // olvidó el total
  case "TEXTO":/* encabezado, filas, total */ break;
}
```
Cada rama repite el orden encabezado → filas → pie, y agregar Markdown exige modificar el método. Se necesita fijar el orden en un solo lugar y permitir formatos nuevos sin tocar el código existente. ¿Qué patrón corrige el defecto?

A. Strategy  B. State  C. Template Method  D. Command

### Ítem 3 (situación de diseño – discriminar el par)
Una aplicación debe (i) crear solicitudes de crédito con 12 campos, la mayoría opcionales, y (ii) ejecutar siempre el mismo proceso de aprobación (validar → calcular riesgo → decidir → notificar), donde solo cambia el cálculo de riesgo según el tipo de crédito. ¿Qué combinación de patrones es la más adecuada?

A. Builder para (i) y Template Method para (ii)
B. Template Method para (i) y Builder para (ii)
C. Factory Method para (i) y Strategy para (ii)
D. Prototype para (i) y Template Method para (ii)

## 4. Hoja de respuestas (independiente)

**Ítem 1 – Clave: B.** El problema es construir un objeto con muchos parámetros, opcionales y con validación: Builder permite construcción incremental legible y `build()` valida.
- A (Factory Method): decide *qué subclase* instanciar, no resuelve el exceso de parámetros; confunde porque ambos son creacionales.
- C (Prototype): clona un objeto existente; plausible porque se podría pensar en copiar una orden "base", pero no valida ni simplifica la construcción.
- D (Abstract Factory): crea familias de objetos relacionados; el estudiante lo asocia con "construcción compleja" pero no hay familias aquí.

**Ítem 2 – Clave: C.** El orden fijo de pasos con partes variables es la definición operativa de Template Method; el esqueleto va en un método `final` de la clase base.
- A (Strategy): también elimina el `switch`, pero intercambia el algoritmo *completo* por composición; es el distractor más fuerte porque ambos usan polimorfismo. No garantiza el orden fijo de pasos.
- B (State): cambia el comportamiento según el estado interno; el `switch` aquí es por formato, no por estado.
- D (Command): encapsula solicitudes como objetos; confunde por tener una operación `execute` por subclase.

**Ítem 3 – Clave: A.** (i) muchos campos opcionales → Builder; (ii) esqueleto fijo con un paso variable → Template Method.
- B: invierte los roles, plausible para quien memoriza nombres sin el problema.
- C: Factory Method y Strategy son dos patrones válidos en otros contextos, pero no resuelven (i) ni garantizan el orden en (ii).
- D: Prototype clona, no construye por partes; solo acierta el segundo patrón.
