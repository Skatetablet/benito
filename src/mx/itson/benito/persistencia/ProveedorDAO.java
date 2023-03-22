/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author ricardogarcia
 */
public class ProveedorDAO {
    
    public static List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);
            proveedores = session.createQuery(criteriaQuery).getResultList();
            
        } catch(HibernateException ex) {
            System.err.println("Ocurrio un error:" + ex.getMessage());
        }
        return proveedores;
    }
    
    public static boolean guardar(String nombre, String clave, String email, String telefono, String contacto) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setClave(clave);
            p.setEmail(email);
            p.setTelefono(telefono);
            p.setContacto(contacto);
            
            session.save(p);
            session.getTransaction().commit();
            resultado = p.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex);

        }
        
        return resultado;
    }
    
    public static Proveedor obtenerById(int id) {
        Proveedor proveedor = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            proveedor = session.get(Proveedor.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return proveedor;
    }
    
    public static boolean editar(int id, String nombre, String clave, String email, String telefono, String contacto) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Proveedor p = obtenerById(id);
            if (p != null) {
                p.setNombre(nombre);
                p.setClave(clave);
                p.setEmail(email);
                p.setTelefono(telefono);
                p.setContacto(contacto);
                session.saveOrUpdate(p);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
