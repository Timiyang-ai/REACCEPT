Iterator<de.metas.materialtracking.model.I_PP_Order> retrievePPOrdersWithMissingICs(final Properties ctx, final int limit, final String trxName)
	{
		final IQueryBL queryBL = Services.get(IQueryBL.class);

		final IQueryBuilder<I_PP_Order> ppOrderQueryBuilder = queryBL.createQueryBuilder(I_PP_Order.class, ctx, trxName)
				.addOnlyContextClient()
				.addOnlyActiveRecordsFilter();

		//
		// Only those manufacturing orders which are invoiceable
		ppOrderQueryBuilder.filter(getPP_OrderInvoiceableFilter(new PlainContextAware(ctx, trxName)));

		//
		// Order by
		// (just to have a predictable order)
		ppOrderQueryBuilder.orderBy()
				.addColumn(I_PP_Order.COLUMN_PP_Order_ID);

		//
		// Execute query and return
		return ppOrderQueryBuilder
				.setLimit(limit)
				.create()
				.iterate(de.metas.materialtracking.model.I_PP_Order.class);
	}