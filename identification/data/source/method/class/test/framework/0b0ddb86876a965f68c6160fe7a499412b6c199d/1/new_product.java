protected void focus() {
        if (this instanceof Focusable) {
            final Application app = getApplication();
            if (app != null) {
                getUI().setFocusedComponent((Focusable) this);
                delayedFocus = false;
            } else {
                delayedFocus = true;
            }
        }
    }