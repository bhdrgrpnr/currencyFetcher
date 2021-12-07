package com.bitOasis.tasks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rate {

	@Id
	@GeneratedValue
	private Long id;

	float bid;
	float bidSize;
	float ask;
	float askSize;
	float dailyChange;
	float dailyChangeRelative;
	float lastPrice;
	float volume;
	float high;
	float low;
	long timeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public float getBidSize() {
		return bidSize;
	}

	public void setBidSize(float bidSize) {
		this.bidSize = bidSize;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getAskSize() {
		return askSize;
	}

	public void setAskSize(float askSize) {
		this.askSize = askSize;
	}

	public float getDailyChange() {
		return dailyChange;
	}

	public void setDailyChange(float dailyChange) {
		this.dailyChange = dailyChange;
	}

	public float getDailyChangeRelative() {
		return dailyChangeRelative;
	}

	public void setDailyChangeRelative(float dailyChangeRelative) {
		this.dailyChangeRelative = dailyChangeRelative;
	}

	public float getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Rate(float bid, float bidSize, float ask, float askSize, float dailyChange, float dailyChangeRelative, float lastPrice, float volume, float high, float low, long timeStamp) {

		this.bid = bid;
		this.bidSize = bidSize;
		this.ask = ask;
		this.askSize = askSize;
		this.dailyChange = dailyChange;
		this.dailyChangeRelative = dailyChangeRelative;
		this.lastPrice = lastPrice;
		this.volume = volume;
		this.high = high;
		this.low = low;
		this.timeStamp = timeStamp;
	}

	public Rate() {
	}
}
