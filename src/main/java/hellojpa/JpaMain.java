package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        // hello 는 persistence.xml에 있는 persistence-unit name
        // EntityManagerFactory 하나만 만든다
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // db connection 얻어 종료되는 일괄적인 작업 transaction 마다 만들어야 한다
        // 엔티티 메니저는 스레드 공유 하면 안된다
        EntityManager em = emf.createEntityManager();
        // jpa에서 데이터를 변경하는 모든 작업은 트랜잭션 내에서 수행해야 한다
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try{
            // 트랜잭션 완료
            transaction.commit();
        } catch (Exception e){
            // 에러의 경우 트랜잭션 롤백
            transaction.rollback();
        } finally {
            // data connection을 물고 있으므로 꼭 닫기
            em.close();
        }
        // 어플리케이션 종료될때 엔티티메니저팩토리 종료
        emf.close();
    }
}
