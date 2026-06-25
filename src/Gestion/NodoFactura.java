package Gestion;

import Clases.Factura;

public class NodoFactura {
    private Factura factura;
    private NodoFactura izquierdo;
    private NodoFactura derecho;

    public NodoFactura(Factura factura) {
        this.factura = factura;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Factura getFactura() { return factura; }
    public void setFactura(Factura factura) { this.factura = factura; }
    public NodoFactura getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoFactura izquierdo) { this.izquierdo = izquierdo; }
    public NodoFactura getDerecho() { return derecho; }
    public void setDerecho(NodoFactura derecho) { this.derecho = derecho; }
}
