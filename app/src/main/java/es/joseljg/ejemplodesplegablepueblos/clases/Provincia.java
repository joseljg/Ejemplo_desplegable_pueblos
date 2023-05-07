package es.joseljg.ejemplodesplegablepueblos.clases;

public class Provincia {
    // atributos
    private String nombre_Comunidad;
    private String codigo_comunidad;
    private String nombre1_provincia;
    private String nombre2_provincia;
    private String codigo_provincia;

    // constructores
    public Provincia(String nombre_Comunidad, String codigo_comunidad, String nombre1_provincia, String nombre2_comunidad, String codigo_provincia) {
        this.nombre_Comunidad = nombre_Comunidad;
        this.codigo_comunidad = codigo_comunidad;
        this.nombre1_provincia = nombre1_provincia;
        this.nombre2_provincia = nombre2_comunidad;
        this.codigo_provincia = codigo_provincia;
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

    public String getNombre1_provincia() {
        return nombre1_provincia;
    }

    public void setNombre1_provincia(String nombre1_provincia) {
        this.nombre1_provincia = nombre1_provincia;
    }

    public String getNombre2_provincia() {
        return nombre2_provincia;
    }

    public void setNombre2_provincia(String nombre2_provincia) {
        this.nombre2_provincia = nombre2_provincia;
    }

    public String getCodigo_provincia() {
        return codigo_provincia;
    }

    public void setCodigo_provincia(String codigo_provincia) {
        this.codigo_provincia = codigo_provincia;
    }

    @Override
    public String toString() {
        return nombre2_provincia;
    }

}
