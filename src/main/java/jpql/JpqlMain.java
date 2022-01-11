package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);
            /*
            //엔티티 프로젝션을 하면 select 된 대상이 모두 영속성 context에서 관리 된다
            List<Member> select_m_from_member_m_ = em.createQuery("select m from Member m ", Member.class).getResultList();
             */
            /*
            // 페이징
            List<Member> pageList = em.createQuery("select m from MemberV2 m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
             */
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
