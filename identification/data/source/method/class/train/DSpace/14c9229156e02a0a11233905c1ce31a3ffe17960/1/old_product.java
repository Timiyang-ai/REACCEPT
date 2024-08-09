void setName(Context context, String name) throws SQLException
    {
        if(!StringUtils.equals(this.name, name)) {
            this.name = name;
            groupsChanged = true;
        }
    }