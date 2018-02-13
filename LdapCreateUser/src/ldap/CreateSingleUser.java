package ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class CreateSingleUser {
 
        public static void main(String[] args) {
        	
            Hashtable<String, String> env = new Hashtable<String, String>();
            String url = ""; //url ldap
            String rootdn = "cn=admin,dc=ldap,dc=com";
            String rootpass = "";
 
 
             //Properties env = new Properties();
 
            env.put( Context.INITIAL_CONTEXT_FACTORY,
                     "com.sun.jndi.ldap.LdapCtxFactory" );
            env.put( Context.PROVIDER_URL, url );
            env.put( Context.SECURITY_PRINCIPAL, rootdn );
            env.put( Context.SECURITY_CREDENTIALS, rootpass );
          
 
       // entry's DN
        String entryDN = "cn=Profesional1,cn=users-group,ou=users,dc=ldap,dc=com";
         
        // entry's attributes
 
        Attribute cn = new BasicAttribute("cn", "Profesional1");
        Attribute sn = new BasicAttribute("sn", "Profesional1");
        Attribute mail = new BasicAttribute("mail", "newuser@foo.com");
        Attribute phone = new BasicAttribute("telephoneNumber", "+1 222 3334444");
        Attribute uid = new BasicAttribute("uid", "TestUser3");
        Attribute uidN = new BasicAttribute("uidNumber", "1005");
        Attribute gidN = new BasicAttribute("gidNumber", "500");
        Attribute homeD = new BasicAttribute("homeDirectory", "/home/users/profesional1");
        Attribute pwd = new BasicAttribute("userPassword", "profesional");
        Attribute oc = new BasicAttribute("objectClass");
            
        oc.add("top");
       // oc.add("person");
       // oc.add("organizationalPerson");
        oc.add("posixAccount");
        oc.add("inetOrgPerson");
        DirContext ctx = null;
 
        try {
            // get a handle to an Initial DirContext
            ctx = new InitialDirContext(env);
 
            // build the entry
            Attributes entry = new BasicAttributes();
            entry.put(cn);
            entry.put(sn);
            entry.put(mail);
            entry.put(phone);
            entry.put(uid);
            entry.put(uidN);
            entry.put(gidN);
            entry.put(homeD);
            entry.put(pwd);
            entry.put(oc);
            
            // Add the entry
            
            ctx.createSubcontext(entryDN, entry);
            System.out.println( "AddUser: added entry " + entryDN + ".");
 
        } catch (NamingException e) {
            System.err.println("AddUser: error adding entry." + e);
        }
     }
}
