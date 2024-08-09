@Override
    public void streamCommands() {
        if (this.commandBuffer.currentCommand() == null) {
            // NO-OP
            return;
        }
        
        if (this.sendPaused) {
            // Another NO-OP
            return;
        }
        
        // The GcodeCommandBuffer class always preloads the next command, so as
        // long as the currentCommand exists and hasn't been sent it is the next
        // which should be sent.
        
        while ((this.commandBuffer.currentCommand().isSent() == false) &&
                CommUtils.checkRoomInBuffer(this.activeCommandList, this.commandBuffer.currentCommand())) {
            GcodeCommand command = this.commandBuffer.currentCommand();
            
            command.setSent(true);
            this.activeCommandList.add(command);

            // Newlines are embedded when they get queued.
            this.sendStringToComm(command.getCommandString());
            
            dispatchListenerEvents(COMMAND_SENT, this.commandSentListeners, command);

            // Load the next command.
            this.commandBuffer.nextCommand();
        }
    }