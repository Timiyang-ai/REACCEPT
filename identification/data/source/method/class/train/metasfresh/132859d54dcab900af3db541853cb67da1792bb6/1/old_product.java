public PageDescriptor createNext()
	{
		return PageDescriptor.builder()
				.selectionUid(pageIdentifier.getSelectionUid())
				.pageUid(UIDStringUtil.createNext())
				.offset(offset + pageSize)
				.pageSize(pageSize)
				.selectionTime(selectionTime)
				.build();
	}