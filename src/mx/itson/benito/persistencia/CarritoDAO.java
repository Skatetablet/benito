/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Carrito;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author ricardogarcia
 */
public class CarritoDAO {
    
    public static List<Carrito> obtenerTodos(){
        List<Carrito> carritos = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Carrito> criteriaQuery = session.getCriteriaBuilder().createQuery(Carrito.class);
            criteriaQuery.from(Carrito.class);
            carritos = session.createQuery(criteriaQuery).getResultList();
        } catch(HibernateException ex) {
            System.err.println("Ocurrio un error:" + ex.getMessage());
        }
        return carritos;
    }
    
    public static boolean agregar(Articulo a, String nombre, String clave, int precio, int cantidad, int total) {
         boolean resultado = false;
         try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Carrito c = new Carrito();
            c.setNombre(a.getNombre());
            c.setClave(a.getClave());
            c.setPrecio(Integer.parseInt(a.getPrecio()));
            c.setCantidad(cantidad);
            c.setTotal(total);
            
            
         } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex);

        }
        
        return resultado;
    }
    
    
}
