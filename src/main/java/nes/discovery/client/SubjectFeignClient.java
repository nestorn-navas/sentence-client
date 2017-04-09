package nes.discovery.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("SUBJECT")
public interface SubjectFeignClient {
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public String getSubject();
}
