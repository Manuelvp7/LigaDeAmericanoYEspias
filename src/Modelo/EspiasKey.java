/*
 * This java source file is generated by DAO4J v1.19
 * Generated on Mon Apr 23 22:57:05 CDT 2018
 * For more information, please contact b-i-d@163.com
 * Please check http://sourceforge.net/projects/dao4j/ for the latest version.
 */

package Modelo;

public class EspiasKey implements java.io.Serializable, Cloneable {
    /* alias */
    protected String alias;

    /* alias */
    public String getAlias() {
        return alias;
    }

    /* alias */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /* Calculate hash code */
    public int hashCode() {
        int hashCode = 0;
        if (alias != null)  
            hashCode += alias.hashCode();
        return hashCode;
    }
    
    /* Indicates whether some other object is "equal to" this one. */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || !(obj instanceof EspiasKey))
            return false;

        EspiasKey key = (EspiasKey) obj;

        if (this.alias == null) {
            if (key.alias != null)
                return false;
        }
        else if (!this.alias.equals(key.alias)) 
            return false;

        return true;
    }

    /* Creates and returns a copy of this object. */
    public Object clone()
    {
        EspiasKey key = new EspiasKey();
        key.alias = this.alias;
        return key;
    }

    /* Returns a string representation of the object. */
    public String toString() {
        String sep = "\r\n";
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName()).append(sep);
        sb.append("[").append("alias").append(" = ").append(alias).append("]");
        return sb.toString();
    }
}