package com.bitOasis.tasks.service;


import com.bitOasis.tasks.model.Rate;
import com.bitOasis.tasks.model.dto.RateDto;
import com.bitOasis.tasks.repository.RateRepository;
import java.security.InvalidParameterException;
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

	public List<Rate> findAllRates() {
		return rateRepository.findAll();
	}

	public Rate findById(long id) {
		return rateRepository.findById(id).get();
	}

	public void deleteAllRates() {
		rateRepository.deleteAll();
	}

	public void updateRate(long rateId, Rate rate) {
		Optional<Rate> rateToBeUpdatedOpt = rateRepository.findById(rateId);

		if(!rateToBeUpdatedOpt.isPresent()){
			throw new InvalidParameterException("rate with id" + rateId + " not found");
		}

		Rate rateToBeUpdated = rateToBeUpdatedOpt.get();
		rateToBeUpdated.setAsk(rate.getAsk());
		rateToBeUpdated.setAskSize(rate.getAskSize());
		rateToBeUpdated.setBid(rate.getBid());
		rateToBeUpdated.setBidSize(rate.getBidSize());
		rateToBeUpdated.setDailyChange(rate.getDailyChange());
		rateToBeUpdated.setDailyChangeRelative(rate.getDailyChangeRelative());
		rateToBeUpdated.setHigh(rate.getHigh());
		rateToBeUpdated.setLow(rate.getLow());
		rateToBeUpdated.setLastPrice(rate.getLastPrice());
		rateToBeUpdated.setVolume(rate.getVolume());

		rateRepository.save(rateToBeUpdated);
	}

	private RateDto toRateDto(Rate rate){
		return new RateDto(rate.getBid(), rate.getBidSize(), rate.getAsk(),rate.getAskSize(), rate.getDailyChange(), rate.getDailyChangeRelative(), rate.getLastPrice(), rate.getVolume(), rate.getHigh(), rate.getLow(), rate.getTimeStamp()) ;
	}





}
