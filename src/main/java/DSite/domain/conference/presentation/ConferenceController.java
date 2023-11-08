package DSite.domain.conference.presentation;

import DSite.domain.conference.dto.response.ConferenceResponse;
import DSite.domain.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConferenceController {
    private final ConferenceService conferenceService;

    @GetMapping("/conference")
    public List<ConferenceResponse> getFilteredConference(){
        return conferenceService.getFilteredConference();
    }
}