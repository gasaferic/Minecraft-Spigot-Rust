package com.gasaferic.main;

//https://www.spigotmc.org/threads/199285/#post-2073868

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class ProbabilityUtilities {
	
	private class Chance {
		
		private int upperLimit;
		private int lowerLimit;
		private ItemStack item;

		public Chance(ItemStack item, int lowerLimit, int upperLimit) {
			this.item = item;
			this.upperLimit = upperLimit;
			this.lowerLimit = lowerLimit;
		}

		public int getUpperLimit() {
			return this.upperLimit;
		}

		public int getLowerLimit() {
			return this.lowerLimit;
		}

		public ItemStack getItem() {
			return this.item;
		}
		
	}

	private List<Chance> chances;
	private int sum;
	private Random random;

	public ProbabilityUtilities() {
		this.random = new Random();
		this.chances = new ArrayList<>();
		this.sum = 0;
	}

	public ProbabilityUtilities(long seed) {
		this.random = new Random(seed);
		this.chances = new ArrayList<>();
		this.sum = 0;
	}

	public void addChance(ItemStack item, int chance) {
		if (!this.chances.contains(((Object) item))) {
			this.chances.add(new Chance(item, this.sum, this.sum + chance));
			this.sum = this.sum + chance;
		}
	}

	public ItemStack getRandomElement() {
		int index = this.random.nextInt(this.sum);
		for (Chance chance : this.chances) {
			if (chance.getLowerLimit() <= index && chance.getUpperLimit() > index) {
				// Bukkit.getConsoleSender().sendMessage("§9" + this.chances.size());
				// System.out.println(chance.lowerLimit);
				return chance.getItem();
			}
		}
		return null; // this should never happen, because the random can't be out of the limits
	}

	public int getOptions() { // might be needed sometimes
		return this.sum;
	}

	public int getChoices() { // might be needed sometimes
		return this.chances.size();
	}
}