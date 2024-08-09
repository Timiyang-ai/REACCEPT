@Override
  public final EppResponse run() throws EppException {
    extensionManager.register(MetadataExtension.class);
    extensionManager.validate();
    validateClientIsLoggedIn(clientId);
    DateTime now = ofy().getTransactionTime();
    DomainBase existingDomain = loadAndVerifyExistence(DomainBase.class, targetId, now);
    verifyOptionalAuthInfo(authInfo, existingDomain);
    verifyHasPendingTransfer(existingDomain);
    verifyResourceOwnership(clientId, existingDomain);
    String tld = existingDomain.getTld();
    if (!isSuperuser) {
      checkAllowedAccessToTld(clientId, tld);
    }
    TransferData transferData = existingDomain.getTransferData();
    String gainingClientId = transferData.getGainingClientId();
    Registry registry = Registry.get(existingDomain.getTld());
    HistoryEntry historyEntry = buildHistoryEntry(existingDomain, registry, now, gainingClientId);
    // Create a transfer billing event for 1 year, unless the superuser extension was used to set
    // the transfer period to zero. There is not a transfer cost if the transfer period is zero.
    Optional<BillingEvent.OneTime> billingEvent =
        (transferData.getTransferPeriod().getValue() == 0)
            ? Optional.empty()
            : Optional.of(
                new BillingEvent.OneTime.Builder()
                    .setReason(Reason.TRANSFER)
                    .setTargetId(targetId)
                    .setClientId(gainingClientId)
                    .setPeriodYears(1)
                    .setCost(getDomainRenewCost(targetId, transferData.getTransferRequestTime(), 1))
                    .setEventTime(now)
                    .setBillingTime(now.plus(Registry.get(tld).getTransferGracePeriodLength()))
                    .setParent(historyEntry)
                    .build());
    // If we are within an autorenew grace period, cancel the autorenew billing event and don't
    // increase the registration time, since the transfer subsumes the autorenew's extra year.
    GracePeriod autorenewGrace =
        getOnlyElement(existingDomain.getGracePeriodsOfType(GracePeriodStatus.AUTO_RENEW), null);
    int extraYears = transferData.getTransferPeriod().getValue();
    if (autorenewGrace != null) {
      extraYears = 0;
      // During a normal transfer, if the domain is in the auto-renew grace period, the auto-renew
      // billing event is cancelled and the gaining registrar is charged for the one year renewal.
      // But, if the superuser extension is used to request a transfer without an additional year
      // then the gaining registrar is not charged for the one year renewal and the losing registrar
      // still needs to be charged for the auto-renew.
      if (billingEvent.isPresent()) {
        ofy().save().entity(
            BillingEvent.Cancellation.forGracePeriod(autorenewGrace, historyEntry, targetId));
      }
    }
    // Close the old autorenew event and poll message at the transfer time (aka now). This may end
    // up deleting the poll message.
    updateAutorenewRecurrenceEndTime(existingDomain, now);
    DateTime newExpirationTime = extendRegistrationWithCap(
        now, existingDomain.getRegistrationExpirationTime(), extraYears);
    // Create a new autorenew event starting at the expiration time.
    BillingEvent.Recurring autorenewEvent = new BillingEvent.Recurring.Builder()
        .setReason(Reason.RENEW)
        .setFlags(ImmutableSet.of(Flag.AUTO_RENEW))
        .setTargetId(targetId)
        .setClientId(gainingClientId)
        .setEventTime(newExpirationTime)
        .setRecurrenceEndTime(END_OF_TIME)
        .setParent(historyEntry)
        .build();
    // Create a new autorenew poll message.
    PollMessage.Autorenew gainingClientAutorenewPollMessage = new PollMessage.Autorenew.Builder()
        .setTargetId(targetId)
        .setClientId(gainingClientId)
        .setEventTime(newExpirationTime)
        .setAutorenewEndTime(END_OF_TIME)
        .setMsg("Domain was auto-renewed.")
        .setParent(historyEntry)
        .build();
    // Construct the post-transfer domain.
    DomainBase partiallyApprovedDomain =
        approvePendingTransfer(existingDomain, TransferStatus.CLIENT_APPROVED, now);
    DomainBase newDomain =
        partiallyApprovedDomain
            .asBuilder()
            // Update the transferredRegistrationExpirationTime here since approvePendingTransfer()
            // doesn't know what to set it to and leaves it null.
            .setTransferData(
                partiallyApprovedDomain
                    .getTransferData()
                    .asBuilder()
                    .setTransferredRegistrationExpirationTime(newExpirationTime)
                    .build())
            .setRegistrationExpirationTime(newExpirationTime)
            .setAutorenewBillingEvent(Key.create(autorenewEvent))
            .setAutorenewPollMessage(Key.create(gainingClientAutorenewPollMessage))
            // Remove all the old grace periods and add a new one for the transfer.
            .setGracePeriods(
                (billingEvent.isPresent())
                    ? ImmutableSet.of(
                        GracePeriod.forBillingEvent(GracePeriodStatus.TRANSFER, billingEvent.get()))
                    : ImmutableSet.of())
            .setLastEppUpdateTime(now)
            .setLastEppUpdateClientId(clientId)
            .build();
    // Create a poll message for the gaining client.
    PollMessage gainingClientPollMessage = createGainingTransferPollMessage(
        targetId,
        newDomain.getTransferData(),
        newExpirationTime,
        historyEntry);
    ImmutableSet.Builder<ImmutableObject> entitiesToSave = new ImmutableSet.Builder<>();
    entitiesToSave.add(
        newDomain,
        historyEntry,
        autorenewEvent,
        gainingClientPollMessage,
        gainingClientAutorenewPollMessage);
    billingEvent.ifPresent(entitiesToSave::add);
    ofy().save().entities(entitiesToSave.build());
    // Delete the billing event and poll messages that were written in case the transfer would have
    // been implicitly server approved.
    ofy().delete().keys(existingDomain.getTransferData().getServerApproveEntities());
    return responseBuilder
        .setResData(createTransferResponse(
            targetId, newDomain.getTransferData(), newDomain.getRegistrationExpirationTime()))
        .build();
  }