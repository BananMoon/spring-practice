package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;
//    new JPAQuery(em)

    public void save(Order order) {
        em.persist(order);
    }
    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }
    // 동적 쿼리!
    // 조건이 있다면 status, name으로 조건처리가 된 쿼리를 실행하고
    // 조건이 없다면, 모든 데이터를 다 가져오도록 쿼리를 실행해야한다.
    // JPQL: Order와 연관된 Member join!
    public List<Order> findAll(OrderSearch orderSearch) {
        /* 동적쿼리 없이 그냥 조건으로 검색하는 쿼리문.
        em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :status " +
                        " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
//                .setFirstResult(100)  // 페이징 : 100부터 시작해서
                .setMaxResults(1000)    // 1000개로 최대 결과 제한
                .getResultList();*/
        /* 동적쿼리 생성 방법 3가지
        1. JPQL : String으로 동적쿼리 생성하는 방법. 버그가 발생하기 쉬움.
        2. JPA Criteria : JPA표준스펙. Java코드로 JPQL을 생성하도록 만들어주는 클래스 모음. 유지보수성이 현저히 떨어져 실무에서 사용X
        3. QueryDSL : (권고) 동적 뿐만아니라 정적쿼리도 (복잡한 경우) 생성해줄 수 있음.
         */

        return em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :status " +
                        " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
//                .setFirstResult(100)  // 페이징 : 100부터 시작해서
                .setMaxResults(1000)    // 1000개로 최대 결과 제한
                .getResultList();
    }
}
