public void replaceComponent(Component oldComponent, Component newComponent) {

        // Gets the captions
        String oldCaption = getTabCaption(oldComponent);
        Resource oldIcon = getTabIcon(oldComponent);
        String newCaption = getTabCaption(newComponent);
        Resource newIcon = getTabIcon(newComponent);

        // Gets the locations
        int oldLocation = -1;
        int newLocation = -1;
        int location = 0;
        for (Iterator i = tabs.iterator(); i.hasNext();) {
            Component component = (Component) i.next();

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
            addComponent(newComponent);
            tabs.remove(newComponent);
            tabs.add(oldLocation, newComponent);
            setTabCaption(newComponent, oldCaption);
            setTabIcon(newComponent, oldIcon);
        } else {
            if (oldLocation > newLocation) {
                tabs.remove(oldComponent);
                tabs.add(newLocation, oldComponent);
                tabs.remove(newComponent);
                tabs.add(oldLocation, newComponent);
            } else {
                tabs.remove(newComponent);
                tabs.add(oldLocation, newComponent);
                tabs.remove(oldComponent);
                tabs.add(newLocation, oldComponent);
            }
            setTabCaption(newComponent, oldCaption);
            setTabIcon(newComponent, oldIcon);
            setTabCaption(oldComponent, newCaption);
            setTabIcon(oldComponent, newIcon);

            requestRepaint();
        }
    }