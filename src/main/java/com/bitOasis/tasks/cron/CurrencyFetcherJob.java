package com.bitOasis.tasks.cron;

import com.bitOasis.tasks.service.CurrencyService;
import com.bitOasis.tasks.model.dto.RateDto;
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
