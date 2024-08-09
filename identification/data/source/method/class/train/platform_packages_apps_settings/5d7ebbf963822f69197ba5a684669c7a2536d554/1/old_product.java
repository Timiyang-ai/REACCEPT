public void onActionClick(long id) {
        getController(id).onActionClick();
        onConditionChanged();
    }