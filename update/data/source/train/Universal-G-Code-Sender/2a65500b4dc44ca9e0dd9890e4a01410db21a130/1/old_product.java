@Override
    public void streamCommands() {
        if (this.commandBuffer.size() == 0) {
            // NO-OP
            return;
        }
        
        if (this.sendPaused) {
            // Another NO-OP
            return;
        }
        
        // Send command if:
        // There is room in the buffer.
        // AND We are NOT in single step mode.
        // OR  We are in single command mode and there are no active commands.
        while (CommUtils.checkRoomInBuffer(this.sentBufferSize, this.commandBuffer.peek())
                && allowMoreCommands()) {
            String commandString = this.commandBuffer.pop();
            this.activeStringList.add(commandString);
            this.sentBufferSize += commandString.length();
            
            // Newlines are embedded when they get queued so just send it.
            this.sendStringToComm(commandString);
            
            GcodeCommand command = new GcodeCommand(commandString);
            command.setSent(true);
            dispatchListenerEvents(COMMAND_SENT, this.commandSentListeners, command);
        }
        
        System.out.println("Number active commands: " + this.activeStringList.size());
    }