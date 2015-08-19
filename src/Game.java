import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Game {
	public final Integer BLUE = 1, GREEN = 2, RED = 3, YELLOW = 4;
	public static final Integer UNIVERSAL = 0;
	public final Integer SKIP = 19, REVERSE = 20, DRAW2 = 21;
	public static final Integer DRAW4 = 22;
	public final static Integer WILDCARD = 23;

	List<Player> players = new LinkedList<Player>();
	List<Card> deck = Card.createDeck();
	Stack<Card> discardPile = new Stack<Card>();

	public void addPlayer(Player player) {
		players.add(player);
	}

	public List<Player> getPlayerList() {
		return players;
	}

	public Stack<Card> getDiscardPile() {
		return discardPile;
	}

	public List<Card> shuffleDeck() {
		Collections.shuffle(deck);

		return deck;
	}

	public void distributeCards() {
		for (int i = 0; i < players.size(); i++) {
			List<Card> cards = new LinkedList();
			for (int j = 0; j < 7; j++) {
				cards.add(deck.get(0));
				deck.remove(0);
			}
			players.get(i).setCards(cards);

		}
	}

	public void createDiscardPile() {
		discardPile.push(deck.get(0));
		deck.remove(0);
	}

	public void startGame() {
		setupGame();
		int playerIndex = 0;
		boolean isClockwise = true;
		while (true) {
			if (discardPile.get(0).isActionCard) {
				if (discardPile.get(0).value == REVERSE) {
					isClockwise = reverseGame(isClockwise);
				}
				else if (discardPile.get(0).value == SKIP) {
					playerIndex = nextPlayer(playerIndex, isClockwise);
				}
				else if (discardPile.get(0).value == DRAW2) {
					drawXCards(playerIndex, 2);
					playerIndex = nextPlayer(playerIndex, isClockwise);

				}
				else if (discardPile.get(0).value == DRAW4) {
					drawXCards(playerIndex,4);
					playerIndex = nextPlayer(playerIndex, isClockwise);
				}
				else if (discardPile.get(0).value == WILDCARD) {
					addDummyCardToDiscardPile(getPrevPlayer(playerIndex,isClockwise));
					
				}

			}

			Card discardCard = players.get(playerIndex).match(discardPile.get(0));
			if (discardCard == null) {
				Card newCard = drawCard();
				boolean foundMatch = match(newCard);
				if (foundMatch == false) {
					players.get(playerIndex).addCard(newCard);
				}

			} else {
				discardPile.push(discardCard);
			}
			if (players.get(playerIndex).getCards().size() == 0)
			{
				players.get(playerIndex).points= players.get(playerIndex).points + getScore();
				break;
			}
			playerIndex = nextPlayer(playerIndex, isClockwise);

		}

	}

	private void addDummyCardToDiscardPile(int prevPlayer) {
		Card card = players.get(prevPlayer).getNormalColorCard();
		
	}

	private int getPrevPlayer(int playerIndex, boolean isClockwise) {
		if(isClockwise)
			return((playerIndex -1)%players.size());
		else
			return((playerIndex +1)%players.size());
		
	}

	protected boolean reverseGame(boolean isClockwise) {
		isClockwise = !(isClockwise);
		return isClockwise;
	}

	protected int nextPlayer(int playerIndex, boolean isClockwise) {
		playerIndex = getNextPlayerIndex(playerIndex, isClockwise);
		return playerIndex;
	}

	protected int getScore() {
		int score = 0;
		for (int i = 0; i < players.size(); i++) {
			List<Card> cards = players.get(i).getCards();
			for (int j = 0; j < cards.size(); j++) {
				System.out.println(cards.get(j).score);
				score = score+cards.get(j).score;
			}
		}
		return score;
	}

	protected void drawXCards(int playerIndex, int num) {
		for (int i = 0; i < num; i++) {
			drawCardFromDeckNaddToPlayerCards(playerIndex);
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Card> getDeck() {
		return deck;
	}

	protected void setupGame() {
		addPlayer(new Player("Rohan"));
		addPlayer(new Player("Sendhil"));
		addPlayer(new Player("Riya"));
		distributeCards();
		createDiscardPile();
	}

	protected void drawCardFromDeckNaddToPlayerCards(int playerIndex) {
		players.get(playerIndex).addCard(drawCard());
	}

	protected int getNextPlayerIndex(int playerIndex, boolean isClockwise) {
		if (isClockwise) {
			playerIndex = (playerIndex + 1) % players.size();
		} else {
			playerIndex = (playerIndex - 1) % players.size();
		}
		return playerIndex;
	}

	private boolean match(Card newCard) {
		Card card = discardPile.get(0);
		if (newCard.getColor() == card.getColor()) {
			discardPile.push(newCard);
			return true;
		}

		if (newCard.getValue() == card.getValue()) {
			discardPile.push(newCard);
			return true;

		}
		if (newCard.getColor() == 0) {
			discardPile.push(newCard);
			return true;
		}
		return false;
	}

	Card drawCard() {
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}

}
