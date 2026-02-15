package com.crickPulse.enums;

public enum Format {
	Odi(1), T20(2), Test(3), ODI(1);

	private final int role;

	Format(int role) {
		this.role = role;
	}

	public int getValue() {
		return this.role;
	}

}