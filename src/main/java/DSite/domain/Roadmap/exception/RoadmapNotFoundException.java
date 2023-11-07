package DSite.domain.Roadmap.exception;

import DSite.domain.Roadmap.exception.error.RoadmapError;
import DSite.global.exception.BusinessException;

public class RoadmapNotFoundException extends BusinessException {
    public static final RoadmapNotFoundException EXCEPTION = new RoadmapNotFoundException();

    private RoadmapNotFoundException(){super(RoadmapError.ROADMAP_ERROR_NOTFOUND);
    }
}
