@ExpectPermission(Permission.CREATE_CARD)
	@RequestMapping(value = "/api/column/{columnId}/card", method = RequestMethod.POST)
	public void create(@PathVariable("columnId") int columnId, @RequestBody CardData card, User user) {
		Card createdCard = cardService.createCard(card.name, columnId, new Date(), user);

        ProjectAndBoard projectAndBoard = boardRepository.findProjectAndBoardByColumnId(columnId);

		if(card.description != null) {
		    cardDataService.updateDescription(createdCard.getId(), card.description, new Date(), user.getId());
        }

        if(card.labels.size() > 0) {
		    for(BulkOperation op: card.labels) {
		        bulkOperationService.addUserLabel(projectAndBoard.getProject().getShortName(),
                    op.getLabelId(),
                    op.getValue(),
                    Collections.singletonList(createdCard.getId()),
                    user);
            }
        }

		emitCreateCard(columnId, createdCard, user);
	}