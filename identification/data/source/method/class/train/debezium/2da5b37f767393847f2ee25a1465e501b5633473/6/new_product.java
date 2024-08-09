public Map<String, ?> offset(int eventRowNumber) {
        setRowInEvent(eventRowNumber);
        return offset();
    }