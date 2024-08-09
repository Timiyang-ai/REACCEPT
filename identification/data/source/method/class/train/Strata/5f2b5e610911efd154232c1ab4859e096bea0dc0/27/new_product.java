@Override
  public ResolvedCmsLeg resolve(ReferenceData refData) {
    Schedule adjustedSchedule = paymentSchedule.createSchedule(refData);
    List<Double> cap = getCapSchedule().isPresent() ? capSchedule.resolveValues(adjustedSchedule.getPeriods()) : null;
    List<Double> floor = getFloorSchedule().isPresent() ? floorSchedule.resolveValues(adjustedSchedule.getPeriods()) : null;
    List<Double> notionals = notional.resolveValues(adjustedSchedule.getPeriods());
    DateAdjuster fixingDateAdjuster = fixingDateOffset.resolve(refData);
    DateAdjuster paymentDateAdjuster = paymentDateOffset.resolve(refData);
    ImmutableList.Builder<CmsPeriod> cmsPeriodsBuild = ImmutableList.builder();
    for (int i = 0; i < adjustedSchedule.size(); i++) {
      SchedulePeriod period = adjustedSchedule.getPeriod(i);
      LocalDate fixingDate = fixingDateAdjuster.adjust(
          (fixingRelativeTo.equals(FixingRelativeTo.PERIOD_START)) ? period.getStartDate() : period.getEndDate());
      LocalDate paymentDate = paymentDateAdjuster.adjust(period.getEndDate());
      double signedNotional = payReceive.normalize(notionals.get(i));
      cmsPeriodsBuild.add(CmsPeriod.builder()
          .currency(currency)
          .notional(signedNotional)
          .startDate(period.getStartDate())
          .endDate(period.getEndDate())
          .unadjustedStartDate(period.getUnadjustedStartDate())
          .unadjustedEndDate(period.getUnadjustedEndDate())
          .yearFraction(period.yearFraction(dayCount, adjustedSchedule))
          .paymentDate(paymentDate)
          .fixingDate(fixingDate)
          .caplet(cap != null ? cap.get(i) : null)
          .floorlet(floor != null ? floor.get(i) : null)
          .dayCount(dayCount)
          .index(index)
          .underlyingSwap(createUnderlyingSwap(fixingDate, refData))
          .build());
    }
    return ResolvedCmsLeg.builder()
        .cmsPeriods(cmsPeriodsBuild.build())
        .payReceive(payReceive)
        .build();
  }