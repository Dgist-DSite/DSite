package DSite.domain.conference.service;

import DSite.domain.conference.dto.response.CodeNaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "externalAPI", url = "https://api.codenary.co.kr")
public interface CodeNaryFeignClient {

    @GetMapping("/calendar")
    List<CodeNaryResponse> getCalendar(@RequestParam(value = "year") Long year, @RequestParam(value = "month") Long month);
}