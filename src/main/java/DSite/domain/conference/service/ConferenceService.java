package DSite.domain.conference.service;

import DSite.domain.conference.dto.response.CodeNaryResponse;
import DSite.domain.conference.dto.response.ConferenceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ConferenceService {
    private final CodeNaryFeignClient codeNaryFeignClient;

    public List<ConferenceResponse> getFilteredConference() {

        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
        Long nowYear= (long) now.getYear();
        Long nowMonth = (long) now.getMonthValue();

        List<CodeNaryResponse> responses = codeNaryFeignClient.getCalendar(nowYear,nowMonth);

        return IntStream.rangeClosed(0, responses.size()-1)
                .mapToObj(i -> ConferenceResponse.builder()
                        .title(responses.get(i).title())
                        .organization(responses.get(i).organization())
                        .start_date(responses.get(i).start_date().toLocalDate())
                        .end_date(responses.get(i).end_date().toLocalDate())
                        .link(responses.get(i).link())
                        .build())
                .collect(Collectors.toList());
    }
}