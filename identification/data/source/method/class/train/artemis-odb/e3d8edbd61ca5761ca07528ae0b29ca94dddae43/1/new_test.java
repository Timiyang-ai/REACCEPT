    private void validate(TypeModel model) {
        new TypeModelValidator(new NullLog(),"test").validate(model);
    }