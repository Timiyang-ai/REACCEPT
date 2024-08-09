private boolean isDuplicateOfLastAuditEvent(AuditEvent.AuditEventType eventType) {
        return (eventType.equals(LOCATION_PROVIDERS_ENABLED) || eventType.equals(LOCATION_PROVIDERS_DISABLED))
                && !auditEvents.isEmpty() && eventType.equals(auditEvents.get(auditEvents.size() - 1).auditEventType);
    }