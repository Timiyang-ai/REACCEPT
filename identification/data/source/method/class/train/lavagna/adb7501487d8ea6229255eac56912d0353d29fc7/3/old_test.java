	@Test
	public void create() {
		CardData cardData = new CardData();
		cardData.setName("name");

		when(cardService.createCard(eq("name"), eq(columnId), any(Date.class), eq(user))).thenReturn(card);

		cardController.create(columnId, cardData, user);

		verify(cardService).createCard(eq("name"), eq(columnId), any(Date.class), eq(user));
		verify(eventEmitter).emitCreateCard(project.getShortName(), board.getShortName(), boardColumn.getId(),
				card, user);
        verify(cardDataService, never()).updateDescription(eq(cardId),
            anyString(),
            any(Date.class),
            eq(userId));
        verify(bulkOperationService, never()).addUserLabel(eq(projectShortName),
            anyInt(),
            any(CardLabelValue.LabelValue.class),
            ArgumentMatchers.<Integer>anyList(),
            eq(user));
        verify(bulkOperationService, never()).setDueDate(eq(projectShortName),
            ArgumentMatchers.<Integer>anyList(),
            any(CardLabelValue.LabelValue.class),
            eq(user));
        verify(bulkOperationService, never()).setMilestone(eq(projectShortName),
            ArgumentMatchers.<Integer>anyList(),
            any(CardLabelValue.LabelValue.class),
            eq(user));
        verify(bulkOperationService, never()).assign(eq(projectShortName),
            ArgumentMatchers.<Integer>anyList(),
            any(CardLabelValue.LabelValue.class),
            eq(user));
        verify(cardDataService, never()).assignFileToCard(anyString(),
            anyString(),
            anyInt(),
            eq(user),
            any(Date.class));
	}