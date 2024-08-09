public O remove(String key)
    {
        if (key==null)
            throw new IllegalArgumentException();
        
        Node<O> node = _root;
        int ni=-1;

        // look for best match
    charLoop:
        for (int i=0;i<key.length();i++)
        {
            char c=key.charAt(i);

            // Advance node
            if (ni==-1)
            {
                ni=0;
                node=(node._children==null)?null:node._children[c%__HASH_WIDTH];
            }
            
            // While we have a node to try
            while (node!=null) 
            {
                // If it is a matching node, goto next char
                if (node._char[ni]==c || _ignoreCase&&node._ochar[ni]==c)
                {
                    ni++;
                    if (ni==node._char.length)
                        ni=-1;
                    continue charLoop;
                }

                // No char match, so if mid node then no match at all.
                if (ni>0) return null;

                // try next in chain
                node=node._next;         
            }
            return null;
        }

        if (ni>0) return null;
        if (node!=null && node._key==null)
            return null;
        
        O old = node._value;
        _entrySet.remove(node);
        node._value=null;
        node._key=null;
        
        return old; 
    }