/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Carrito;
import mx.itson.benito.entidades.Ordenes;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author ricardogarcia
 */

public class OrdenesDAO {

    public static List<Ordenes> obtenerTodos(){
        List<Ordenes> ordenes = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Ordenes> criteriaQuery = session.getCriteriaBuilder().createQuery(Ordenes.class);
            criteriaQuery.from(Ordenes.class);
            ordenes = session.createQuery(criteriaQuery).getResultList();
        } catch(HibernateException ex) {
            System.err.println("Ocurrio un error:" + ex.getMessage());
        }
        return ordenes;
    }
    
    public static boolean agregar(Carrito c, String fecha, int total) {
         boolean resultado = false;
         try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Ordenes o = new Ordenes();
            o.setCarrito(c);
            o.setFecha(fecha);
            o.setTotal(total);
            
            
         } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex);

        }
        
        return resultado;
    }
    
    public static Carrito obtenerbyId(int id) {
        Carrito carrito = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            carrito = session.get(Carrito.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un errro: " + ex.getMessage());
        }
        return carrito;

    }
    
}
