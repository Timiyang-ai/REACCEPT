@Override
    public View getView(MetaClass metaClass, String name) {
        Preconditions.checkNotNullArgument(metaClass, "MetaClass is null");

        View view = findView(metaClass, name);

        if (view == null)
            throw new ViewNotFoundException(String.format("View %s/%s not found", metaClass.getName(), name));
        return view;
    }