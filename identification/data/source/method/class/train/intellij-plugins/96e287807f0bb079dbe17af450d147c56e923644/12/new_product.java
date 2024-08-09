@Override
    public int compareTo(Object o) {
        return getTemplate().compareTo(((TemplateElement) o).getTemplate());
    }