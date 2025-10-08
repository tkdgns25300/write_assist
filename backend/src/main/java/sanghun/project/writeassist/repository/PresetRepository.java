package sanghun.project.writeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanghun.project.writeassist.domain.Preset;

public interface PresetRepository extends JpaRepository<Preset, Integer> {
    // 기본 CRUD 메서드만 사용
}

