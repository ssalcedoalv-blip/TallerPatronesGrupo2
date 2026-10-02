# Taller de patrones de diseño – Grupo 2: Builder + Template Method
Programación III – Mag. Alberto Paternina León

Integrantes: Santiago Andrés Salcedo, Carlos Eduardo Ensuncho, José Carlos Díaz Arévalo

## Estructura
- `builder/OrdenServicioInicial.java` – constructor telescópico (defecto).
- `builder/OrdenServicioRefactorizada.java` – Builder.
- `template/ReporteInicial.java` – `switch` + algoritmo duplicado (defecto).
- `template/ReporteRefactorizado.java` – Template Method.

## Cómo ejecutar (JDK 8 o superior)
```
javac builder/*.java template/*.java -d out
java -cp out OrdenServicioInicial
java -cp out OrdenServicioRefactorizada
java -cp out ReporteInicial
java -cp out ReporteRefactorizado
```
