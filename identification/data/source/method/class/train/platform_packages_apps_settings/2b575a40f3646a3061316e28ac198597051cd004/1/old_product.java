public List<ConditionalCard> getDisplayableCards() {
        final List<ConditionalCard> cards = new ArrayList<>();
        for (ConditionalCard card : mCandidates) {
            if (getController(card.getId()).isDisplayable()) {
                cards.add(card);
            }
        }
        return cards;
    }