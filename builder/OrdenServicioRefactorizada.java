/** BUILDER - VERSIÓN REFACTORIZADA: construcción incremental, legible y validada. */
public class OrdenServicioRefactorizada {
    private final String placa, cliente, tipoServicio;           // obligatorios
    private final String telefono, mecanico, observaciones;      // opcionales
    private final boolean urgente, requiereRepuestos;
    private final double descuento;

    private OrdenServicioRefactorizada(Builder b) {
        placa = b.placa; cliente = b.cliente; tipoServicio = b.tipoServicio;
        telefono = b.telefono; mecanico = b.mecanico; observaciones = b.observaciones;
        urgente = b.urgente; requiereRepuestos = b.requiereRepuestos; descuento = b.descuento;
    }

    public static class Builder {
        private final String placa, cliente, tipoServicio;
        private String telefono = "N/A", mecanico = "Sin asignar", observaciones = "";
        private boolean urgente = false, requiereRepuestos = false;
        private double descuento = 0.0;

        public Builder(String placa, String cliente, String tipoServicio) {
            this.placa = placa; this.cliente = cliente; this.tipoServicio = tipoServicio;
        }
        public Builder telefono(String v)        { telefono = v; return this; }
        public Builder mecanico(String v)        { mecanico = v; return this; }
        public Builder observaciones(String v)   { observaciones = v; return this; }
        public Builder urgente()                 { urgente = true; return this; }
        public Builder conRepuestos()            { requiereRepuestos = true; return this; }
        public Builder descuento(double v)       { descuento = v; return this; }

        public OrdenServicioRefactorizada build() {
            if (placa == null || placa.isEmpty()) throw new IllegalStateException("La placa es obligatoria");
            if (descuento < 0 || descuento > 0.3) throw new IllegalStateException("Descuento fuera de rango (0 a 0.3)");
            if (urgente && descuento > 0) throw new IllegalStateException("Una orden urgente no admite descuento");
            return new OrdenServicioRefactorizada(this);
        }
    }

    @Override public String toString() {
        return "Orden[" + placa + ", " + cliente + ", " + tipoServicio + ", mecánico=" + mecanico
            + ", urgente=" + urgente + ", repuestos=" + requiereRepuestos
            + ", descuento=" + descuento + ", obs=" + observaciones + "]";
    }

    public static void main(String[] args) {
        System.out.println(new Builder("MON123", "Ana Pérez", "REVISION").build());
        System.out.println(new Builder("BXT45F", "Luis Gómez", "CAMBIO_ACEITE")
            .telefono("3001234567").mecanico("Carlos").conRepuestos().descuento(0.1).build());
        try {
            new Builder("KLM98A", "Marta Ruiz", "FRENOS").urgente().descuento(0.5).build();
        } catch (IllegalStateException e) {
            System.out.println("Rechazada: " + e.getMessage());
        }
    }
}
