/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Estado;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author ricardogarcia
 */
public class EstadoDAO {
    
    public static List<Estado> obtenerTodos() {
        List<Estado> estados = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Estado> criteriaQuery = session.getCriteriaBuilder().createQuery(Estado.class);
            criteriaQuery.from(Estado.class);
            estados = session.createQuery(criteriaQuery).getResultList();
        } catch(HibernateException ex) {
            System.err.println("Ocurrio un error:" + ex.getMessage());
        }
        return estados;
        
    }
    
    public static Estado obtenerById(int id) {
        Estado estado = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            estado = session.get(Estado.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return estado;
    }
}
