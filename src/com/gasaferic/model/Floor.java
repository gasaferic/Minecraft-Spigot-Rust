package com.gasaferic.model;

public enum Floor {

	SECOND(350, 12, "secondoPiano.schematic"), THIRD(400, 15, "terzoPiano.schematic"),
	FOURTH(450, 18, "quartoPiano.schematic"), FIFTH(500, 21, "quintoPiano.schematic"),
	BUNKER(300, 9, "bunker.schematic");

	private int logAmount;
	private int ironAmount;
	private String schematic;

	Floor(int logAmount, int ironAmount, String schematic) {
		this.logAmount = logAmount;
		this.ironAmount = ironAmount;
		this.schematic = schematic;
	}

	public int getLogAmount() {
		return logAmount;
	}

	public int getIronAmount() {
		return ironAmount;
	}

	public String getSchematic() {
		return schematic;
	}

}