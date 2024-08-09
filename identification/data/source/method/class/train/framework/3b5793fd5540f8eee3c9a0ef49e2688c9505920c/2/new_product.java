public void replaceComponent(Component oldComponent, Component newComponent) {

        if (selected == oldComponent) {
            // keep selection w/o selectedTabChange event
            selected = newComponent;
        }

        Tab newTab = tabs.get(newComponent);
        Tab oldTab = tabs.get(oldComponent);

        // Gets the captions
        String oldCaption = null;
        Resource oldIcon = null;
        String newCaption = null;
        Resource newIcon = null;

        if (oldTab != null) {
            oldCaption = oldTab.getCaption();
            oldIcon = oldTab.getIcon();
        }

        if (newTab != null) {
            newCaption = newTab.getCaption();
            newIcon = newTab.getIcon();
        } else {
            newCaption = newComponent.getCaption();
            newIcon = newComponent.getIcon();
        }

        // Gets the locations
        int oldLocation = -1;
        int newLocation = -1;
        int location = 0;
        for (final Iterator i = components.iterator(); i.hasNext();) {
            final Component component = (Component) i.next();

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
            keyMapper.remove(oldComponent);
            newTab = addTab(newComponent);
            components.remove(newComponent);
            components.add(oldLocation, newComponent);
            newTab.setCaption(oldCaption);
            newTab.setIcon(oldIcon);
        } else {
            if (oldLocation > newLocation) {
                components.remove(oldComponent);
                components.add(newLocation, oldComponent);
                components.remove(newComponent);
                components.add(oldLocation, newComponent);
            } else {
                components.remove(newComponent);
                components.add(oldLocation, newComponent);
                components.remove(oldComponent);
                components.add(newLocation, oldComponent);
            }

            if (newTab != null) {
                // This should always be true
                newTab.setCaption(oldCaption);
                newTab.setIcon(oldIcon);
            }
            if (oldTab != null) {
                // This should always be true
                oldTab.setCaption(newCaption);
                oldTab.setIcon(newIcon);
            }

            requestRepaint();
        }

    }