package com.crickPulse.enums;

public enum Status {
	Upcoming(0), Live(1), Finished(2), Abandoned(2);

	private final int role;

	Status(int role) {
		this.role = role;
	}

	public int getValue() {
		return this.role;
	}

}