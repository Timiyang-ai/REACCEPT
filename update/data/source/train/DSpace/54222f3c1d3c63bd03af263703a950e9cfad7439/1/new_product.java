@Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
        if (getClass() != objClass)
        {
            return false;
        }
        final ResourcePolicy other = (ResourcePolicy) obj;
        if (getAction() != other.getAction())
        {
            return false;
        }
        if (!ObjectUtils.equals(getEPerson(), other.getEPerson()))
        {
            return false;
        }
        if (!ObjectUtils.equals(getGroup(), other.getGroup()))
        {
            return false;
        }
        if (!ObjectUtils.equals(getStartDate(), other.getStartDate()))
        {
            return false;
        }
        if (!ObjectUtils.equals(getEndDate(), other.getEndDate()))
        {
            return false;
        }
        return true;
    }