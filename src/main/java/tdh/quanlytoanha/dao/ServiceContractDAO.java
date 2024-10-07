package tdh.quanlytoanha.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import tdh.quanlytoanha.dtos.ServiceContractDTO;
import tdh.quanlytoanha.util.HibernateUtil;

import java.text.SimpleDateFormat;

@Repository
public class ServiceContractDAO {

    public void createServiceContract(Integer companyId, Integer serviceId, ServiceContractDTO serviceContractDTO){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            NativeQuery query = session.createSQLQuery("INSERT INTO service_contract(company_id,service_id,start_date,description) VALUES(:companyId,:serviceId,:startDate,:description)");
            query.setParameter("companyId", companyId);
            query.setParameter("serviceId",serviceId);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
            String dateToSave = sdf1.format(sdf.parse(serviceContractDTO.getStartDate()));
            query.setParameter("startDate",dateToSave);
            query.setParameter("description",serviceContractDTO.getDescription());
            query.executeUpdate();

            session.getTransaction().commit();

        }catch(Exception e){
            if(session.getTransaction() != null) session.getTransaction().rollback();
        }
    }
}