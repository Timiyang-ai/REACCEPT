@ExpectPermission(Permission.CREATE_CARD)
	@RequestMapping(value = "/api/column/{columnId}/card", method = RequestMethod.POST)
	public void create(@PathVariable("columnId") int columnId, @RequestBody CardData card, User user) {
		Card createdCard = cardService.createCard(card.getName(), columnId, new Date(), user);

        ProjectAndBoard projectAndBoard = boardRepository.findProjectAndBoardByColumnId(columnId);

		if(card.getDescription() != null) {
		    cardDataService.updateDescription(createdCard.getId(), card.getDescription(), new Date(), user.getId());
        }

        if(card.getLabels().size() > 0) {
		    for(BulkOperation op: card.labels) {
		        bulkOperationService.addUserLabel(projectAndBoard.getProject().getShortName(),
                    op.getLabelId(),
                    op.getValue(),
                    Collections.singletonList(createdCard.getId()),
                    user);
            }
        }

        if(card.getDueDate() != null) {
            bulkOperationService.setDueDate(projectAndBoard.getProject().getShortName(),
                Collections.singletonList(createdCard.getId()),
                card.getDueDate().getValue(),
                user);
        }

        if(card.getMilestone() != null) {
            bulkOperationService.setMilestone(projectAndBoard.getProject().getShortName(),
                Collections.singletonList(createdCard.getId()),
                card.getMilestone().getValue(),
                user);
        }

		emitCreateCard(columnId, createdCard, user);
	}