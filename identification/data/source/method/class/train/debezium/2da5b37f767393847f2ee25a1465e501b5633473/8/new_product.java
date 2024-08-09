public Map<String, ?> offsetForRow(int eventRowNumber, int totalNumberOfRows) {
        if (eventRowNumber < (totalNumberOfRows - 1)) {
            // This is not the last row, so our offset should record the next row to be used ...
            this.lastEventRowNumber = eventRowNumber;
            this.nextEventRowNumber = eventRowNumber + 1;
            // so write out the offset with the position of this event
            return offsetUsingPosition(lastBinlogPosition);
        }
        // This is the last row, so write out the offset that has the position of the next event ...
        this.lastEventRowNumber = this.nextEventRowNumber;
        this.nextEventRowNumber = 0;
        return offsetUsingPosition(nextBinlogPosition);
    }