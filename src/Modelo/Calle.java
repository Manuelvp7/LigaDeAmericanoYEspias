/*
 * This java source file is generated by DAO4J v1.19
 * Generated on Sun Feb 25 05:11:22 CST 2018
 * For more information, please contact b-i-d@163.com
 * Please check http://sourceforge.net/projects/dao4j/ for the latest version.
 */

package Modelo;

/*
 * For Table Calle
 */
public class Calle implements java.io.Serializable, Cloneable {
    private CalleKey _key = new CalleKey();

    /* nombreColonia */
    protected String nombrecolonia;

    /* nombreCalle, PK */
    protected String nombrecalle;

    /* Return the key object. */
    public CalleKey getKeyObject() {
        return _key;
    }

    /* nombreColonia */
    public String getNombrecolonia() {
        return nombrecolonia;
    }

    /* nombreColonia */
    public void setNombrecolonia(String nombrecolonia) {
        this.nombrecolonia = nombrecolonia;
    }

    /* nombreCalle, PK */
    public String getNombrecalle() {
        return nombrecalle;
    }

    /* nombreCalle, PK */
    public void setNombrecalle(String nombrecalle) {
        this.nombrecalle = nombrecalle;
        _key.setNombrecalle(nombrecalle);
    }

    /* Indicates whether some other object is "equal to" this one. */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || !(obj instanceof Calle))
            return false;

        Calle bean = (Calle) obj;

        if (this.nombrecolonia == null) {
            if (bean.nombrecolonia != null)
                return false;
        }
        else if (!this.nombrecolonia.equals(bean.nombrecolonia)) 
            return false;

        if (this.nombrecalle == null) {
            if (bean.nombrecalle != null)
                return false;
        }
        else if (!this.nombrecalle.equals(bean.nombrecalle)) 
            return false;

        return true;
    }

    /* Creates and returns a copy of this object. */
    public Object clone()
    {
        Calle bean = new Calle();
        bean.nombrecolonia = this.nombrecolonia;
        bean.nombrecalle = this.nombrecalle;
        return bean;
    }

    /* Returns a string representation of the object. */
    public String toString() {
        String sep = "\r\n";
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName()).append(sep);
        sb.append("[").append("nombrecolonia").append(" = ").append(nombrecolonia).append("]").append(sep);
        sb.append("[").append("nombrecalle").append(" = ").append(nombrecalle).append("]").append(sep);
        return sb.toString();
    }
}