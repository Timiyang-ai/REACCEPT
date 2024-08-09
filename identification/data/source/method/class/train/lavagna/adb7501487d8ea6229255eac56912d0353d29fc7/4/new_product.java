@ExpectPermission(Permission.CREATE_CARD)
	@RequestMapping(value = "/api/column/{columnId}/card", method = RequestMethod.POST)
	public Card create(@PathVariable("columnId") int columnId, @RequestBody CardData card, UserWithPermission user) {
		Card createdCard = cardService.createCard(card.getName(), columnId, new Date(), user);

        ProjectAndBoard projectAndBoard = boardRepository.findProjectAndBoardByColumnId(columnId);

		if(card.getDescription() != null) {
		    cardDataService.updateDescription(createdCard.getId(), card.getDescription(), new Date(), user.getId());
        }

        if(user.getBasePermissions().containsKey(Permission.MANAGE_LABEL_VALUE) && card.getLabels().size() > 0) {
		    for(BulkOperation op: card.getLabels()) {
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

        if(card.getAssignedUsers().size() > 0) {
		    for(BulkOperation op: card.getAssignedUsers()) {
                bulkOperationService.assign(projectAndBoard.getProject().getShortName(),
                    Collections.singletonList(createdCard.getId()),
                    op.getValue(),
                    user);
            }
        }

        if(user.getBasePermissions().containsKey(Permission.CREATE_FILE) && card.getFiles().size() > 0) {
		    for(NewCardFile file : card.getFiles()) {
		        cardDataService.assignFileToCard(file.getName(), file.getDigest(), createdCard.getId(), user, new Date());
            }
        }

		emitCreateCard(columnId, createdCard, user);

		return createdCard;
	}