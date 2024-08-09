@Override
    public void queueStringForComm(final String input) {        
        String commandString = input;
        
        if (! commandString.endsWith("\n")) {
            commandString += "\n";
        }
        
        // Add command to queue
        GcodeCommand command = this.commandBuffer.appendCommandString(commandString);
    }