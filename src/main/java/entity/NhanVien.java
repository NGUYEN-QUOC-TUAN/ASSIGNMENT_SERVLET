package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "TenDem")
    private String tenDem;
    @Column(name = "Ho")
    private String ho;
    @Column(name = "GioiTinh")
    private String gioiTinh;
    @Column(name = "NgaySinh")
    private Date ngaySinh;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "Sdt")
    private String soDienThoai;
    @Column(name = "MatKhau")
    private String matKhau;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCH")
    private CuaHang cuaHang;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCV")
    private ChucVu chucVu;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idGuiBC")
    private NhanVien guiBC;
    @Column(name = "TrangThai")
    private int trangThai;

}
