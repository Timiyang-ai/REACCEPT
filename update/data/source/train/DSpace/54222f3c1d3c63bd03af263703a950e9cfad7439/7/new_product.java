@Override
    public boolean supports(String identifier)
    {
        try {
            doiService.formatIdentifier(identifier);
        } catch (IdentifierException e) {
            return false;
        } catch (IllegalArgumentException e)
        {
            return false;
        }
        return true;
    }