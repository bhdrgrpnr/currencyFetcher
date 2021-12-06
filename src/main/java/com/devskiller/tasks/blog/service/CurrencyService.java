package com.devskiller.tasks.blog.service;


import com.devskiller.tasks.blog.model.Rate;
import com.devskiller.tasks.blog.model.dto.RateDto;
import com.devskiller.tasks.blog.repository.RateRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.Logger;

@Service
public class CurrencyService {

	private static final Logger LOGGER = LogManager.getLogger(CurrencyService.class);

	@Value("${api.host.baseurl}")
	private String apiHost;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RateRepository rateRepository;

	public RateDto getRateFromBitFinex(){
		Float[] fields = restTemplate.getForObject(apiHost, Float[].class);

		try{
			return new RateDto(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9], new Date().getTime());
		}catch (Exception e){
			LOGGER.error( "error convertion response", e);
			return null;
		}
	}

	public void saveRate(RateDto rateDto) {
		rateRepository.save(new Rate(rateDto.getBid(), rateDto.getBidSize(), rateDto.getAsk(),rateDto.getAskSize(), rateDto.getDailyChange(), rateDto.getDailyChangeRelative(), rateDto.getLastPrice(), rateDto.getVolume(), rateDto.getHigh(), rateDto.getLow(), rateDto.getTimeStamp()));
	}

	public List<RateDto> findAllRates() {
		List<Rate> rates = rateRepository.findAll();
		List<RateDto> rateDtos = new ArrayList<>();
		for(Rate rate : rates){
			rateDtos.add(new RateDto(rate.getBid(), rate.getBidSize(), rate.getAsk(),rate.getAskSize(), rate.getDailyChange(), rate.getDailyChangeRelative(), rate.getLastPrice(), rate.getVolume(), rate.getHigh(), rate.getLow(), rate.getTimeStamp()));
		}

		return rateDtos;
	}

	public void deleteAllRates() {
		rateRepository.deleteAll();
	}

	public RateDto updateRate(long rateId, RateDto rateDto) {
		Optional<Rate> rateToBeUpdatedOpt = rateRepository.findById(rateId);

		if(!rateToBeUpdatedOpt.isPresent()){
			throw new InvalidParameterException("rate with id" + rateId + " not found");
		}

		Rate rateToBeUpdated = rateToBeUpdatedOpt.get();
		rateToBeUpdated.setAsk(rateDto.getAsk());
		rateToBeUpdated.setAskSize(rateDto.getAskSize());
		rateToBeUpdated.setBid(rateDto.getBid());
		rateToBeUpdated.setBidSize(rateDto.getBidSize());
		rateToBeUpdated.setDailyChange(rateDto.getDailyChange());
		rateToBeUpdated.setDailyChangeRelative(rateDto.getDailyChangeRelative());
		rateToBeUpdated.setHigh(rateDto.getHigh());
		rateToBeUpdated.setLow(rateDto.getLow());
		rateToBeUpdated.setLastPrice(rateDto.getLastPrice());
		rateToBeUpdated.setVolume(rateDto.getVolume());

		return toRateDto(rateRepository.save(rateToBeUpdated));
	}

	private RateDto toRateDto(Rate rate){
		return new RateDto(rate.getBid(), rate.getBidSize(), rate.getAsk(),rate.getAskSize(), rate.getDailyChange(), rate.getDailyChangeRelative(), rate.getLastPrice(), rate.getVolume(), rate.getHigh(), rate.getLow(), rate.getTimeStamp()) ;
	}





}
