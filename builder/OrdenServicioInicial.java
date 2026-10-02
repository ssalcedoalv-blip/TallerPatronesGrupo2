/** BUILDER - VERSIÓN INICIAL (con defectos): constructor telescópico. */
public class OrdenServicioInicial {
    private final String placa, cliente, telefono, tipoServicio, mecanico, observaciones;
    private final boolean urgente, requiereRepuestos;
    private final double descuento;

    // Defecto: 9 parámetros; el orden es fácil de confundir y los opcionales obligan a pasar null/false/0.
    public OrdenServicioInicial(String placa, String cliente, String telefono, String tipoServicio,
                                String mecanico, boolean urgente, boolean requiereRepuestos,
                                double descuento, String observaciones) {
        this.placa = placa; this.cliente = cliente; this.telefono = telefono;
        this.tipoServicio = tipoServicio; this.mecanico = mecanico; this.urgente = urgente;
        this.requiereRepuestos = requiereRepuestos; this.descuento = descuento;
        this.observaciones = observaciones;
        // Defecto: sin validación; se puede crear una orden inconsistente.
    }

    @Override public String toString() {
        return "Orden[" + placa + ", " + cliente + ", " + tipoServicio + ", mecánico=" + mecanico
            + ", urgente=" + urgente + ", repuestos=" + requiereRepuestos
            + ", descuento=" + descuento + ", obs=" + observaciones + "]";
    }

    public static void main(String[] args) {
        // Caso simple: hay que rellenar todo con null/false/0 y contar posiciones.
        System.out.println(new OrdenServicioInicial("MON123", "Ana Pérez", null, "REVISION", null, false, false, 0, null));
        // Defecto visible: los dos booleanos son intercambiables sin que el compilador avise.
        System.out.println(new OrdenServicioInicial("BXT45F", "Luis Gómez", "3001234567", "CAMBIO_ACEITE", "Carlos", false, true, 0.1, ""));
        // Defecto visible: orden urgente con 50% de descuento (inconsistente) aceptada sin error.
        System.out.println(new OrdenServicioInicial("KLM98A", "Marta Ruiz", "3109876543", "FRENOS", "Pedro", true, true, 0.5, "Cliente espera"));
    }
}
