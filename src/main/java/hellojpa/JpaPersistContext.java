package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistContext {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("hello");
            // 영속 // 영속성 컨텍스트 관리
            // 저장
            em.persist(member);
            /*
            // 회원 엔티티를 영속성 컨텍스트에서 분리
            em.detach(member);
             */
            /*
            // 겍체를 삭제한 상태(삭제)
            em.refresh(member);
             */
            // 위에서 1차 캐시로 저장되었기 때문에 sql 문 안날라간다
            Member member2 = em.find(Member.class, 100L);
            // 영속 엔티티의 동일성 보장
            System.out.println(member == member2);

            // 커밋 시점에 쿼리가 날라간다
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
