public List<ConditionalCard> getDisplayableCards() {
        final List<ConditionalCard> cards = new ArrayList<>();
        final List<Future<ConditionalCard>> displayableCards = new ArrayList<>();
        // Check displayable future
        for (ConditionalCard card : mCandidates) {
            final DisplayableChecker future = new DisplayableChecker(
                    card, getController(card.getId()));
            displayableCards.add(mExecutorService.submit(future));
        }
        // Collect future and add displayable cards
        for (Future<ConditionalCard> cardFuture : displayableCards) {
            try {
                final ConditionalCard card = cardFuture.get(DISPLAYABLE_CHECKER_TIMEOUT_MS,
                        TimeUnit.MILLISECONDS);
                if (card != null) {
                    cards.add(card);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.w(TAG, "Failed to get displayable state for card, likely timeout. Skipping", e);
            }
        }
        return cards;
    }