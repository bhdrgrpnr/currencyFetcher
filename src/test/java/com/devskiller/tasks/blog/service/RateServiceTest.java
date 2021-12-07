package com.devskiller.tasks.blog.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.Rate;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.model.dto.RateDto;
import com.devskiller.tasks.blog.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateServiceTest {

	@Autowired
	CurrencyService currencyService;

	@Test
	public void getAll() {
		currencyService.findAllRates();
	}

	@Test
	public void deleteAll() {

		currencyService.deleteAllRates();
		//Hopefully there will be less than 10 secs between execution of these methods so this test wont fail.
		List<Rate> allRates = currencyService.findAllRates();
		Assert.assertTrue(allRates.size() <=1);
	}

	@Test
	public void updateRate() throws InterruptedException {

		float ask = 1f;
		float askSize = 2f;
		float bidSize = 3f;
		float bid = 4f;
		float dailyChange = 5f;
		float dailyChangeRelative = 6f;
		float high = 7f;
		float low = 8f;
		float volume = 9f;
		float lastPrice = 10f;

		//we should wait for scheduler(cron job) to run at least once
		Thread.sleep(20000);

		List<Rate> allRates = currencyService.findAllRates();
		Rate rateToBeUpdated = allRates.get(0);
		rateToBeUpdated.setAsk(ask);
		rateToBeUpdated.setAskSize(askSize);
		rateToBeUpdated.setBidSize(bidSize);
		rateToBeUpdated.setBid(bid);
		rateToBeUpdated.setDailyChange(dailyChange);
		rateToBeUpdated.setDailyChangeRelative(dailyChangeRelative);
		rateToBeUpdated.setHigh(high);
		rateToBeUpdated.setLow(low);
		rateToBeUpdated.setVolume(volume);
		rateToBeUpdated.setLastPrice(lastPrice);

		currencyService.updateRate(rateToBeUpdated.getId(), rateToBeUpdated);

		Rate rateUpdated = currencyService.findById(rateToBeUpdated.getId());

		Assert.assertEquals(rateUpdated.getAsk(), ask, 0.0001);
		Assert.assertEquals(rateUpdated.getAskSize(), askSize, 0.0001);
		Assert.assertEquals(rateUpdated.getBid(), bid, 0.0001);
		Assert.assertEquals(rateUpdated.getBidSize(), bidSize, 0.0001);
		Assert.assertEquals(rateUpdated.getDailyChange(), dailyChange, 0.0001);
		Assert.assertEquals(rateUpdated.getDailyChangeRelative(), dailyChangeRelative, 0.0001);
		Assert.assertEquals(rateUpdated.getHigh(), high, 0.0001);
		Assert.assertEquals(rateUpdated.getLow(), low, 0.0001);
		Assert.assertEquals(rateUpdated.getVolume(), volume, 0.0001);
		Assert.assertEquals(rateUpdated.getLastPrice(), lastPrice, 0.0001);
	}


}
