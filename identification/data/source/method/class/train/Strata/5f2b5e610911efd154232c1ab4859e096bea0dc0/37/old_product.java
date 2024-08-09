@Override
  public ResolvedIborCapFloorLeg resolve(ReferenceData refData) {
    Schedule adjustedSchedule = paymentSchedule.createSchedule();
    List<Double> cap = getCapSchedule().isPresent() ? capSchedule.resolveValues(adjustedSchedule.getPeriods()) : null;
    List<Double> floor = getFloorSchedule().isPresent() ? floorSchedule.resolveValues(adjustedSchedule.getPeriods()) : null;
    List<Double> notionals = notional.resolveValues(adjustedSchedule.getPeriods());
    DateAdjuster paymentDateAdjuster = paymentDateOffset.toDateAdjuster(refData);
    DateAdjuster fixingDateAdjuster = calculation.getFixingDateOffset().toDateAdjuster(refData);

    ImmutableList.Builder<IborCapletFloorletPeriod> periodsBuild = ImmutableList.builder();
    for (int i = 0; i < adjustedSchedule.size(); i++) {
      SchedulePeriod period = adjustedSchedule.getPeriod(i);
      LocalDate paymentDate = paymentDateAdjuster.adjust(period.getEndDate());
      LocalDate fixingDate = fixingDateAdjuster.adjust(
          (calculation.getFixingRelativeTo().equals(FixingRelativeTo.PERIOD_START)) ?
              period.getStartDate() : period.getEndDate());
      IborRateObservation observation = IborRateObservation.of(calculation.getIndex(), fixingDate, refData);
      double signedNotional = payReceive.normalize(notionals.get(i));
      periodsBuild.add(IborCapletFloorletPeriod.builder()
          .unadjustedStartDate(period.getUnadjustedStartDate())
          .unadjustedEndDate(period.getUnadjustedEndDate())
          .startDate(period.getStartDate())
          .endDate(period.getEndDate())
          .rateObservation(observation)
          .paymentDate(paymentDate)
          .notional(signedNotional)
          .currency(currency)
          .yearFraction(period.yearFraction(calculation.getDayCount(), adjustedSchedule))
          .caplet(cap != null ? cap.get(i) : null)
          .floorlet(floor != null ? floor.get(i) : null)
          .build());
    }
    return ResolvedIborCapFloorLeg.builder()
        .capletFloorletPeriods(periodsBuild.build())
        .payReceive(payReceive)
        .build();
  }