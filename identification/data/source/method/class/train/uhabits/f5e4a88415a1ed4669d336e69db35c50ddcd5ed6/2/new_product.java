public void copyAttributes(@NonNull Habit model)
    {
        this.name = model.name;
        this.description = model.description;
        this.freqNum = model.freqNum;
        this.freqDen = model.freqDen;
        this.color = model.color;
        this.position = model.position;
        this.reminderHour = model.reminderHour;
        this.reminderMin = model.reminderMin;
        this.reminderDays = model.reminderDays;
        this.highlight = model.highlight;
        this.archived = model.archived;
    }