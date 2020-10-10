package battlefield;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import player.Player;

public class Battlefield {

	public static int numberOfPlayers;

	private static List<Player> listOfPlayers;
	private static List<Player> toRemove;
	static int starter;
	static boolean player1Turn = false;
	static boolean playersCreated = false;
	static boolean gameOver = false;
	public static Scanner scanner;

	private static Player player;

	public static Player createPlayer() {
		System.out.println("Who are you? \n");
		scanner = new Scanner(System.in);
		String name = scanner.next();
		player = new Player(name);
		System.out.println("Player with the name " + player.getName() + " has been created!");
		// scanner.close();
		return player;

	}

	public static void play() {

		if (playersCreated == false) {
			scanner = new Scanner(System.in);
			numberOfPlayers = scanner.nextInt();
			listOfPlayers = new ArrayList<Player>();
			toRemove = new ArrayList<Player>();
			for (int i = 0; i < numberOfPlayers; i++) {
				listOfPlayers.add(createPlayer());
			}
			playersCreated = true;
			starter = chooseBeginnerRandomly();
			listOfPlayers.add(0, listOfPlayers.get(starter));
			listOfPlayers = listOfPlayers.stream().distinct().collect(Collectors.toList());
			System.out.println(listOfPlayers);
		}

		if (listOfPlayers.size() == 1) {
			System.out.println("Game Over " + listOfPlayers.get(0) + " wins!");
		} else {
			for (Player player1 : listOfPlayers) {
				System.out.println("Player " + player1.getName() + " makes the move!");
				System.out.println("What to do to whom?");
				String name = scanner.next();
				for (Player player2 : listOfPlayers) {
					System.out.println(player2.getName() + " " + name);
					if (player2.getName().matches(name)) {
						System.out.println("Make move: ");
						player1.makeMove(player2);

						if (player2.getHealth() <= 0) {
							System.out.println("Player " + player2.getName() + " removed!");
							toRemove.add(player2);

						}
					}
				}
				listOfPlayers.removeAll(toRemove);
			}
			System.out.println("Enter");
			play();
		}

	}

	public static int chooseBeginnerRandomly() {
		Random random = new Random();
		starter = random.nextInt(numberOfPlayers - 1) + 1;
		System.out.println("Player " + listOfPlayers.get(starter).getName() + " starts!");
		return starter;
	}

}
