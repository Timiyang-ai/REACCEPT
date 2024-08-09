@Override
    public ProcessorDTO wakeUp(ProcessorDTO processor) {
        return findById(processor.getParentGroupId(), processor.getId())
            .map(initState -> {
                ProcessorDTO current = initState;
                List<Function<ProcessorDTO, ProcessorDTO>> wakeSequence = genrateWakeSequence(current);
                
                for (Function<ProcessorDTO, ProcessorDTO> transition : wakeSequence) {
                    current = transition.apply(createProcessor(current));
                }
                
                return current;
            })
            .orElseThrow(() -> new NifiComponentNotFoundException(processor.getId(), NifiConstants.NIFI_COMPONENT_TYPE.PROCESSOR, null));
    }