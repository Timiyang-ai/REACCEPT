public void setApplication(final Application application) {
        //Clear references to this command in existing applications
        if (this.application != null
                && this.application.getCommands() != null) {
            this.application.getCommands().remove(this);
        }
        //set the application for this command
        this.application = application;

        //Add the reverse reference in the new applications
        if (this.application != null) {
            Set<Command> commands = this.application.getCommands();
            if (commands == null) {
                commands = new HashSet<>();
                this.application.setCommands(commands);
            }
            if (!commands.contains(this)) {
                commands.add(this);
            }
        }
    }