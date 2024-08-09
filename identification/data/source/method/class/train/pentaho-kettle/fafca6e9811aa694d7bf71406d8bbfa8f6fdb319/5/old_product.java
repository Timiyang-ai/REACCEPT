public synchronized void synchronizeSteps( StepMeta step ) {
    if ( !step.isShared() ) {
      return;
    }
    synchronizeTransformations( step, stepMetaSynchronizationHandler );
  }