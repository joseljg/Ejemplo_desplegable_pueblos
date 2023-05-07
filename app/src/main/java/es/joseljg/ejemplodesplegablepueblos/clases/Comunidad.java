package es.joseljg.ejemplodesplegablepueblos.clases;

import java.util.Objects;

public class Comunidad {
    private String nombre_Comunidad;
    private String codigo_comunidad;

    public Comunidad(String nombre_Comunidad, String codigo_comunidad) {
        this.nombre_Comunidad = nombre_Comunidad;
        this.codigo_comunidad = codigo_comunidad;
    }

    public String getNombre_Comunidad() {
        return nombre_Comunidad;
    }

    public void setNombre_Comunidad(String nombre_Comunidad) {
        this.nombre_Comunidad = nombre_Comunidad;
    }

    public String getCodigo_comunidad() {
        return codigo_comunidad;
    }

    public void setCodigo_comunidad(String codigo_comunidad) {
        this.codigo_comunidad = codigo_comunidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comunidad)) return false;
        Comunidad comunidad = (Comunidad) o;
        return codigo_comunidad.equals(comunidad.codigo_comunidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_comunidad);
    }

    @Override
    public String toString() {
        return nombre_Comunidad;
    }
}
