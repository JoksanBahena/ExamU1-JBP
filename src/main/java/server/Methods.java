package server;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Methods {

    public String rfc (String nombre, String apellidoP, String apellidoM, String año, String mes, String dia) {
        String letraNom = nombre.substring(0,1);
        String letraApeP = apellidoP.substring(0,2);
        String letraApeM = apellidoM.substring(0,1);
        String numAño = año.substring(2,4);

        String randomXD = Methods.generateRandomString(3);

        String rfcT = letraApeP+letraApeM+letraNom+numAño+mes+dia+randomXD;

        return rfcT.toUpperCase();
    }

    public static String generateRandomString(int length) {

        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }

    public boolean guardarDatos(String nombre, String apellido1, String apellido2, String curp, String fechaNac, String rfc){
        DaoRFC daoRFC = new DaoRFC();
        return daoRFC.guardarDatos(nombre, apellido1, apellido2, curp, fechaNac, rfc);
    }

    public String ListPersons(){
        DaoRFC daoRFC = new DaoRFC();
        List<BeanRFC> listPersons = daoRFC.listPersons();

        String dato = "";
        String fullDatos = "";

        for (BeanRFC datos : listPersons) {
            dato = "------------------------------------" + "\nNombre: " + datos.getNombre() + "\nApellido Paterno: " + datos.getApellidoP() + "\nApellido Materno: " + datos.getApellidoM() +
                    "\nCURP: " + datos.getCurp() + "\nFecha de nacimiento: " + datos.getFechaNac() + "\nRFC: " + datos.getRfc() + "\n";


            fullDatos = fullDatos.concat(dato);

        }

        return fullDatos;
    }

    public String datosPersona(String curp) {
        DaoRFC daoRFC = new DaoRFC();
        BeanRFC beanRFC = daoRFC.consulta(curp);

        String datos = "------------------------------------" + "\nNombre: " + beanRFC.getNombre() + "\nApellido Paterno: " + beanRFC.getApellidoP() + "\nApellido Materno: " +
                beanRFC.getApellidoM() + "\nCURP: " + beanRFC.getCurp() + "\nFecha de nacimiento: " + beanRFC.getFechaNac() + "\nRFC: " + beanRFC.getRfc() + "\n----------------------";

        return datos;
    }

    public boolean eliminarPersona (String curp) {
        DaoRFC daoRFC = new DaoRFC();

        return daoRFC.deletePerson(curp);
    }

    public boolean modificarPersona(String nombreM, String apellido1M, String apellido2M, String curpM, String fechaNacM, String rfcM){
        DaoRFC daoRFC = new DaoRFC();
        BeanRFC person = new BeanRFC(nombreM, apellido1M, apellido2M, curpM, fechaNacM, rfcM);
        boolean result = daoRFC.updatePerson(person);
        return result;

        /*DaoRFC daoRFC = new DaoRFC();
        boolean result = daoRFC.updatePerson(nombre, apellido1, apellido2, curp, fechaNac, rfc);
        return result;*/
    }

    public boolean verificarCurp (String curp) {
        DaoRFC daoRFC = new DaoRFC();
        List<BeanRFC> listPersons = daoRFC.listPersons();
        boolean validacion = false;

        for (BeanRFC datos: listPersons) {

            if (curp.equals(datos.getCurp())) {
                validacion = true;

            }
        }

        return validacion;
    }


}
