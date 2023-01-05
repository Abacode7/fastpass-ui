package com.pluralsight.fastpassui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class FastPassController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private StreamBridge streamBridge;

    @RequestMapping(path="/customerdetails")
	public String getFastPassCustomerDetails(@RequestParam(defaultValue = "800") String fastpassid, Model m) {

        FastPassCustomer customer = webClientBuilder.build().get()
            .uri("http://localhost:8082/fastpass?fastpassid=" + fastpassid)
            .retrieve()
            .bodyToMono(FastPassCustomer.class)
            .block();

        streamBridge.send("generatetollcharge-out-0", new FastPassToll(fastpassid, "1001", 10.23f));
		
		System.out.println("fastpassid: " + fastpassid);
		m.addAttribute("customer", customer);
		return "console";

    }
    
}
