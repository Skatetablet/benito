/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author ricardogarcia
 */
public class ArticuloDAO {
    public static List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);
            articulos = session.createQuery(criteriaQuery).getResultList();
        } catch(HibernateException ex) {
            System.err.println("Ocurrio un error:" + ex.getMessage());
        }
        return articulos;
        
    }
    
    public static boolean guardar(String nombre, String clave, String precio, Proveedor p) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Articulo a = new Articulo();
            a.setNombre(nombre);
            a.setClave(clave);
            a.setPrecio(precio);
            a.setProveedor(p);
            
            session.save(a);
            session.getTransaction().commit();
            resultado = a.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex);

        }
        
        return resultado;
        
    }
    
    public static Articulo obtenerById(int id) {
        Articulo articulo = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulo = session.get(Articulo.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return articulo;
    }
    
    public static boolean editar(int id, String nombre, String clave, String precio, Proveedor p) {
        boolean resultado = false; 
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Articulo a = obtenerById(id);
            if (a != null) {
                a.setNombre(nombre);
                a.setClave(clave);
                a.setPrecio(precio);
                a.setProveedor(p);

                session.saveOrUpdate(a);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
