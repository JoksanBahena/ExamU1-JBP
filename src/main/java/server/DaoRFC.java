package server;

import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoRFC {
    public boolean guardarDatos(String nombre, String apellidoP, String apellidoM, String curp, String fechaNac, String rfc){
        boolean result = false;
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement pstm = connection.prepareStatement("insert into datos (nombre, apellido1, apellido2, curp, fecha_nac, rfc) values (?, ?, ?, ?, ?, ?);");
        ){
            pstm.setString(1, nombre);
            pstm.setString(2, apellidoP);
            pstm.setString(3, apellidoM);
            pstm.setString(4, curp);
            pstm.setString(5, fechaNac);
            pstm.setString(6, rfc);
            result = pstm.executeUpdate()==1;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<BeanRFC> listPersons(){
        List<BeanRFC> listPersons = new ArrayList<>();
        try{
            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from datos;");
            BeanRFC person = null;
            while(rs.next()){
                person = new BeanRFC();
                person.setNombre(rs.getString("nombre"));
                person.setApellidoP(rs.getString("apellido1"));
                person.setApellidoM(rs.getString("apellido2"));
                person.setCurp(rs.getString("curp"));
                person.setFechaNac(rs.getString("fecha_nac"));
                person.setRfc(rs.getString("rfc"));
                listPersons.add(person);
            }
            rs.close();
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();

        }

        return listPersons;
    }

    public BeanRFC consulta (String curp) {
        BeanRFC person = new BeanRFC();

        try (Connection connection = MySQLConnection.getConnection();
                PreparedStatement pstm = connection.prepareStatement("select * from examen.datos where curp = ?;");
                ){

            pstm.setString(1,curp);
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                person.setNombre(rs.getString("nombre"));
                person.setApellidoP(rs.getString("apellido1"));
                person.setApellidoM(rs.getString("apellido2"));
                person.setCurp(rs.getString("curp"));
                person.setFechaNac(rs.getString("fecha_nac"));
                person.setRfc(rs.getString("rfc"));
            }

        }catch (Exception e) {
            e.printStackTrace();

        }

        return person;
    }

    public boolean deletePerson(String curp){
        boolean result = false;
        try(
                Connection con = MySQLConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement("delete from datos where curp = ?;");
        ){
            pstm.setString(1, curp);
            result = pstm.executeUpdate()==1;

        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }

    public boolean updatePerson(BeanRFC person){
        boolean result = false;
        try(
                Connection con = MySQLConnection.getConnection();
                PreparedStatement pstm = con.prepareStatement("update examen.datos set nombre=?, apellido1=?, apellido2=?, curp=?, fecha_nac=?, rfc=? where curp=?;");
        ){
            pstm.setString(1, person.getNombre());
            pstm.setString(2, person.getApellidoP());
            pstm.setString(3, person.getApellidoM());
            pstm.setString(4, person.getCurp());
            pstm.setString(5, person.getFechaNac());
            pstm.setString(6, person.getRfc());
            pstm.setString(7, person.getCurp());
            result = pstm.executeUpdate()==1;

        }catch (Exception e){
            e.printStackTrace();

        }

        return result;


    }
}
