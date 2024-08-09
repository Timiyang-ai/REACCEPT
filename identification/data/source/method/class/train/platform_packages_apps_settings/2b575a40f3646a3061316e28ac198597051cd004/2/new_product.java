public List<ContextualCard> getDisplayableCards() {
        final List<ContextualCard> cards = new ArrayList<>();
        final List<Future<ContextualCard>> displayableCards = new ArrayList<>();
        // Check displayable future
        for (ConditionalCardController card : mCardControllers) {
            final DisplayableChecker future = new DisplayableChecker(getController(card.getId()));
            displayableCards.add(mExecutorService.submit(future));
        }
        // Collect future and add displayable cards
        for (Future<ContextualCard> cardFuture : displayableCards) {
            try {
                final ContextualCard card = cardFuture.get(
                        DISPLAYABLE_CHECKER_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                if (card != null) {
                    cards.add(card);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.w(TAG, "Failed to get displayable state for card, likely timeout. Skipping", e);
            }
        }
        return cards;
    }