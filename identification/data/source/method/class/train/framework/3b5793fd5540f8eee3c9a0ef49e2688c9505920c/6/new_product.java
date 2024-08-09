public void replaceComponent(Component oldComponent, Component newComponent) {

        if (selected == oldComponent) {
            // keep selection w/o selectedTabChange event
            selected = newComponent;
        }

        Tab newTab = tabs.get(newComponent);
        Tab oldTab = tabs.get(oldComponent);

        // Gets the locations
        int oldLocation = -1;
        int newLocation = -1;
        int location = 0;
        for (final Iterator<Component> i = components.iterator(); i.hasNext();) {
            final Component component = i.next();

            if (component == oldComponent) {
                oldLocation = location;
            }
            if (component == newComponent) {
                newLocation = location;
            }

            location++;
        }

        if (oldLocation == -1) {
            addComponent(newComponent);
        } else if (newLocation == -1) {
            removeComponent(oldComponent);
            newTab = addTab(newComponent, oldLocation);
            // Copy all relevant metadata to the new tab (#8793)
            // TODO Should reuse the old tab instance instead?
            copyTabMetadata(oldTab, newTab);
        } else {
            components.set(oldLocation, newComponent);
            components.set(newLocation, oldComponent);

            // Tab associations are not changed, but metadata is swapped between
            // the instances
            // TODO Should reassociate the instances instead?
            Tab tmp = new TabSheetTabImpl(null, null);
            copyTabMetadata(newTab, tmp);
            copyTabMetadata(oldTab, newTab);
            copyTabMetadata(tmp, oldTab);

            requestRepaint();
        }

    }