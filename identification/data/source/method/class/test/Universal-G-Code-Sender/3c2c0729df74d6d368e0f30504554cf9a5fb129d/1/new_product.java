@Override
    public void queueStringForComm(final String input) {        
        String commandString = input;
        
        // Add command to queue
        this.commandBuffer.add(commandString);
    }