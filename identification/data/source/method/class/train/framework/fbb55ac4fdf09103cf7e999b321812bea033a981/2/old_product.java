public void refresh(T data) {
        if (updatedData.isEmpty()) {
            markAsDirty();
        }

        updatedData.add(data);
    }