package DSite.domain.Board.exception;

import DSite.domain.Board.exception.error.BoardError;
import DSite.global.exception.BusinessException;

public class BoardUrlStrangeException extends BusinessException {
    public static final BoardUrlStrangeException EXCEPTION = new BoardUrlStrangeException();

    private BoardUrlStrangeException(){super(BoardError.BOARD_URL_STRANGER);
    }
}