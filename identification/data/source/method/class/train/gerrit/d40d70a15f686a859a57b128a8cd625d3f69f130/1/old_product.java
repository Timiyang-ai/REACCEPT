public RefUpdate.Result save(CommitBuilder cb)
      throws PGPException, IOException {
    if (toAdd.isEmpty() && toRemove.isEmpty()) {
      return RefUpdate.Result.NO_CHANGE;
    }
    if (reader == null) {
      load();
    }
    if (notes == null) {
      notes = NoteMap.newEmptyMap();
    }
    ObjectId newTip;
    try (ObjectInserter ins = repo.newObjectInserter()) {
      for (PGPPublicKeyRing keyRing : toAdd.values()) {
        saveToNotes(ins, keyRing);
      }
      for (Fingerprint fp : toRemove) {
        deleteFromNotes(ins, fp);
      }
      cb.setTreeId(notes.writeTree(ins));
      if (cb.getTreeId().equals(
          tip != null ? tip.getTree() : EMPTY_TREE)) {
        return RefUpdate.Result.NO_CHANGE;
      }

      if (tip != null) {
        cb.setParentId(tip);
      }
      if (cb.getMessage() == null) {
        int n = toAdd.size() + toRemove.size();
        cb.setMessage(
            String.format("Update %d public key%s", n, n != 1 ? "s" : ""));
      }
      newTip = ins.insert(cb);
      ins.flush();
    }

    RefUpdate ru = repo.updateRef(RefNames.REFS_GPG_KEYS);
    ru.setExpectedOldObjectId(tip);
    ru.setNewObjectId(newTip);
    ru.setRefLogIdent(cb.getCommitter());
    ru.setRefLogMessage("Store public keys", true);
    RefUpdate.Result result = ru.update();
    close();
    switch (result) {
      case FAST_FORWARD:
      case NEW:
      case NO_CHANGE:
        toAdd.clear();
        toRemove.clear();
        break;
      default:
        break;
    }
    return result;
  }