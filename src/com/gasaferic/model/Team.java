package com.gasaferic.model;

import java.util.ArrayList;

public class Team {

	private final Survivor TEAM_OWNER;
	private final String TEAM_NAME;
	private int teamSize;
	private ArrayList<Survivor> teamMembers = new ArrayList<Survivor>();
	private ArrayList<Survivor> teamersToRemove = new ArrayList<Survivor>();

	public Team(Survivor teamOwner, String teamName, int teamSize, ArrayList<Survivor> teamMembers) {
		this.TEAM_OWNER = teamOwner;
		this.TEAM_NAME = teamName;
		this.teamSize = teamSize + 1;
		this.teamMembers = teamMembers;
	}

	public Survivor getTeamOwner() {
		return this.TEAM_OWNER;
	}

	public String getTeamName() {
		return this.TEAM_NAME;
	}

	public int getTeamSize() {
		return this.teamSize;
	}

	public ArrayList<Survivor> getTeamMembers() {
		return this.teamMembers;
	}

	public ArrayList<Survivor> getTeamersToRemove() {
		return this.teamersToRemove;
	}

	public void setTeamSize(int newSize) {
		this.teamSize = newSize;
	}

	public void addTeamMember(Survivor survivor) {
		if (!this.teamMembers.contains(survivor)) {
			this.teamMembers.add(survivor);
		}
		if (this.teamersToRemove.contains(survivor)) {
			this.teamersToRemove.remove(survivor);
		}
	}

	public void removeTeamMember(Survivor survivor) {
		if (this.teamMembers.contains(survivor)) {
			this.teamMembers.remove(survivor);
			this.teamersToRemove.add(survivor);
		}
	}
	
	public void teamerToRemove(Survivor survivor) {
		this.teamersToRemove.add(survivor);
	}

	public boolean isInTeam(Survivor survivor) {
		if (teamMembers.contains(survivor)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean sameTeam(Survivor survivor, Survivor secondSurvivor) {
		if (teamMembers.contains(survivor) && teamMembers.contains(secondSurvivor)) {
			return true;
		} else {
			return false;
		}
	}

}