package ideaboard.ideahub.repository;

import ideaboard.ideahub.domain.IdeaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

//<엔티티 클래스, pk 타입> + Entity 클래스와   - SpringDataJpa 없이 구현 후 리팩토링 할 예정
public interface IdeaBoardRepositoryDataJpa extends JpaRepository<IdeaBoard, Long> {

}
