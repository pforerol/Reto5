package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatosModeloDB {

    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    DatosSucursalPuestoTrabajo datosDB;

    public ArrayList<DatosSucursalPuestoTrabajo> getPuestosTrabajo(int idSucursal) {
        System.out.println("Ingreso a getPuestosTrabajo");
        ArrayList listPuestosTrabajo = new ArrayList();
        String query = "SELECT `idPuestoTrabajo`, `nombrePuestoTrabajo`, `salario`, `FK_idSucursal` FROM puestotrabajo INNER JOIN sucursal ON (sucursal.idSucursal = puestotrabajo.FK_idSucursal) WHERE sucursal.idSucursal= ?;";
        try {
            connection = conexion.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, idSucursal);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosDB = new DatosSucursalPuestoTrabajo();
                int idPuestoTrabajo = rs.getInt("idPuestoTrabajo");
                String nombrePuestoTrabajo = rs.getString("nombrePuestoTrabajo");
                float salario = rs.getFloat("salario");
                datosDB.setIdPuestoTrabajo(idPuestoTrabajo);
                datosDB.setNombrePuestoTrabajo(nombrePuestoTrabajo);
                datosDB.setSalario(salario);
                listPuestosTrabajo.add(datosDB);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Puestos de trabajo" + listPuestosTrabajo);
        return listPuestosTrabajo;
    }

    public ArrayList<DatosSucursalPuestoTrabajo> getSucursales() {
        ArrayList listaSucursales = new ArrayList();
        String query = "SELECT `idSucursal`, `nombreSucursal` FROM `sucursal`;";
        try {
            connection = conexion.getConnection();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosDB = new DatosSucursalPuestoTrabajo();
                int idSucursalC = rs.getInt("idSucursal");
                String nombreSucursal = rs.getString("nombreSucursal");
                datosDB.setIdSucursal(idSucursalC);
                datosDB.setNombreSucursal(nombreSucursal);
                listaSucursales.add(datosDB);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Sucursales: " + listaSucursales);
        return listaSucursales;
    }
     
        public ArrayList getListaGerentes(){
        ArrayList mListaGerentes = new ArrayList();
        String queryGerente = "SELECT personasCargo, salario, nombrePuestoTrabajo, idEmp, CONCAT(nombreEmp, ' ', apellidos) AS nombre, personasCargo, salario FROM `gerente` INNER JOIN empleado ON(FK_idEmp = idEmp) INNER JOIN puestotrabajo ON(FK_idPuestoTrabajo = idPuestoTrabajo) WHERE nombrePuestoTrabajo = 'Gerente';";
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryGerente);
            while(rs.next()){
                String nombrePuestoTrabajo = rs.getString("nombrePuestoTrabajo");
                String nombreEmpleado = rs.getString("nombre");
                float salario = rs.getFloat("salario");
                int cantidadSubordinados = rs.getInt("personasCargo");
                System.out.println(nombrePuestoTrabajo + " - "+ nombreEmpleado + " - " + salario + " - " + cantidadSubordinados);
                
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println(mListaGerentes);
        return mListaGerentes;
            }
        public ArrayList getListaOperarios(){
        ArrayList mListaOperarios = new ArrayList();
        Operario operario = null;
        String queryOperario = "SELECT idOperario, nombrePuestoTrabajo, CONCAT(nombreEmp, ' ', apellidos) AS nombre, manejoMaquinariaPesada, salario FROM operario INNER JOIN empleado ON(FK_idEmp = idEmp) INNER JOIN puestotrabajo ON(empleado.FK_idPuestoTrabajo = puestotrabajo.idPuestoTrabajo) WHERE puestotrabajo.nombrePuestoTrabajo = 'Operario';";
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryOperario);
            while(rs.next()){
                String nombrePuestoTrabajo = rs.getString("nombrePuestoTrabajo");
                String nombreEmpleado = rs.getString("nombre");
                float salario = rs.getFloat("salario");
                boolean manejoMaquinaPesada = rs.getBoolean("manejoMaquinariaPesada");
                System.out.println(nombrePuestoTrabajo + " - "+ nombreEmpleado + " - " + salario + " - " + manejoMaquinaPesada);
                
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println(mListaOperarios);
        return mListaOperarios;
        }
 
public ArrayList getListaDomiciliarios(){
        ArrayList mListaDomiciliarios = new ArrayList();
        Domiciliario domiciliario = null;
        String queryDomiciliario = "SELECT idDomiciliario, nombrePuestoTrabajo, CONCAT(nombreEmp, ' ', apellidos) AS nombre, tipoTransporte, salario FROM domiciliario INNER JOIN empleado ON(FK_idEmp = idEmp) INNER JOIN puestotrabajo ON(empleado.FK_idPuestoTrabajo = puestotrabajo.idPuestoTrabajo) WHERE puestotrabajo.nombrePuestoTrabajo = 'Domiciliario';";
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryDomiciliario);
            while(rs.next()){
                String nombrePuestoTrabajo = rs.getString("nombrePuestoTrabajo");
                String nombreEmpleado = rs.getString("nombre");
                float salario = rs.getFloat("salario");
                String tipoTransporte = rs.getString("tipoTransporte");
                System.out.println(nombrePuestoTrabajo + " - "+ nombreEmpleado + " - " + salario + " - " + tipoTransporte);
                
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println(mListaDomiciliarios);
        return mListaDomiciliarios;
    }
}

