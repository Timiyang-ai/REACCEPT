public Hierarchy createFor(@NonNull final BPartnerId bPartnerId)
	{
		return createFor(
				bPartnerId/* starting point */,
				Hierarchy.builder() /* result builder */,
				new HashSet<BPartnerId>() /* helper to make sure we don't enter a cycle */
		);
	}