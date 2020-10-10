package player;

import java.util.Scanner;

public class Player {

	private String name;
	private int health = 100;
	private int attackPower = 100;
	private int lifePotion = 20;
	private boolean turn = false;

	public Player(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	private enum Option {
		ATTACK(0), DRINK(1);

		private final int number;

		private Option(int number) {
			this.number = number;
		}

		private int getValue() {
			return number;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getLifePotion() {
		return lifePotion;
	}

	public void setLifePotion(int lifePotion) {
		this.lifePotion = lifePotion;
	}

	private void attack(Player player) {
		this.attackPower *= Math.random();
		player.health = player.health - Math.round(this.attackPower);
		System.out.println(player.name + " lost " + attackPower + " health \nCurrent health of " + player.name + ": "
				+ player.health);

	}

	private void drinkLifePotion() {
		this.lifePotion *= Math.random();
		this.health = this.health + Math.round(lifePotion);
		if (this.health <= 100) {
			System.out.println("You gained " + this.lifePotion + " health \nCurrent health: " + this.health);
		} else {
			this.health = 100;
			System.out.println("You gained " + this.lifePotion + " health \nCurrent health: " + this.health);
		}

	}

	public void makeMove(Player player) {
		System.out.println("0 = ATTACk 1 = POTION \n");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		// scanner.close();
		if (this.health != 100) {
			if (option == Option.ATTACK.getValue()) {
				this.attack(player);

			} else
				this.drinkLifePotion();
		}

		else {
			System.out.println("Already Max Health, therefore attack automatically executed!");
			this.attack(player);
		}

	}

}
