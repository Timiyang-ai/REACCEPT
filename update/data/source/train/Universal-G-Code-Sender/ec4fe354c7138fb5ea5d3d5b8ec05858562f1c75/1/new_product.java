@Override
    public void streamCommands() {
        // If there are no commands to send, exit.
        if (!this.getNextCommand().isPresent()) {
            return;
        }
        
        // If streaming is paused, exit.
        if (this.sendPaused) {
            // Another NO-OP
            return;
        }
        
        // Send command if:
        // There is room in the buffer.
        // AND We are NOT in single step mode.
        // OR  We are in single command mode and there are no active commands.
        while (this.getNextCommand().isPresent() &&
                CommUtils.checkRoomInBuffer(
                    this.sentBufferSize,
                    this.getNextCommand().get().getCommandString(),
                    this.getBufferSize())
                && allowMoreCommands()) {

            GcodeCommand command = this.getNextCommand().get();

            if (command.getCommandString().isEmpty()) {
                dispatchListenerEvents(COMMAND_SKIPPED, command);
                synchronized(nextCommandLock) {
                    nextCommand = null;
                }
                continue;
            }

            String commandString = command.getCommandString().trim();
            
            this.activeCommandList.add(command);
            this.sentBufferSize += commandString.length() + 1;
        
            // Command already has a newline attached.
            this.sendMessageToConsoleListener(">>> " + commandString + "\n");
            
            try {
                this.sendingCommand(commandString);
                conn.sendStringToComm(commandString + "\n");
                synchronized(nextCommandLock) {
                    nextCommand = null;
                }
                dispatchListenerEvents(SerialCommunicatorEvent.COMMAND_SENT, command);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }