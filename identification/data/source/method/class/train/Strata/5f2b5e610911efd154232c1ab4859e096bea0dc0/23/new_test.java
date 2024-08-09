  @Test
  public void test_resolve() {
    CmsLeg baseFloor = CmsLeg.builder()
        .floorSchedule(FLOOR)
        .index(INDEX)
        .notional(NOTIONAL)
        .payReceive(PAY)
        .paymentSchedule(SCHEDULE_EUR)
        .build();
    ResolvedCmsLeg resolvedFloor = baseFloor.resolve(REF_DATA);
    LocalDate end1 = LocalDate.of(2016, 10, 21);
    LocalDate fixing1 = EUR_EURIBOR_6M.calculateFixingFromEffective(START, REF_DATA);
    LocalDate fixing2 = EUR_EURIBOR_6M.calculateFixingFromEffective(end1, REF_DATA);
    LocalDate fixing3 = EUR_EURIBOR_6M.calculateFixingFromEffective(END, REF_DATA);
    LocalDate endDate = SCHEDULE_EUR.calculatedEndDate().adjusted(REF_DATA);

    CmsPeriod period1 = CmsPeriod.builder()
        .currency(EUR)
        .floorlet(FLOOR.getInitialValue())
        .notional(-NOTIONAL.getInitialValue())
        .index(INDEX)
        .startDate(START)
        .endDate(end1)
        .unadjustedStartDate(START)
        .unadjustedEndDate(end1)
        .fixingDate(fixing1)
        .paymentDate(end1)
        .yearFraction(EUR_EURIBOR_6M.getDayCount().yearFraction(START, end1))
        .dayCount(EUR_EURIBOR_6M.getDayCount())
        .underlyingSwap(createUnderlyingSwap(fixing1))
        .build();
    CmsPeriod period2 = CmsPeriod.builder()
        .currency(EUR)
        .floorlet(FLOOR.getSteps().get(0).getValue().getModifyingValue())
        .notional(-NOTIONAL.getSteps().get(0).getValue().getModifyingValue())
        .index(INDEX)
        .startDate(end1)
        .endDate(endDate)
        .unadjustedStartDate(end1)
        .unadjustedEndDate(END)
        .fixingDate(fixing2)
        .paymentDate(endDate)
        .yearFraction(EUR_EURIBOR_6M.getDayCount().yearFraction(end1, endDate))
        .dayCount(EUR_EURIBOR_6M.getDayCount())
        .underlyingSwap(createUnderlyingSwap(fixing2))
        .build();
    assertThat(resolvedFloor.getCurrency()).isEqualTo(EUR);
    assertThat(resolvedFloor.getStartDate()).isEqualTo(baseFloor.getStartDate().adjusted(REF_DATA));
    assertThat(resolvedFloor.getEndDate()).isEqualTo(baseFloor.getEndDate().adjusted(REF_DATA));
    assertThat(resolvedFloor.getIndex()).isEqualTo(INDEX);
    assertThat(resolvedFloor.getPayReceive()).isEqualTo(PAY);
    assertThat(resolvedFloor.getCmsPeriods()).hasSize(2);
    assertThat(resolvedFloor.getCmsPeriods().get(0)).isEqualTo(period1);
    assertThat(resolvedFloor.getCmsPeriods().get(1)).isEqualTo(period2);

    CmsLeg baseFloorEnd = CmsLeg.builder()
        .floorSchedule(FLOOR)
        .fixingRelativeTo(FixingRelativeTo.PERIOD_END)
        .index(INDEX)
        .notional(NOTIONAL)
        .payReceive(PAY)
        .paymentSchedule(SCHEDULE_EUR)
        .build();
    ResolvedCmsLeg resolvedFloorEnd = baseFloorEnd.resolve(REF_DATA);
    CmsPeriod period1End = CmsPeriod.builder()
        .currency(EUR)
        .floorlet(FLOOR.getInitialValue())
        .notional(-NOTIONAL.getInitialValue())
        .index(INDEX)
        .startDate(START)
        .endDate(end1)
        .unadjustedStartDate(START)
        .unadjustedEndDate(end1)
        .fixingDate(fixing2)
        .paymentDate(end1)
        .yearFraction(EUR_EURIBOR_6M.getDayCount().yearFraction(START, end1))
        .dayCount(EUR_EURIBOR_6M.getDayCount())
        .underlyingSwap(createUnderlyingSwap(fixing2))
        .build();
    CmsPeriod period2End = CmsPeriod.builder()
        .currency(EUR)
        .floorlet(FLOOR.getSteps().get(0).getValue().getModifyingValue())
        .notional(-NOTIONAL.getSteps().get(0).getValue().getModifyingValue())
        .index(INDEX)
        .startDate(end1)
        .endDate(endDate)
        .unadjustedStartDate(end1)
        .unadjustedEndDate(END)
        .fixingDate(fixing3)
        .paymentDate(endDate)
        .yearFraction(EUR_EURIBOR_6M.getDayCount().yearFraction(end1, endDate))
        .dayCount(EUR_EURIBOR_6M.getDayCount())
        .underlyingSwap(createUnderlyingSwap(fixing3))
        .build();
    assertThat(resolvedFloorEnd.getCurrency()).isEqualTo(EUR);
    assertThat(resolvedFloorEnd.getStartDate()).isEqualTo(baseFloor.getStartDate().adjusted(REF_DATA));
    assertThat(resolvedFloorEnd.getEndDate()).isEqualTo(baseFloor.getEndDate().adjusted(REF_DATA));
    assertThat(resolvedFloorEnd.getIndex()).isEqualTo(INDEX);
    assertThat(resolvedFloorEnd.getPayReceive()).isEqualTo(PAY);
    assertThat(resolvedFloorEnd.getCmsPeriods()).hasSize(2);
    assertThat(resolvedFloorEnd.getCmsPeriods().get(0)).isEqualTo(period1End);
    assertThat(resolvedFloorEnd.getCmsPeriods().get(1)).isEqualTo(period2End);

    CmsLeg baseCap = CmsLeg.builder()
        .index(INDEX)
        .capSchedule(CAP)
        .notional(NOTIONAL)
        .payReceive(PAY)
        .paymentSchedule(SCHEDULE_EUR)
        .paymentDateOffset(PAYMENT_OFFSET)
        .build();
    ResolvedCmsLeg resolvedCap = baseCap.resolve(REF_DATA);
    CmsPeriod periodCap1 = CmsPeriod.builder()
        .currency(EUR)
        .notional(-NOTIONAL.getInitialValue())
        .index(INDEX)
        .caplet(CAP.getInitialValue())
        .startDate(START)
        .endDate(end1)
        .unadjustedStartDate(START)
        .unadjustedEndDate(end1)
        .fixingDate(fixing1)
        .paymentDate(PAYMENT_OFFSET.adjust(end1, REF_DATA))
        .yearFraction(EUR_EURIBOR_6M.getDayCount().yearFraction(START, end1))
        .dayCount(EUR_EURIBOR_6M.getDayCount())
        .underlyingSwap(createUnderlyingSwap(fixing1))
        .build();
    CmsPeriod periodCap2 = CmsPeriod.builder()
        .currency(EUR)
        .notional(-NOTIONAL.getSteps().get(0).getValue().getModifyingValue())
        .index(INDEX)
        .caplet(CAP.getInitialValue())
        .startDate(end1)
        .endDate(endDate)
        .unadjustedStartDate(end1)
        .unadjustedEndDate(END)
        .fixingDate(fixing2)
        .paymentDate(PAYMENT_OFFSET.adjust(endDate, REF_DATA))
        .yearFraction(EUR_EURIBOR_6M.getDayCount().yearFraction(end1, endDate))
        .dayCount(EUR_EURIBOR_6M.getDayCount())
        .underlyingSwap(createUnderlyingSwap(fixing2))
        .build();
    assertThat(resolvedCap.getCurrency()).isEqualTo(EUR);
    assertThat(resolvedCap.getStartDate()).isEqualTo(baseCap.getStartDate().adjusted(REF_DATA));
    assertThat(resolvedCap.getEndDate()).isEqualTo(baseCap.getEndDate().adjusted(REF_DATA));
    assertThat(resolvedCap.getIndex()).isEqualTo(INDEX);
    assertThat(resolvedCap.getPayReceive()).isEqualTo(PAY);
    assertThat(resolvedCap.getCmsPeriods()).hasSize(2);
    assertThat(resolvedCap.getCmsPeriods().get(0)).isEqualTo(periodCap1);
    assertThat(resolvedCap.getCmsPeriods().get(1)).isEqualTo(periodCap2);
  }