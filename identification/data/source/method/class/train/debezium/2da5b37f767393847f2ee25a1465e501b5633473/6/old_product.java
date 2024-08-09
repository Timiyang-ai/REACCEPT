public Map<String, Object> offset(int eventRowNumber) {
        setRowInEvent(eventRowNumber);
        return offset();
    }