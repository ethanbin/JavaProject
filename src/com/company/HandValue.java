package com.company;

import java.util.*;

/**
 * Created by Ethan on 11/13/2017.
 */
public class HandValue {
    private enum HandRankings {
        ROYAL_FLUSH(9), STRAIGHT_FLUSH(8), FOUR_OF_A_KIND(7), FULL_HOUSE(6),
            FLUSH(5), STRAIGHT(4), THREE_OF_A_KIND(3), TWO_PAIRS(2), PAIR(1), HIGH_CARD(0);

        private int handRankingStrength;

        HandRankings(int strength) {
            handRankingStrength = strength;
        }

        int getHandRankingStrength(){
            return handRankingStrength;
        }
    }

    // first value in the list is the hand ranking value, then the high card
    private List<Integer> handValue;
    private List<Card> holeAndCommunityCards;
    private boolean wasEvaluated;

    HandValue(List<Card> communityCards, List<Card> holeCards) {
        init();
        holeAndCommunityCards.addAll(communityCards);
        holeAndCommunityCards.addAll(holeCards);
    }

    private void init(){
        handValue = new ArrayList<>();
        holeAndCommunityCards = new ArrayList<>();
        wasEvaluated = false;
    }

    public void evaluateHand(){

        if (!wasEvaluated){
            // findRoyalFlush
        }
        if (!wasEvaluated){
            findStraightFlush();
        }
        if (!wasEvaluated){
            // findFourOfAKind();
        }
        if (!wasEvaluated){
            // findFullHouse();
        }
        if (!wasEvaluated){
            findFlush();
        }
        if (!wasEvaluated){
            // findStraight();
        }
        if (!wasEvaluated){
            // findThreeOfAKind();
        }
        if (!wasEvaluated){
            // findTwoPairs();
        }
        if (!wasEvaluated){
            // findPair();
        }
        if (!wasEvaluated) {
            // findHighCard();
        }

    }

    public void findStraightFlush() {
        Collections.sort(holeAndCommunityCards, new SuitComparator());
        int highValue = holeAndCommunityCards.get(0).getValue();

        // represents how many cards of the same suit we found in a row so far
        int matchingCardsInARow = 1;

        // bool value represents whether or not we might be able to have a straight flush at the moment
        // (true when last card(s) and current card are the same suit)
        boolean potentiallyAStraightFlush = false;
        for (int i = 1; i < holeAndCommunityCards.size() && !(i >= 4 && !potentiallyAStraightFlush) && matchingCardsInARow < 5; i++) {
            if (holeAndCommunityCards.get(i - 1).getSuitValue() == holeAndCommunityCards.get(i).getSuitValue() &&
                    holeAndCommunityCards.get(i - 1).getValue() == holeAndCommunityCards.get(i).getValue() - 1){
                potentiallyAStraightFlush = true;
                matchingCardsInARow++;
            }
            else{
                highValue = holeAndCommunityCards.get(i).getValue();
                potentiallyAStraightFlush = false;
                matchingCardsInARow = 1;
            }
        }
        if (matchingCardsInARow == 5){
            handValue.add(HandRankings.FLUSH.getHandRankingStrength());
            handValue.add(highValue);
            wasEvaluated = true;
        }
    }

    public void findFlush() {
        Collections.sort(holeAndCommunityCards, new SuitComparator());
        int highValue = holeAndCommunityCards.get(0).getValue();

        // represents how many cards of the same suit we found in a row so far
        int matchingCardsInARow = 1;

        // bool value represents whether or not we might be able to have a flush at the moment
        // (true when last card(s) and current card are the same suit)
        boolean potentiallyAFlush = false;
        for (int i = 1; i < holeAndCommunityCards.size() && !(i >= 4 && !potentiallyAFlush) && matchingCardsInARow < 5; i++) {
            if (holeAndCommunityCards.get(i - 1).getSuitValue() == holeAndCommunityCards.get(i).getSuitValue()){
                potentiallyAFlush = true;
                matchingCardsInARow++;
            }
            else{
                highValue = holeAndCommunityCards.get(i).getValue();
                potentiallyAFlush = false;
                matchingCardsInARow = 1;
            }
        }
        if (matchingCardsInARow == 5){
            handValue.add(HandRankings.STRAIGHT_FLUSH.getHandRankingStrength());
            handValue.add(highValue);
            wasEvaluated = true;
        }
    }

    @Override
    public String toString(){
        if (handValue.size() == 0)
            return "Hand not evaluated.";
        HandRankings[] rankings = HandRankings.values();
        Collections.reverse(Arrays.asList(rankings));
        String ret = rankings[handValue.get(0)].toString() + ": ";
        for (int i = 1; i < handValue.size(); i++) {
            ret += handValue.get(i) + " ";
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Card> holeCards = new ArrayList<>();
        holeCards.add(new Card(Card.Suit.DIAMONDS, 3));
        holeCards.add(new Card(Card.Suit.DIAMONDS, 4));

        List<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card(Card.Suit.DIAMONDS, 6));
        communityCards.add(new Card(Card.Suit.DIAMONDS, 7));
        communityCards.add(new Card(Card.Suit.DIAMONDS, 5));
        communityCards.add(new Card(Card.Suit.CLUBS, 6));
        communityCards.add(new Card(Card.Suit.CLUBS, 10));

        HandValue hv = new HandValue(holeCards, communityCards);
        hv.evaluateHand();
        System.out.println(hv);
    }
}