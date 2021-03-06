    /*
 * This java source file is generated by DAO4J v1.19
 * Generated on Mon Apr 23 22:57:05 CDT 2018
 * For more information, please contact b-i-d@163.com
 * Please check http://sourceforge.net/projects/dao4j/ for the latest version.
 */

package Modelo;

/*
 * For Table espias
 */
public class Espias implements java.io.Serializable, Cloneable {
    private EspiasKey _key = new EspiasKey();

    /* alias, PK */
    protected String alias;

    /* genero */
    protected String genero;

    /* estado */
    protected byte estado;

    /* Return the key object. */
    public EspiasKey getKeyObject() {
        return _key;
    }

    /* alias, PK */
    public String getAlias() {
        return alias;
    }

    /* alias, PK */
    public void setAlias(String alias) {
        this.alias = alias;
        _key.setAlias(alias);
    }

    /* genero */
    public String getGenero() {
        return genero;
    }

    /* genero */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /* estado */
    public short getEstado() {
        return estado;
    }

    /* estado */
    public void setEstado(byte estado) {
        this.estado = estado;
    }

    /* Indicates whether some other object is "equal to" this one. */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || !(obj instanceof Espias))
            return false;

        Espias bean = (Espias) obj;

        if (this.alias == null) {
            if (bean.alias != null)
                return false;
        }
        else if (!this.alias.equals(bean.alias)) 
            return false;

        if (this.genero == null) {
            if (bean.genero != null)
                return false;
        }
        else if (!this.genero.equals(bean.genero)) 
            return false;

        if (this.estado != bean.estado)
            return false;

        return true;
    }

    /* Creates and returns a copy of this object. */
    public Object clone()
    {
        Espias bean = new Espias();
        bean.alias = this.alias;
        bean.genero = this.genero;
        bean.estado = this.estado;
        return bean;
    }

    /* Returns a string representation of the object. */
    public String toString() {
        String sep = "\r\n";
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName()).append(sep);
        sb.append("[").append("alias").append(" = ").append(alias).append("]").append(sep);
        sb.append("[").append("genero").append(" = ").append(genero).append("]").append(sep);
        sb.append("[").append("estado").append(" = ").append(estado).append("]").append(sep);
        return sb.toString();
    }
}