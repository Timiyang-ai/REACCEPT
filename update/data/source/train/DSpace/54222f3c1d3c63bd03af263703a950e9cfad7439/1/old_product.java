@Override
    public boolean equals(Object obj)
    {
        try
        {
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            final ResourcePolicy other = (ResourcePolicy) obj;         
            if (this.getAction() != other.getAction())
            {
                return false;
            }
            if (this.getEPerson() != other.getEPerson() && (this.getEPerson() == null || !this.getEPerson().equals(other.getEPerson())))
            {
                return false;
            }
            if (this.getGroup() != other.getGroup() && (this.getGroup() == null || !this.getGroup().equals(other.getGroup())))
            {
                return false;
            }
            if (this.getStartDate() != other.getStartDate() && (this.getStartDate() == null || !this.getStartDate().equals(other.getStartDate())))
            {
                return false;
            }
            if (this.getEndDate() != other.getEndDate() && (this.getEndDate() == null || !this.getEndDate().equals(other.getEndDate())))
            {
                return false;
            }    
            return true;
        }
        catch (SQLException ex)
        {
            log.error("Error while comparing ResourcePolicy objects", ex);
        }
        return false;
    }