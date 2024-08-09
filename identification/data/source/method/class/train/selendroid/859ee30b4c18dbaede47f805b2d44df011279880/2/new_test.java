    private static List<View> searchViews(View root) {
        List<AndroidElement> elements = AbstractNativeElementContext.searchViews(
                    mockContext(),
                    root,
                    FIND_MATCH,
                    false);
        List<View> foundViews = new ArrayList<View>();
        for (AndroidElement element : elements) {
            foundViews.add(((AndroidNativeElement) element).getView());
        }
        return foundViews;
    }