public void validate(TypeModel model )
    {
        String errors="";
        errors += validateFields(model.fields);
        errors += validateMethods(model.methods);

        if ( !errors.isEmpty() )
        {
            throw new TypeModelValidatorException("Ambiguous field(s) or method(s).\n" + errors);
        }
    }