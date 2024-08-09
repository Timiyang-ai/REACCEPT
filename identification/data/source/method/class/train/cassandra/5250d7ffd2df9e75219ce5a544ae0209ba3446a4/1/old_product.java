NodeBuilder update(Object key)
    {
        assert copyFrom != null;
        int copyFromKeyEnd = getKeyEnd(copyFrom);

        int i = copyFromKeyPosition;
        boolean found; // exact key match?
        boolean owns = true; // true if this node (or a child) should contain the key
        if (i == copyFromKeyEnd)
        {
            found = false;
        }
        else
        {
            // this optimisation is for the common scenario of updating an existing row with the same columns/keys
            // and simply avoids performing a binary search until we've checked the proceeding key;
            // possibly we should disable this check if we determine that it fails more than a handful of times
            // during any given builder use to get the best of both worlds
            int c = -comparator.compare(key, copyFrom[i]);
            if (c >= 0)
            {
                found = c == 0;
            }
            else
            {
                i = find(comparator, key, copyFrom, i + 1, copyFromKeyEnd);
                found = i >= 0;
                if (!found)
                    i = -i - 1;
            }
        }

        if (found)
        {
            Object prev = copyFrom[i];
            Object next = updateFunction.apply(prev, key);
            // we aren't actually replacing anything, so leave our state intact and continue
            if (prev == next)
                return null;
            key = next;
        }
        else if (i == copyFromKeyEnd && compare(comparator, key, upperBound) >= 0)
            owns = false;

        if (isLeaf(copyFrom))
        {

            if (owns)
            {
                // copy keys from the original node up to prior to the found index
                copyKeys(i);

                if (found)
                {
                    // if found, we've applied updateFunction already
                    replaceNextKey(key);
                }
                else
                {
                    // if not found, we still need to apply the update function
                    key = updateFunction.apply(key);
                    addNewKey(key); // handles splitting parent if necessary via ensureRoom
                }

                // done, so return null
                return null;
            }
            else
            {
                // we don't want to copy anything if we're ascending and haven't copied anything previously,
                // as in this case we can return the original node. Leaving buildKeyPosition as 0 indicates
                // to buildFromRange that it should return the original instead of building a new node
                if (buildKeyPosition > 0)
                    copyKeys(i);
            }

            // if we don't own it, all we need to do is ensure we've copied everything in this node
            // (which we have done, since not owning means pos >= keyEnd), ascend, and let Modifier.update
            // retry against the parent node.  The if/ascend after the else branch takes care of that.
        }
        else
        {
            // branch
            if (found)
            {
                copyKeys(i);
                replaceNextKey(key);
                copyChildren(i + 1);
                return null;
            }
            else if (owns)
            {
                copyKeys(i);
                copyChildren(i);

                // belongs to the range owned by this node, but not equal to any key in the node
                // so descend into the owning child
                Object newUpperBound = i < copyFromKeyEnd ? copyFrom[i] : upperBound;
                Object[] descendInto = (Object[]) copyFrom[copyFromKeyEnd + i];
                ensureChild().reset(descendInto, newUpperBound, updateFunction, comparator);
                return child;
            }
            else if (buildKeyPosition > 0 || buildChildPosition > 0)
            {
                // ensure we've copied all keys and children, but only if we've already copied something.
                // otherwise we want to return the original node
                copyKeys(copyFromKeyEnd);
                copyChildren(copyFromKeyEnd + 1); // since we know that there are exactly 1 more child nodes, than keys
            }
        }

        return ascend();
    }