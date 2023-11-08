package DSite.domain.Roadmap.exception;

import DSite.domain.Roadmap.exception.error.RoadmapError;
import DSite.global.exception.BusinessException;

public class NodeBoardNotFoundException extends BusinessException {
    public static final NodeBoardNotFoundException EXCEPTION = new NodeBoardNotFoundException();

    private NodeBoardNotFoundException(){super(RoadmapError.NODE_BOARD_NOTFOUND);
    }
}
