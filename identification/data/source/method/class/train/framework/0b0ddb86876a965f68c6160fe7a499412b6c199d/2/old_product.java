protected void focus() {
        if (this instanceof Focusable) {
            final VaadinSession app = getApplication();
            if (app != null) {
                getUI().setFocusedComponent((Focusable) this);
                delayedFocus = false;
            } else {
                delayedFocus = true;
            }
        }
    }