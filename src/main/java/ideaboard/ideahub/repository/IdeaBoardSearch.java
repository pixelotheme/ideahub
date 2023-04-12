package ideaboard.ideahub.repository;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IdeaBoardSearch {


    private String title;
//  잠금상태로 검색

    @Builder
    public IdeaBoardSearch(String title) {
        this.title = title;
    }
}
