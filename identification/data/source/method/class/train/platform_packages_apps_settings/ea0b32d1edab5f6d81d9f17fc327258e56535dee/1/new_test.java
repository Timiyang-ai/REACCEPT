    @Test
    public void sortCards_shouldBeDescendingOrder() {
        final List<ContextualCard> cards = new ArrayList<>();
        final ContextualCard card1 =
                buildContextualCard(TEST_SLICE_URI).mutate().setRankingScore(99.0).build();
        final ContextualCard card2 =
                buildContextualCard("context://test/test2").mutate().setRankingScore(88.0).build();
        cards.add(card1);
        cards.add(card2);

        final List<ContextualCard> sortedCards = mManager.sortCards(cards);

        assertThat(sortedCards.get(0).getSliceUri()).isEqualTo(Uri.parse(TEST_SLICE_URI));
    }