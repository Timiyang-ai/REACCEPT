void setName(String name) throws SQLException
    {
        if(!StringUtils.equals(this.name, name) && !isPermanent()) {
            this.name = name;
            groupsChanged = true;
        }
    }