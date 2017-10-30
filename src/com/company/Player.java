package com.company;
import java.util.*;

/**
 * Created by Kailie on 10/17/2017.
 */

public class Player {

	private String username;
	private int balance;
	private boolean smallBlind;
	private boolean bigBlind;
	private boolean canPlay;

	Player(String username, int balance){
		this.username = username;
		this.balance = balance;
	}

	public void displayPlayerInfo() {
		//should displayPlayerInfo their name, balance, and two cards
		//might also be displaying their possible action when its their turn?
	}

//	public boolean isDealer() {
//
//	}

	public boolean isSmallBlind() {
		//we need a counter to know which player its up to to determine this, bigBlind and Dealer too
		//we also need a player ID to be able to do ^^^^^^
		return smallBlind;
	}

	public boolean isBigBlind() {
		return bigBlind;
	}

	public int getBalance() {
		return balance;
	}

	public boolean canPlay() {
		return getBalance() > 0;
	}
	
	@Override
	public String toString() {
		return "Player [username=" + username + ", balance=" + getBalance() + ", canPlay=" + canPlay() + "]";
	}
	
	public static void main(String [] args) {
		Player k = new Player("Kay", 2000);
		System.out.println(k.toString());
		Player l = new Player("Rob", -1000);
		System.out.println(l.toString());
		Player m = new Player("Vanessa", 0);
		System.out.println(m.toString());
		Player n = new Player("David", 251);
		System.out.println(n.toString());
	}
}
