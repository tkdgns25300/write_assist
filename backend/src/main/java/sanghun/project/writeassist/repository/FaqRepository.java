package sanghun.project.writeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanghun.project.writeassist.domain.Faq;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Integer> {

    /**
     * 활성화된 FAQ 목록을 정렬 순서대로 조회합니다.
     *
     * @return 활성화된 FAQ 목록 (정렬 순서 기준)
     */
    List<Faq> findByIsActiveTrueOrderBySortOrderAsc();
}

