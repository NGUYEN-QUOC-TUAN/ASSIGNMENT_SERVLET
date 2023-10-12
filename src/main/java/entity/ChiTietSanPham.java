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
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idSP")
    private SanPham sanPham;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idNsx")
    private NhaSanXuat nhaSanXuat;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idMauSac")
    private MauSac mauSac;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idDongSP")
    private DongSanPham dongSanPham;
    @Column(name = "NamBH")
    private Integer namBaoHanh;
    @Column(name = "MoTa")
    private String moTa;
    @Column(name = "SoLuongTon")
    private Integer soLuongTon;
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;
    @Column(name = "GiaBan")
    private BigDecimal giaBan;
}
