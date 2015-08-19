import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Player {
	String Name;
	int points;
	List<Card> cards = new LinkedList<Card>();
	public Player(){
		
	}
	public Player(String Name)
	{
		this.Name = Name;
		points = 0;
	}
	void setCards(List<Card> cards){
		this.cards = cards;
	}
	public String getName() {
		return Name;
	}
	public int getPoints() {
		return points;
	}
	public List<Card> getCards() {
		return cards;
	}
	public Card match(Card card) {
		for (int i = 0; i < cards.size(); i++) {
			Card card2 = cards.get(i);
			if(card2.getColor() == card.getColor())
				return card2;
			if(card2.getValue() == card.getValue())
				return card2;
			if(card2.getColor() == 0){
				return card2;
			}
		}
		return null;
	}
	public void addCard(Card newCard) {
		cards.add(newCard);
		
	}
	public Card getNormalColorCard() {
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).color != 0){
				return cards.get(i);
			}
		}
		return null;
	}
	
}
