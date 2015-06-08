/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MARIO
 */
@Entity
@Table(name = "data_usuarios")
@NamedQueries({
    @NamedQuery(name = "DataUsuarios.findAll", query = "SELECT d FROM DataUsuarios d")})
public class DataUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idlogin")
    private int idlogin;
    @Basic(optional = false)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Basic(optional = false)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @Lob
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Lob
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "telefono")
    private String telefono;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fechadata")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadata;

    public DataUsuarios() {
    }

    public DataUsuarios(Integer id) {
        this.id = id;
    }

    public DataUsuarios(Integer id, int idlogin, String tipodocumento, String documento, String nombres, String apellidos, int estado, Date fechadata) {
        this.id = id;
        this.idlogin = idlogin;
        this.tipodocumento = tipodocumento;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estado = estado;
        this.fechadata = fechadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(int idlogin) {
        this.idlogin = idlogin;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechadata() {
        return fechadata;
    }

    public void setFechadata(Date fechadata) {
        this.fechadata = fechadata;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataUsuarios)) {
            return false;
        }
        DataUsuarios other = (DataUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DataUsuarios[ id=" + id + " ]";
    }
    
}
