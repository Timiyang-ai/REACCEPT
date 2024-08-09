    @Test
    public void wakeUp() {

        List<ProcessorDTO> processors = new ArrayList<>();
        processors.add(newProcessor("cron-stopped", "STOPPED", NifiFeedConstants.SCHEDULE_STRATEGIES.CRON_DRIVEN.name(), "0 0 12 1/1 * ? *"));
        processors.add(newProcessor("cron-disabled", "DISABLED", NifiFeedConstants.SCHEDULE_STRATEGIES.CRON_DRIVEN.name(), "0 0 12 1/1 * ? *"));
        processors.add(newProcessor("cron-started", "RUNNING", NifiFeedConstants.SCHEDULE_STRATEGIES.CRON_DRIVEN.name(), "0 0 12 1/1 * ? *"));
        processors.add(newProcessor("timer-stopped", "STOPPED", NifiFeedConstants.SCHEDULE_STRATEGIES.TIMER_DRIVEN.name(), "5 min"));
        processors.add(newProcessor("timer-disabled", "DISABLED", NifiFeedConstants.SCHEDULE_STRATEGIES.TIMER_DRIVEN.name(), "5 min"));
        processors.add(newProcessor("timer-started", "RUNNING", NifiFeedConstants.SCHEDULE_STRATEGIES.TIMER_DRIVEN.name(), "5 min"));

        // Mock NiFi Process Groups REST client
        final NiFiProcessorsRestClient client = Mockito.mock(AbstractNiFiProcessorsRestClient.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(client.updateWithRetry(Mockito.any(ProcessorDTO.class), Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                ProcessorDTO processorDTO = invocationOnMock.getArgumentAt(0, ProcessorDTO.class);
                return processorDTO;
            }
        });

        processors.stream().forEach(processorDTO -> {
            Mockito.when(client.findById("parent", processorDTO.getId())).thenReturn(Optional.of(processorDTO));
            ProcessorDTO wakeupDto = client.wakeUp(processorDTO);

            if (wakeupDto.getConfig() != null) {
                //Ensure the dto after wakeup matches the starting dto
                Assert.assertEquals(wakeupDto.getConfig().getSchedulingStrategy(), processorDTO.getConfig().getSchedulingStrategy());
                Assert.assertEquals(wakeupDto.getConfig().getSchedulingPeriod(), processorDTO.getConfig().getSchedulingPeriod());

                if (wakeupDto.getState() != null) {
                    Assert.assertEquals(wakeupDto.getState(), processorDTO.getState());
                }
            }
        });


    }