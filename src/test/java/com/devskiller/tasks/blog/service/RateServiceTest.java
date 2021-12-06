package com.devskiller.tasks.blog.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import com.devskiller.tasks.blog.model.Post;
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
		List<RateDto> allRates = currencyService.findAllRates();
		Assert.assertTrue(allRates.size() <=1);
	}

	@Test
	public void deleteAll() {
		List<RateDto> allRates = currencyService.findAllRates();
		RateDto rateDtoToBeUpdated = allRates.get(0);
		rateDtoToBeUpdated.setAsk(1f);
		rateDtoToBeUpdated.setAskSize(2f);
		rateDtoToBeUpdated.setBidSize(3f);
		rateDtoToBeUpdated.setBid(4f);
		rateDtoToBeUpdated.setDailyChange(5f);
		rateDtoToBeUpdated.setDailyChangeRelative(6f);
		rateDtoToBeUpdated.setHigh(7f);
		rateDtoToBeUpdated.setLow(8f);
		rateDtoToBeUpdated.setVolume(9f);


		currencyService.updateRate(rateDtoToBeUpdated.ge , rateDtoToBeUpdated);


	}


}
