package nes.discovery.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectFeignClient client;
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackSubject")
	public String getSubject() {
		return this.client.getSubject();
	}
	
	public String getFallbackSubject(){
		return "Subject-FallBack";
	}

}
