@Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{action");
        sb.append(" class:").append(className).append(" method:").append(methodName);

        sb.append(" domain:").append(domain);
        sb.append(" section:").append(section);

        if (itemClassName != null) {
            sb.append(" item:").append(itemClassName);
        }

        if (buttonClassName != null) {
            sb.append(" button:").append(buttonClassName);
        }

        sb.append("}");

        return sb.toString();
    }