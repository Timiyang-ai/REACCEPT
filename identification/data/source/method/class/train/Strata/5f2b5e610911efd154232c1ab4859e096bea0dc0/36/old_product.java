@Override
  public ResolvedCds resolve(ReferenceData refData) {
    ResolvedCdsPremiumLeg resolvedPremiumLeg = premiumLeg.resolve(refData);

    return ResolvedCds.builder()
        .legalEntityId(legalEntityId)
        .protectStart(protectStart)
        .resolvedPremiumLeg(resolvedPremiumLeg)
        .settlementDateOffset(settlementDateOffset)
        .build();
  }