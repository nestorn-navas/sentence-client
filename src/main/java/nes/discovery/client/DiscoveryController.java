package nes.discovery.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DiscoveryController {
	
	@Autowired
	private LoadBalancerClient balancer;
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/sentence")
	public @ResponseBody String getSentence() {
		return subjectService.getSubject() + " " +
	           getWord("VERB") + " " + 
			   getWord("ARTICLE") + " " + 
	           getWord("ADJECTIVE") + " " + 
			   getWord("NOUN") + ".";
	}

	public String getWord(String service) {
		ServiceInstance instance = balancer.choose(service);
   		return (new RestTemplate()).getForObject(instance.getUri(),String.class);
	}
	

}
