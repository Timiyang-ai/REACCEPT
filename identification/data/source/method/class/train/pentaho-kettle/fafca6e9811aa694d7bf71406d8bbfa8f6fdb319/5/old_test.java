  @Test
  public void synchronizeSteps() throws Exception {
    final String stepName = "SharedStep";
    TransMeta transformarion1 = createTransMeta();
    StepMeta step1 = createStepMeta( stepName, true );
    transformarion1.addStep( step1 );
    spoonDelegates.trans.addTransformation( transformarion1 );

    TransMeta transformarion2 = createTransMeta();
    StepMeta step2 = createStepMeta( stepName, true );
    transformarion2.addStep( step2 );
    spoonDelegates.trans.addTransformation( transformarion2 );

    step2.setDescription( AFTER_SYNC_VALUE );
    sharedUtil.synchronizeSteps( step2 );
    assertThat( step1.getDescription(), equalTo( AFTER_SYNC_VALUE ) );
  }