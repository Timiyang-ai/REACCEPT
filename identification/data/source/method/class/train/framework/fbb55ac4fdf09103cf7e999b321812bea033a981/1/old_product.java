public void refresh(T data) {
        if (!handler.getActiveData().contains(data)) {
            // Item is not currently available at the client-side
            return;
        }

        if (updatedData.isEmpty()) {
            markAsDirty();
        }

        updatedData.add(data);
    }