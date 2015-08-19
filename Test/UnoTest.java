import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class UnoTest {
	public final Integer BLUE = 1, GREEN = 2, RED = 3, YELLOW = 4;
	public static final Integer UNIVERSAL = 0;
	public final Integer SKIP = 19, REVERSE = 20, DRAW2 = 21;
	public static final Integer DRAW4 = 22;
	public final static Integer WILDCARD = 23;
	
	
	@Test
	public void shouldCreateCard() {
		Card card = new Card();
	}
	@Test
	public void shouldCreateDeck() {
		List<Card> cards = Card.createDeck();
		Assert.assertEquals(108, cards.size());
		
	}
	@Test
	public void shouldCreatePlayer() {
		Player player = new Player();
	}
	@Test
	public void shouldshuffleDeck() {
		Game game = new Game();
		List<Card> cards =  game.shuffleDeck();
		Assert.assertEquals(108, cards.size());
	}
	@Test
	public void shouldSetupGame() {
		
		Game game = new Game();
	}
	
	@Test
	public void shouldCreateGame(){
		Game game = new Game();
	}
	@Test
	public void shouldAddPlayers(){
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		Assert.assertEquals(3, game.getPlayerList().size());
	}
	
	@Test
	public void shouldDistributeCards(){
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		game.distributeCards();
		List<Player> playerList = game.getPlayerList();
		for (int i = 0; i < playerList.size(); i++) {
			Assert.assertEquals(7,playerList.get(i).getCards().size());
		}
	}
	@Test
	public void shouldDiscardACard(){
		Game game = new Game();
		game.createDiscardPile();
		Stack<Card> stack = game.getDiscardPile();
		Assert.assertEquals(1, stack.size());
	}
	
	@Test
	public void shouldMatchCard() {
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		List<Card> cards = new LinkedList<>();
		cards.add(new Card(RED,2,false));
		cards.add(new Card(GREEN,3,false));
		cards.add(new Card(YELLOW,REVERSE,true));
		cards.add(new Card(UNIVERSAL,WILDCARD,true));
		game.getPlayerList().get(0).setCards(cards);
		Card card = new Card(GREEN,4,false);
		Card matchCard = game.getPlayerList().get(0).match(card);
		Assert.assertEquals(GREEN, matchCard.getColor());
		Assert.assertEquals(cards.get(1).getValue(), matchCard.getValue());
		
	}
	@Test
	public void shouldDrawCard() {
		Game game = new Game();
		game.setupGame();
		int sizeBefore = game.getDeck().size();
		Card card = game.drawCard();
		int sizeAfter = game.getDeck().size();
		Assert.assertEquals(1,sizeBefore - sizeAfter);
		
	}
	@Test
	public void shouldSkipTurn() {
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		int index = game.nextPlayer(0, true);
		Assert.assertEquals(1,index);
	}
	@Test
	public void shouldDraw2CardsNskip() {
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		game.distributeCards();
		int numBefore = game.getPlayers().get(0).getCards().size();
		game.drawXCards(0, 2);
		int numAfter = game.getPlayers().get(0).getCards().size();
		Assert.assertEquals(2, numAfter - numBefore);
	}
	@Test
	public void shouldDraw4CardsNskip() {
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		game.distributeCards();
		int numBefore = game.getPlayers().get(0).getCards().size();
		game.drawXCards(0, 4);
		int numAfter = game.getPlayers().get(0).getCards().size();
		Assert.assertEquals(4, numAfter - numBefore);
	}
	@Test
	public void shouldReverseDirection() {
		Game game = new Game();
		Assert.assertEquals(false, game.reverseGame(true));
	}
	
	@Test
	public void shouldSelectColorOnWildCard() {
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(GREEN, DRAW2, true));
		game.getPlayers().get(0).setCards(cards);
		Card card = game.getPlayers().get(0).getNormalColorCard();
		Assert.assertEquals(GREEN, card.getColor());
	}
	@Test
	public void shouldCountPoints() {
		Game game = new Game();
		game.addPlayer(new Player("Rohan"));
		game.addPlayer(new Player("Sendhil"));
		game.addPlayer(new Player("Riya"));
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(GREEN, DRAW2, true));
		game.getPlayers().get(0).setCards(cards);
		List<Card> cards1 = new LinkedList<Card>();
		cards1.add(new Card(UNIVERSAL,DRAW4,true));
		game.getPlayers().get(1).setCards(cards1);
		int score = game.getScore();
		Assert.assertEquals(70, score);
		
	}
	@Test
	public void shouldDecideWinner() {

	}

}
