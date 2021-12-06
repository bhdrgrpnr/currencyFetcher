package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.model.dto.RateDto;
import com.devskiller.tasks.blog.service.CommentService;
import com.devskiller.tasks.blog.service.CurrencyService;
import java.security.InvalidParameterException;
import java.util.Currency;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<RateDto> getAllRates() {
		return currencyService.findAllRates();
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllRates() {
		 currencyService.deleteAllRates();
	}

	@PutMapping("/{id}")
	public ResponseEntity<RateDto> updateRates(@PathVariable("id") long id, @RequestBody RateDto rateDto) {
		try{
			return ResponseEntity.ok(currencyService.updateRate(id, rateDto));
		}catch (InvalidParameterException e){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
