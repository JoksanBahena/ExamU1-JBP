package client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import server.BeanRFC;
import server.DaoRFC;
import server.Methods;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class clientRPC {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        String option = "", num1 = "", num2 = "";
        do {
            System.out.println("1. Registra persona");
            System.out.println("2. Consulta de una persona");
            System.out.println("3. Modificar persona");
            System.out.println("4. Consultar personas");
            System.out.println("5. Eliminar persona");
            System.out.println("6. Salir");

            option = scanner.next();

            if (isNumber(option)) {
                switch (Integer.parseInt(option)) {
                    case 1:
                        System.out.println("Ingrese su curp");
                        String curp = scanner.next();
                        System.out.println("------------------------------");

                        Object [] verificarCurp = {curp};
                        Boolean response9 = (Boolean) client.execute("Methods.verificarCurp", verificarCurp);

                        if (response9 == true) {
                            System.out.println("El CURP ya existe");
                            System.out.println("---------------------------");

                        }else {
                            System.out.println("Ingrese el nombre");
                            String nombre = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese el primer apellido");
                            String apellidoP = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese el segundo apellido");
                            String apellidoM = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese la fecha de nacimiento (dd/mm/aaaa)");
                            String fechaNac = scanner.next();
                            System.out.println("------------------------------");

                            /*System.out.println("Ingrese el mes de nacimiento");
                            String mes = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese el año de nacimiento");
                            String año = scanner.next();
                            System.out.println("------------------------------");

                            String fechaNacM = añoM + mesM + diaM;*/

                            Object [] rfc = {nombre, apellidoP, apellidoM, fechaNac};
                            String response = (String) client.execute("Methods.rfc", rfc);

                            Object [] datos = {nombre, apellidoP, apellidoM, curp, fechaNac, response};

                            Boolean response2 = (Boolean) client.execute("Methods.guardarDatos", datos);

                            System.out.println("RFC: "+response);

                            if (response2 == true) {
                                System.out.println("Registro exitoso");
                                System.out.println("------------------------------");

                            }else {
                                System.out.println("Error");
                                System.out.println("------------------------------");

                            }

                        }

                        break;

                    case 2:
                        System.out.println("Ingrese el CURP");
                        String curpC = scanner.next();
                        System.out.println("---------------------");
                        
                        Object [] verificarCurp4 = {curpC};
                        Boolean response12 = (Boolean) client.execute("Methods.verificarCurp", verificarCurp4);

                        if (response12 == false) {
                            System.out.println("No se encortro la CURP");
                            System.out.println("---------------------------");

                        }else {
                            Object[] consulta = {curpC};
                            String response4 = (String) client.execute("Methods.datosPersona", consulta);

                            System.out.println(response4);

                        }
                        break;

                    case 3:
                        System.out.println("Ingrese su curp");
                        String curpM = scanner.next();
                        System.out.println("------------------------------");

                        Object [] verificarCurp2 = {curpM};
                        Boolean response10 = (Boolean) client.execute("Methods.verificarCurp", verificarCurp2);

                        if (response10 == false) {
                            System.out.println("No se encortro la CURP");
                            System.out.println("---------------------------");

                        }else {
                            Object[] modificar = {curpM};
                            String response6 = (String) client.execute("Methods.datosPersona", modificar);

                            System.out.println(response6);

                            System.out.println("Ingrese el nombre");
                            String nombreM = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese el primer apellido");
                            String apellidoPM = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese el segundo apellido");
                            String apellidoMM = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese la fecha de nacimiento (dd/mm/aaaa)");
                            String fechaNac2 = scanner.next();
                            System.out.println("------------------------------");

                            /*System.out.println("Ingrese el mes de nacimiento");
                            String mesM = scanner.next();
                            System.out.println("------------------------------");

                            System.out.println("Ingrese el año de nacimiento");
                            String añoM = scanner.next();
                            System.out.println("------------------------------");

                            String fechaNacM = añoM + mesM + diaM;*/

                            Object[] rfcM = {nombreM, apellidoPM, apellidoMM, fechaNac2};
                            String response7 = (String) client.execute("Methods.rfc", rfcM);

                            Object[] datosM = {nombreM, apellidoPM, apellidoMM, curpM, fechaNac2, response7};

                            Boolean response8 = (Boolean) client.execute("Methods.modificarPersona", datosM);

                            System.out.println("RFC: " + response7);

                            if (response8 == true) {
                                System.out.println("Se modifico correctamente");
                                System.out.println("------------------------------");

                            } else {
                                System.out.println("Error");
                                System.out.println("------------------------------");

                            }

                        }
                        break;

                    case 4:
                        Object[] datosP = {};
                        String response3 = (String) client.execute("Methods.ListPersons",datosP);

                        System.out.println(response3);
                        System.out.println("------------------------------");

                        break;

                    case 5:
                        System.out.println("Ingrese el CURP");
                        String curpD = scanner.next();
                        System.out.println("-----------------------------");

                        Object [] verificarCurp3 = {curpD};
                        Boolean response13 = (Boolean) client.execute("Methods.verificarCurp", verificarCurp3);

                        if (response13 == false) {
                            System.out.println("No se encortro la CURP");
                            System.out.println("---------------------------");

                        }else {
                            Object[] eliminar = {curpD};
                            Boolean response5 = (Boolean) client.execute("Methods.eliminarPersona", eliminar);

                            if (response5 == true) {
                                System.out.println("Se elimino correctamente");
                                System.out.println("------------------------------");

                            } else {
                                System.out.println("Error");
                                System.out.println("------------------------------");

                            }
                        }
                        break;

                    default:
                        System.out.println("Saliendo...");
                }
            }else {
                System.out.println("Ingrese una opcion valida");
                System.out.println("-----------------------------");
            }

        } while (!option.equals("6"));

    }

    public static boolean isNumber (String number) {
        try {
            int num = Integer.parseInt(number);
            return true;

        }catch (NumberFormatException e) {
            return false;

        }
    }
}