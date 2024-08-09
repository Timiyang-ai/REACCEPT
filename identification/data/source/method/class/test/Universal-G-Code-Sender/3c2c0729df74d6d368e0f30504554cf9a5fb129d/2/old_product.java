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
                    this.getNextCommand().get(),
                    this.getBufferSize())
                && allowMoreCommands()) {

            String commandString = this.getNextCommand().get();
            this.activeStringList.add(commandString);

            this.sentBufferSize += commandString.length();
            
            // Newlines are embedded when they get queued so just send it.
        
            // Command already has a newline attached.
            this.sendMessageToConsoleListener(">>> " + commandString);
            
            try {
                this.sendingCommand(commandString);
                conn.sendStringToComm(commandString);
                synchronized(nextCommandLock) {
                    nextCommand = null;
                }
                dispatchListenerEvents(COMMAND_SENT, this.commandSentListeners, commandString.trim());
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }