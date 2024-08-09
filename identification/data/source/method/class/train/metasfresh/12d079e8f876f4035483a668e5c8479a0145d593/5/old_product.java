@VisibleForTesting
	void updateSchedules(final Properties ctx, final List<OlAndSched> olsAndScheds)
	{
		if (olsAndScheds.isEmpty())
		{
			return;
		}

		//
		// Services
		final IBPartnerBL bpartnerBL = Services.get(IBPartnerBL.class);
		final IProductBL productsService = Services.get(IProductBL.class);
		final IShipmentScheduleDeliveryDayBL shipmentScheduleDeliveryDayBL = Services.get(IShipmentScheduleDeliveryDayBL.class);
		final IShipmentScheduleEffectiveBL shipmentScheduleEffectiveBL = Services.get(IShipmentScheduleEffectiveBL.class);
		final IShipmentScheduleAllocDAO shipmentScheduleAllocDAO = Services.get(IShipmentScheduleAllocDAO.class);
		final IBPartnerProductDAO bpartnerProductDAO = Services.get(IBPartnerProductDAO.class);
		final IDeliveryDayBL deliveryDayBL = Services.get(IDeliveryDayBL.class);

		//
		// Briefly update our shipment schedules:
		// * set BPartnerAddress_Override if was not set before
		// * update HeaderAggregationKey
		for (final OlAndSched olAndSched : olsAndScheds)
		{
			final I_M_ShipmentSchedule sched = olAndSched.getSched();

			updateCatchUomId(sched);

			updateWarehouseId(sched);

			shipmentScheduleBL.updateBPArtnerAddressOverrideIfNotYetSet(sched);

			shipmentScheduleBL.updateHeaderAggregationKey(sched);

			updateShipmentConstraints(sched);
		}

		final ShipmentSchedulesDuringUpdate firstRun = generate(ctx, olsAndScheds, null);
		firstRun.updateCompleteStatusAndSetQtyToZeroWhereNeeded();

		final int removeCnt = applyCandidateProcessors(ctx, firstRun);
		logger.info("{} records were discarded by candidate processors", removeCnt);

		// evaluate the processor's result: lines that have been discarded won't
		// be delivered and won't be validated in the second run.
		for (final DeliveryLineCandidate inOutLine : firstRun.getAllLines())
		{
			if (inOutLine.isDiscarded())
			{
				inOutLine.setQtyToDeliver(BigDecimal.ZERO);
			}
			else
			{
				// remember: 'removeLine' means that a *new* line might be
				// created for the corresponding olAndSched
				inOutLine.removeFromGroup();
				firstRun.removeLine(inOutLine);
			}
		}

		// make the second run
		final IShipmentSchedulesDuringUpdate secondRun = generate(ctx, olsAndScheds, firstRun);

		// finally update the shipment schedule entries
		for (final OlAndSched olAndSched : olsAndScheds)
		{
			final I_M_ShipmentSchedule sched = olAndSched.getSched();
			final IDeliverRequest deliverRequest = olAndSched.getDeliverRequest();
			final BPartnerId bpartnerId = shipmentScheduleEffectiveBL.getBPartnerId(sched); // task 08756: we don't really care for the ol's partner, but for the partner who will actually receive the shipment.

			sched.setAllowConsolidateInOut(bpartnerBL.isAllowConsolidateInOutEffective(bpartnerId, SOTrx.SALES));

			updatePreparationAndDeliveryDate(sched);

			//
			// Delivery Day related info:
			// TODO: invert dependency add make this pluggable from de.metas.tourplanning module
			shipmentScheduleDeliveryDayBL.updateDeliveryDayInfo(sched);

			// task 09358: ol.qtyReserved should be as correct as QtyOrdered and QtyDelivered, but in some cases isn't. this here is a workaround to the problem
			// task 09869: don't rely on ol anyways
			final BigDecimal qtyDelivered = shipmentScheduleAllocDAO.retrieveQtyDelivered(sched);
			sched.setQtyDelivered(qtyDelivered);
			sched.setQtyReserved(BigDecimal.ZERO.max(deliverRequest.getQtyOrdered().subtract(sched.getQtyDelivered())));

			updateLineNetAmt(olAndSched);

			ShipmentScheduleQtysHelper.updateQtyToDeliver(olAndSched, secondRun);

			markAsChangedByUpdateProcess(sched);

			if (olAndSched.hasSalesOrderLine())
			{
				final DocStatus orderDocStatus = olAndSched.getOrderDocStatus();
				if (!orderDocStatus.isCompletedOrClosedOrReversed() // task 07355: thread closed orders like completed orders
						&& !sched.isProcessed() // task 05206: ts: don't try to delete already processed scheds..it won't work
						&& sched.getQtyDelivered().signum() == 0 // also don't try to delete if there is already a picked or delivered Qty.
						&& sched.getQtyPickList().signum() == 0)
				{
					logger.debug("QtyToDeliver_Override=" + sched.getQtyToDeliver_Override()
							+ "; QtyReserved=" + sched.getQtyReserved()
							+ "; DocStatus=" + orderDocStatus
							+ "; => Deleting " + sched);

					InterfaceWrapperHelper.delete(sched);
					continue;
				}
			}

			updateProcessedFlag(sched);
			if (sched.isProcessed())
			{
				// 04870 : Delivery rule force assumes we deliver full quantity ordered if qtyToDeliver_Override is null.
				// 06019 : check both DeliveryRule, as DeliveryRule_Override
				final boolean deliveryRuleIsForced = DeliveryRule.FORCE.equals(shipmentScheduleEffectiveBL.getDeliveryRule(sched));
				if (deliveryRuleIsForced)
				{
					sched.setQtyToDeliver(BigDecimal.ZERO);
					shipmentSchedulePA.save(sched);
				}
				else
				{
					Check.errorUnless(sched.getQtyToDeliver().signum() == 0, "{} has QtyToDeliver = {} (should be zero)", sched, sched.getQtyToDeliver());
				}
				continue;
			}

			// task 08694
			// I talked with Mark and he observed that in the wiki-page of 08459 it is specified differently.
			// I will let it here nevertheless, so we can keep track of it's way to work

			final BPartnerId partnerId = BPartnerId.ofRepoId(sched.getC_BPartner_ID());
			final ProductId productId = ProductId.ofRepoId(sched.getM_Product_ID());

			// FRESH-334 retrieve the bp product for org or for org 0
			final I_M_Product product = productsService.getById(productId);
			final OrgId orgId = OrgId.ofRepoId(product.getAD_Org_ID());

			final I_C_BPartner_Product bpp = bpartnerProductDAO.retrieveBPartnerProductAssociation(ctx, partnerId, productId, orgId);
			if (bpp == null)
			{
				// in case no dropship bpp entry was found, the schedule shall not be dropship
				sched.setIsDropShip(false);
			}
			else
			{

				final boolean isDropShip = bpp.isDropShip();

				if (isDropShip)
				{
					// if there is bpp that is dropship and has a C_BPartner_Vendor_ID,
					// set the customer's vendor for the given product in the schedule
					sched.setC_BPartner_Vendor_ID(bpp.getC_BPartner_Vendor_ID());
				}

				// set the dropship flag in shipment schedule as it is in the bpp
				sched.setIsDropShip(isDropShip);
			}

			// 08860
			// update preparation date override based on delivery date effective
			// DO this only if the preparationDate_Override was not already set manually or by the process

			if (sched.getDeliveryDate_Override() != null && sched.getPreparationDate_Override() == null)
			{
				final ZonedDateTime deliveryDate = shipmentScheduleEffectiveBL.getDeliveryDate(sched);

				final IContextAware contextAwareSched = InterfaceWrapperHelper.getContextAware(sched);
				final BPartnerLocationId bpLocationId = shipmentScheduleEffectiveBL.getBPartnerLocationId(sched);

				final ZonedDateTime calculationTime = TimeUtil.asZonedDateTime(sched.getCreated());
				final ZonedDateTime preparationDate = deliveryDayBL.calculatePreparationDateOrNull(
						contextAwareSched,
						SOTrx.SALES,
						calculationTime,
						deliveryDate,
						bpLocationId);

				// In case the DeliveryDate Override is set, also update the preparationDate override
				sched.setPreparationDate_Override(TimeUtil.asTimestamp(preparationDate));

			}

			shipmentSchedulePA.save(sched);
		}
	}