package com.bitOasis.tasks.rest;

import com.bitOasis.tasks.service.CurrencyService;
import com.bitOasis.tasks.model.Rate;
import com.bitOasis.tasks.model.dto.RateDto;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/rates")
public class RateController {

	private final CurrencyService currencyService;

	public RateController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<RateDto>> getAllRates() {

		List<Rate> allRates = currencyService.findAllRates();

		List<RateDto> allRateDtos = new ArrayList<>();
		for(Rate rate : allRates){
			allRateDtos.add(new RateDto(rate.getBid(), rate.getBidSize(), rate.getAsk(),rate.getAskSize(), rate.getDailyChange(), rate.getDailyChangeRelative(), rate.getLastPrice(), rate.getVolume(), rate.getHigh(), rate.getLow(), rate.getTimeStamp()));
		}
		return ResponseEntity.ok(allRateDtos);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllRates() {
		 currencyService.deleteAllRates();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateRates(@PathVariable("id") long id, @RequestBody RateDto rateDto) {
		try{
			currencyService.updateRate(id, fromDto(rateDto));
			return ResponseEntity.ok(Boolean.TRUE);
		}catch (InvalidParameterException e){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	private Rate fromDto(RateDto rateDto){
		return new Rate(rateDto.getBid(), rateDto.getBidSize(), rateDto.getAsk(),rateDto.getAskSize(), rateDto.getDailyChange(), rateDto.getDailyChangeRelative(), rateDto.getLastPrice(), rateDto.getVolume(), rateDto.getHigh(), rateDto.getLow(), rateDto.getTimeStamp());
	}

}
