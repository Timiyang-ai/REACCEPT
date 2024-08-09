@Override public native V remove(Object key) /*-[
      if (key == nil) {
        JavaUtilHashMap_HashMapEntry *e = self->entryForNullKey_;
        if (e == nil) {
          return nil;
        }
        JavaUtilHashMap_set_entryForNullKey_(self, nil);
        modCount_++;
        size__--;
        [self postRemoveWithJavaUtilHashMap_HashMapEntry:e];
        return e->value_;
      }
      jint hash_ = JavaUtilCollections_secondaryHashWithId_(key);
      IOSObjectArray *tab = table_;
      jint index = hash_ & (tab->size_ - 1);
      for (JavaUtilHashMap_HashMapEntry *e = tab->buffer_[index], *prev = nil; e != nil;
          prev = e, e = e->next_) {
        if (e->hash__ == hash_ && [key isEqual:e->key_]) {
          if (prev == nil) {
            tab->buffer_[index] = e->next_;
          } else {
            prev->next_ = e->next_;
          }
          e->next_ = nil;  // Balance the missing retain on e.next above.
          [e autorelease];  // Balance the missing release on e above.
          modCount_++;
          size__--;
          [self postRemoveWithJavaUtilHashMap_HashMapEntry:e];
          return e->value_;
        }
      }
      return nil;
    ]-*/;