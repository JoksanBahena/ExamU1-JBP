package server;

public class BeanRFC {
    String nombre;
    String apellidoP;
    String apellidoM;
    String curp;
    String fechaNac;
    String rfc;

    public BeanRFC() {
    }

    public BeanRFC(String nombre, String apellidoP, String apellidoM, String curp, String fechaNac, String rfc) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.curp = curp;
        this.fechaNac = fechaNac;
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return "---------------------"+ "\n" + "Nombre: " + nombre + "\n" + "Apellido Paterno: " + apellidoP + "\n" + "Apellido Materno: " + apellidoM +
        "\n" + "CURP: " + curp + "\n" + "Fecha de nacimiento: " + fechaNac + "\n" + "RFC: " + rfc + "\n";
    }
}
