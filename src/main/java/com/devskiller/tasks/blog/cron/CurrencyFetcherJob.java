package com.devskiller.tasks.blog.cron;

import com.devskiller.tasks.blog.model.dto.RateDto;
import com.devskiller.tasks.blog.service.CurrencyService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyFetcherJob {


	@Autowired
	CurrencyService currencyService;

	@Scheduled(cron = "*/10 * * * * *")
	public void fetch(){
		RateDto rate = currencyService.getRateFromBitFinex();
		currencyService.saveRate(rate);
	}


}
