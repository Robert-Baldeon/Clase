package com.example.jpa.ejercicio_05.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class DireccionEmpotrada {
    private String calle;
    private String ciudad;
    private String tipoDireccion;

    public DireccionEmpotrada() {}

	public String getCalle() { return calle; }
	public void setCalle(String calle) { this.calle = calle; }
	public String getCiudad() { return ciudad; }
	public void setCiudad(String ciudad) { this.ciudad = ciudad; }
	public String getTipoDireccion() { return tipoDireccion; }
	public void setTipoDireccion(String tipoDireccion) { this.tipoDireccion = tipoDireccion; }
}
