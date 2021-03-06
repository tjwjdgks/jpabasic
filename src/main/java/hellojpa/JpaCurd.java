package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaCurd {
    public static void main(String []args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            /*
            // 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("hello");

            em.persist(member); // 저장된다
             */

            // member 수정
            // 엔티티 클래스, Pk // member 찾기
            /*
            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getId() +" " + findMember.getName());
            findMember.setName("test"); // entity 객체 변경, 수정 쿼리 나간다
             */

            // member 삭제
            /*
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
             */

            // 특정 조건을 사용하고 싶을 때 jpql 을 사용한다
            // jpql은 table 대상으로 짜는 것이 아닌 엔티티 객체를 대상으로 쿼리한다 // db 방언에 맞춰서 쿼리를 만든다
            List<Member> resultList = em.createQuery("select m from Member as m ", Member.class).getResultList();
            transaction.commit();

        } catch (Exception e){
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}

