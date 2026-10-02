import java.util.*;

/** TEMPLATE METHOD - VERSIÓN REFACTORIZADA: el esqueleto vive en la clase base. */
public class ReporteRefactorizado {
    static class Nota { final String estudiante; final double valor;
        Nota(String e, double v) { estudiante = e; valor = v; } }

    static abstract class Reporte {
        // Método plantilla: fija el orden de los pasos y no se puede sobrescribir.
        public final String generar(List<Nota> notas) {
            StringBuilder sb = new StringBuilder();
            sb.append(encabezado());
            for (Nota n : notas) sb.append(fila(n));
            sb.append(pie(notas.size()));
            return sb.toString();
        }
        protected abstract String encabezado();
        protected abstract String fila(Nota n);
        protected abstract String pie(int total);
    }

    static class ReporteCsv extends Reporte {
        protected String encabezado() { return "estudiante,nota\n"; }
        protected String fila(Nota n) { return n.estudiante + "," + n.valor + "\n"; }
        protected String pie(int t)   { return "total," + t + "\n"; }
    }
    static class ReporteHtml extends Reporte {
        protected String encabezado() { return "<table>\n"; }
        protected String fila(Nota n) { return "<tr><td>" + n.estudiante + "</td><td>" + n.valor + "</td></tr>\n"; }
        protected String pie(int t)   { return "</table>\n<p>Total: " + t + "</p>\n"; }
    }
    // Extensión sin modificar nada existente: solo una clase nueva.
    static class ReporteMarkdown extends Reporte {
        protected String encabezado() { return "| Estudiante | Nota |\n|---|---|\n"; }
        protected String fila(Nota n) { return "| " + n.estudiante + " | " + n.valor + " |\n"; }
        protected String pie(int t)   { return "\n**Total:** " + t + "\n"; }
    }

    public static void main(String[] args) {
        List<Nota> notas = Arrays.asList(new Nota("Ana", 4.5), new Nota("Luis", 3.8));
        Reporte[] reportes = { new ReporteCsv(), new ReporteHtml(), new ReporteMarkdown() };
        for (Reporte r : reportes) System.out.println("--- " + r.getClass().getSimpleName() + " ---\n" + r.generar(notas));
    }
}
