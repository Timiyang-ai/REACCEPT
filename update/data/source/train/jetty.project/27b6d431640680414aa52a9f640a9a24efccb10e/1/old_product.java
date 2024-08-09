public static String decodePath(String path)
    {
        if (path==null)
            return null;
        char[] chars=null;
        int n=0;
        byte[] bytes=null;
        int b=0;
        
        int len=path.length();
        
        for (int i=0;i<len;i++)
        {
            char c = path.charAt(i);

            if (c=='%' && (i+2)<len)
            {
                if (chars==null)
                {
                    chars=new char[len];
                    bytes=new byte[len];
                    path.getChars(0,i,chars,0);
                }
                bytes[b++]=(byte)(0xff&TypeUtil.parseInt(path,i+1,2,16));
                i+=2;
                continue;
            }
            else if (bytes==null)
            {
                n++;
                continue;
            }
            
            if (b>0)
            {
                String s;
                try
                {
                    s=new String(bytes,0,b,__CHARSET);
                }
                catch (UnsupportedEncodingException e)
                {       
                    s=new String(bytes,0,b);
                }
                s.getChars(0,s.length(),chars,n);
                n+=s.length();
                b=0;
            }
            
            chars[n++]=c;
        }

        if (chars==null)
            return path;

        if (b>0)
        {
            String s;
            try
            {
                s=new String(bytes,0,b,__CHARSET);
            }
            catch (UnsupportedEncodingException e)
            {       
                s=new String(bytes,0,b);
            }
            s.getChars(0,s.length(),chars,n);
            n+=s.length();
        }
        
        return new String(chars,0,n);
    }