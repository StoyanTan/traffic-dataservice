package com.kaggle.trafficdata.trafficdataservice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.kaggle.trafficdata.trafficdataservice.model.Filter;
import com.kaggle.trafficdata.trafficdataservice.model.ParameterValueObject;
import com.kaggle.trafficdata.trafficdataservice.model.TrafficIncident;

@Repository
public class TrafficDao implements ITrafficDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ParameterValueObject> getClustering(Filter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<TrafficIncident> traffic = query.from(TrafficIncident.class);
        query.select(
                cb.tuple(traffic.get(filter.getFilterCriteria()), cb.count(traffic.get(filter.getFilterCriteria()))))
                .groupBy(traffic.get(filter.getFilterCriteria()));
        List<Tuple> resultList = em.createQuery(query).getResultList();

        List<ParameterValueObject> objectResult = new ArrayList<>();
        for (Tuple tuple : resultList) {
            if (tuple.get(0) != null) {
                ParameterValueObject object = new ParameterValueObject();
                object.setParameter(tuple.get(0).toString());
                object.setCount(tuple.get(1).toString());
                objectResult.add(object);
            }
        }
        return objectResult;
    }

    @Override
    public List<TrafficIncident> getTraffic(Filter filter, int offset, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrafficIncident> cq = cb.createQuery(TrafficIncident.class);
        Root<TrafficIncident> traffic = cq.from(TrafficIncident.class);

        if (filter == null || filter.getFilterCriteria() == null) {
            cq.select(traffic);
        } else {
            cq.select(traffic).where(cb.equal(traffic.get(filter.getFilterCriteria()), filter.getValue()));
        }

        return em.createQuery(cq).setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }

    @Override
    public TrafficIncident getIncidentById(Filter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrafficIncident> cq = cb.createQuery(TrafficIncident.class);
        Root<TrafficIncident> traffic = cq.from(TrafficIncident.class);

        if (filter == null || filter.getFilterCriteria() == null) {
            return null;
        } else {
            cq.select(traffic).where(cb.equal(traffic.get(filter.getFilterCriteria()), filter.getValue()));
        }
        return em.createQuery(cq).getSingleResult();

    }

    @Override
    public Long getCount(Filter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrafficIncident> cq = cb.createQuery(TrafficIncident.class);
        Root<TrafficIncident> traffic = cq.from(TrafficIncident.class);

        if (filter == null || filter.getFilterCriteria() == null) {
            return null;
        } else {
            cq.select(traffic)
                    .where(cb.equal(traffic.get(filter.getFilterCriteria()), filter.getValue()));

            return (long) em.createQuery(cq).getResultList().size();
        }
    }
}
