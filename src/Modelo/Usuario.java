/*
 * This java source file is generated by DAO4J v1.19
 * Generated on Mon Apr 23 23:08:23 CDT 2018
 * For more information, please contact b-i-d@163.com
 * Please check http://sourceforge.net/projects/dao4j/ for the latest version.
 */

package Modelo;

/*
 * For Table usuario
 */
public class Usuario implements java.io.Serializable, Cloneable {
    private UsuarioKey _key = new UsuarioKey();

    /* nombreUsuario, PK */
    protected String nombreusuario;

    /* contrasena */
    protected String contrasena;

    /* categoriaUsuario */
    protected String categoriausuario;

    /* Return the key object. */
    public UsuarioKey getKeyObject() {
        return _key;
    }

    /* nombreUsuario, PK */
    public String getNombreusuario() {
        return nombreusuario;
    }

    /* nombreUsuario, PK */
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
        _key.setNombreusuario(nombreusuario);
    }

    /* contrasena */
    public String getContrasena() {
        return contrasena;
    }

    /* contrasena */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /* categoriaUsuario */
    public String getCategoriausuario() {
        return categoriausuario;
    }

    /* categoriaUsuario */
    public void setCategoriausuario(String categoriausuario) {
        this.categoriausuario = categoriausuario;
    }

    /* Indicates whether some other object is "equal to" this one. */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || !(obj instanceof Usuario))
            return false;

        Usuario bean = (Usuario) obj;

        if (this.nombreusuario == null) {
            if (bean.nombreusuario != null)
                return false;
        }
        else if (!this.nombreusuario.equals(bean.nombreusuario)) 
            return false;

        if (this.contrasena == null) {
            if (bean.contrasena != null)
                return false;
        }
        else if (!this.contrasena.equals(bean.contrasena)) 
            return false;

        if (this.categoriausuario == null) {
            if (bean.categoriausuario != null)
                return false;
        }
        else if (!this.categoriausuario.equals(bean.categoriausuario)) 
            return false;

        return true;
    }

    /* Creates and returns a copy of this object. */
    public Object clone()
    {
        Usuario bean = new Usuario();
        bean.nombreusuario = this.nombreusuario;
        bean.contrasena = this.contrasena;
        bean.categoriausuario = this.categoriausuario;
        return bean;
    }

    /* Returns a string representation of the object. */
    public String toString() {
        String sep = "\r\n";
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName()).append(sep);
        sb.append("[").append("nombreusuario").append(" = ").append(nombreusuario).append("]").append(sep);
        sb.append("[").append("contrasena").append(" = ").append(contrasena).append("]").append(sep);
        sb.append("[").append("categoriausuario").append(" = ").append(categoriausuario).append("]").append(sep);
        return sb.toString();
    }
}