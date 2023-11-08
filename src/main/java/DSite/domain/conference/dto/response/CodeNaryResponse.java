package DSite.domain.conference.dto.response;
import java.time.LocalDateTime;

public record CodeNaryResponse(
        String title,
        String organization,
        LocalDateTime start_date,
        LocalDateTime end_date,
        String link
) {
}