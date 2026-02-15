package com.crickPulse.enums;

public enum SubscriptionType {

	MONTHLY(0), YEARLY(1), QUARTERLY(2), FIXED(3);

	private final int subscriptionType;

	SubscriptionType(int subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public int getValue() {
		return this.subscriptionType;
	}

}