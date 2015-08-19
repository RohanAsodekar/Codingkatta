import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Card {
	public final Integer BLUE = 1, GREEN = 2, RED = 3, YELLOW = 4;
	public static final Integer UNIVERSAL = 0;
	public final Integer SKIP  = 19, REVERSE = 20, DRAW2 = 21;
	public static final Integer DRAW4 = 22;
	public final static Integer WILDCARD= 23;
	Integer color, value;
	boolean isActionCard;
	Integer score;
	
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		this.color = color;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public boolean isActionCard() {
		return isActionCard;
	}
	public void setActionCard(boolean isActionCard) {
		this.isActionCard = isActionCard;
	}
	
	
	public Card(){
		
	}
	public Card(Integer color, Integer value, boolean isActionCard){
		this.color = color;
		this.value = value;
		this.isActionCard = isActionCard;
		if(value < 10)
		{
			this.score = value;
		}
		else if(value < 22)
		{
			this.score = 20;
		}
		else{
			this.score = 50;
		}
	}
	public static List<Card> createDeck(){
		List<Card> cards = new LinkedList<Card>();
		for (int i = 1; i <=4; i++) {
			Card card = new Card(i, 0, false);
			cards.add(card);
			for (int j = 1; j < 10; j++) {
				card = new Card(i,j,false);
				cards.add(card);
				cards.add(card);
			}
			for (int j = 19; j < 22; j++) {
				card = new Card(i, j, true);
				cards.add(card);
				cards.add(card);
			}
			
		}
		Card card = new Card(UNIVERSAL, DRAW4, true);
		for (int i = 0; i < 4; i++) {
			cards.add(card);
		}
		card = new Card(UNIVERSAL, WILDCARD, true);
		for (int i = 0; i < 4; i++) {
			cards.add(card);
		}
		return cards;
		
	}
	
}
