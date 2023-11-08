package DSite.domain.conference.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record  ConferenceResponse(
        String title,
        String organization,
        LocalDate start_date,
        LocalDate end_date,
        String link
) {
}