import java.util.*;

/** TEMPLATE METHOD - VERSIÓN INICIAL (con defectos): switch + algoritmo duplicado. */
public class ReporteInicial {
    static class Nota { final String estudiante; final double valor;
        Nota(String e, double v) { estudiante = e; valor = v; } }

    // Defecto 1: un switch que crece con cada formato nuevo (hay que modificar la clase).
    // Defecto 2: el esqueleto (encabezado -> filas -> pie) se repite en cada rama.
    static String generar(String formato, List<Nota> notas) {
        StringBuilder sb = new StringBuilder();
        switch (formato) {
            case "CSV":
                sb.append("estudiante,nota\n");
                for (Nota n : notas) sb.append(n.estudiante).append(",").append(n.valor).append("\n");
                sb.append("total,").append(notas.size()).append("\n");
                break;
            case "HTML":
                sb.append("<table>\n");
                for (Nota n : notas) sb.append("<tr><td>").append(n.estudiante).append("</td><td>").append(n.valor).append("</td></tr>\n");
                sb.append("</table>\n"); // olvidó el total: la duplicación genera inconsistencias
                break;
            case "TEXTO":
                sb.append("REPORTE DE NOTAS\n");
                for (Nota n : notas) sb.append(n.estudiante).append(": ").append(n.valor).append("\n");
                sb.append("Total: ").append(notas.size()).append("\n");
                break;
            default: throw new IllegalArgumentException("Formato no soportado: " + formato);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Nota> notas = Arrays.asList(new Nota("Ana", 4.5), new Nota("Luis", 3.8));
        for (String f : new String[]{"CSV", "HTML", "TEXTO"}) System.out.println("--- " + f + " ---\n" + generar(f, notas));
    }
}
