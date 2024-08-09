protected void focus() {
        if (this instanceof Focusable) {
            final Application app = getApplication();
            if (app != null) {
                getRoot().setFocusedComponent((Focusable) this);
                delayedFocus = false;
            } else {
                delayedFocus = true;
            }
        }
    }