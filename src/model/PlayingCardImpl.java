package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {

	private Suit suit;
	private Value value;

	// Constructor
	public PlayingCardImpl(Value value, Suit suit) {
		this.suit = suit;
		this.value = value;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof PlayingCard)
		{
			PlayingCard other = (PlayingCard) obj;
			return equals(other);
		}
		return false;
	}

	@Override
	public int getScore() {
		int score;
		switch (value) {
		case ACE:
			score = 1;
			break;
		case JACK:
		case KING:
		case QUEEN:
		case TEN:
			score = 10;
			break;
		case TWO:
			score = 2;
			break;
		case THREE:
			score = 3;
			break;
		case FOUR:
			score = 4;
			break;
		case FIVE:
			score = 5;
			break;
		case SIX:
			score = 6;
			break;
		case SEVEN:
			score = 7;
			break;
		case EIGHT:
			score = 8;
			break;
		case NINE:
			score = 9;
			break;
		default:
			score = 00;
			break;
		}
		return score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		return((card != null) && suit.equals(card.getSuit())
				&&  (value.equals(card.getValue())));
		
	}

	@Override
	public String toString() {

		return String.format("Suit : %s, Value : %s, Score : %d ", suit, value, getScore());
	}

}
