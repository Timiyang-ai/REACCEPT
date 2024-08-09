static void rollback(final String patchId, final Collection<ContentModification> originalPatch, final Collection<ContentModification> rollbackPatch,
                         final Map<Location, ContentTaskDefinition> modifications, final ContentItemFilter filter,
                         final PatchingTaskContext.Mode mode) {

        // Process the original patch information
        final Map<Location, ContentModification> originalModifications = new HashMap<Location, ContentModification>();
        for (final ContentModification modification : originalPatch) {
            originalModifications.put(new Location(modification.getItem()), modification);
        }
        // Process the rollback information
        for (final ContentModification modification : rollbackPatch) {

            final ContentItem item = modification.getItem();
            // Skip module items when rolling back
            if (!filter.accepts(item)) {
                continue;
            }
            final Location location = new Location(item);
            final ContentModification original = originalModifications.remove(location);
            final ContentEntry contentEntry = new ContentEntry(patchId, modification);
            ContentTaskDefinition definition = modifications.get(location);
            if (definition == null) {
                definition = new ContentTaskDefinition(location, contentEntry);
                modifications.put(location, definition);
            } else {
                // TODO perhaps we don't need check that
                boolean strict = true; // Strict history checks
                if (strict) {
                    // Check if the consistency of the history
                    final ContentEntry previous = definition.getTarget();
                    final byte[] hash = previous.getItem().getContentHash();
                    if (!Arrays.equals(hash, contentEntry.getTargetHash())) {
                        throw new IllegalStateException();
                    }
                }
                //
                definition.setTarget(contentEntry);
            }
            if (original == null
                    || mode == PatchingTaskContext.Mode.ROLLBACK) {
                continue;
            }

            // Check if the current content was the original item (preserve)
            final byte[] currentContent = modification.getTargetHash();
            final byte[] originalContent = original.getItem().getContentHash();

            // TODO relax requirements for conflict resolution on rollback!
            if (!Arrays.equals(currentContent, originalContent)) {
                definition.addConflict(contentEntry);
            } else {
                // Check if backup item was the targeted one (override)
                final byte[] backupItem = item.getContentHash();
                final byte[] originalTarget = original.getTargetHash();
                //
                if (!Arrays.equals(backupItem, originalTarget)) {
                    definition.addConflict(contentEntry);
                }
            }
        }
    }