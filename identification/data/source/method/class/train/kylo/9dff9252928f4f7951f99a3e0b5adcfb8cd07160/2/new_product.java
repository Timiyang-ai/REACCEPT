@Override
    public SecurityRole createRole(String entityName, String roleName, String title, String descr) {
        Session session = JcrMetadataAccess.getActiveSession();
        Path rolePath = SecurityPaths.rolePath(entityName, roleName);
        
        if (JcrUtil.hasNode(session, rolePath.toString())) {
            throw new SecurityRoleAlreadyExistsException(entityName, roleName);
        } else {
            if (! JcrUtil.hasNode(session, rolePath.getParent().toString())) {
                // TODO create new exception
                throw new RoleNotFoundException("No role entity found with the specified name: " + entityName);
            }
            
            Node entityNode = JcrUtil.getNode(session, rolePath.getParent().toString());
            JcrSecurityRole role = JcrUtil.getOrCreateNode(entityNode, roleName, JcrSecurityRole.NODE_TYPE, JcrSecurityRole.class);
            role.setTitle(title == null ? roleName : title);
            role.setDescription(descr);
            return role;
        }
    }