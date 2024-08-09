public void refresh(T data) {
        Objects.requireNonNull(data,
                "DataCommunicator can not refresh null object");
        Object id = getDataProvider().getId(data);

        // ActiveDataHandler has always the latest data through KeyMapper.
        Map<Object, T> activeData = getActiveDataHandler().getActiveData();

        if (activeData.containsKey(id)) {
            // Item is currently available at the client-side
            if (updatedData.isEmpty()) {
                markAsDirty();
            }

            updatedData.add(activeData.get(id));
        }
    }