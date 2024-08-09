@Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{action");

        sb.append(" domain:").append(domain);
        sb.append(" section:").append(section);

        if (className != null) {
            sb.append(" class:").append(className);
        }

        if (methodName != null) {
            sb.append(" method:").append(methodName);
        }

        if (menuName != null) {
            sb.append(" menu:").append(menuName);
        }

        if (itemClassName != null) {
            sb.append(" item:").append(itemClassName);
        }

        if (buttonClassName != null) {
            sb.append(" button:").append(buttonClassName);
        }

        sb.append("}");

        return sb.toString();
    }