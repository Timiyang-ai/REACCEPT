protected void focus() {
        if (this instanceof Focusable) {
            final VaadinSession app = getSession();
            if (app != null) {
                getUI().setFocusedComponent((Focusable) this);
                delayedFocus = false;
            } else {
                delayedFocus = true;
            }
        }
    }