package controller;

import entity.ChiTietSanPham;
import entity.DongSanPham;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ChiTietSanPhamService;
import service.DongSanPhamService;
import service.MauSacService;
import service.NhaSanXuatService;
import service.SanPhamService;
import service.impl.ChiTietSanPhamServiceImpl;
import service.impl.DongSanPhamServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhaSanXuatServiceImpl;
import service.impl.SanPhamServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChiTietSanPhamController", value = {
        "/chi-tiet-san-pham/hien-thi",
        "/chi-tiet-san-pham/detail",
        "/chi-tiet-san-pham/remove",
        "/chi-tiet-san-pham/view-add",
        "/chi-tiet-san-pham/view-update",
        "/chi-tiet-san-pham/add",
        "/chi-tiet-san-pham/update"
})
public class ChiTietSanPhamController extends HttpServlet {
    private List<ChiTietSanPham> listCTSanPhams = new ArrayList<>();
    private List<SanPham> listSanPhams = new ArrayList<>();
    private List<NhaSanXuat> listNhaSanXuats = new ArrayList<>();
    private List<MauSac> listMauSacs = new ArrayList<>();
    private List<DongSanPham> listDongSanPhams = new ArrayList<>();
    private ChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamServiceImpl();
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private NhaSanXuatService nhaSanXuatService = new NhaSanXuatServiceImpl();
    private MauSacService mauSacService = new MauSacServiceImpl();
    private DongSanPhamService dongSanPhamService = new DongSanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiCTSanPham(request, response);
        } else if (uri.contains("detail")) {
            this.hienThiCTSanPham(request, response);
        } else if (uri.contains("remove")) {
            this.removeCTSanPham(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddSanPham(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateSanPham(request, response);
        } else {
            this.hienThiCTSanPham(request, response);
        }
    }

    private void viewUpdateSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void removeCTSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = chiTietSanPhamService.deleteChiTietSanPham(chiTietSanPhamService.getOneChiTietSanPham(request.getParameter("id")));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }

    private void viewAddSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listSanPhams", sanPhamService.getAllSanPhams());
        request.setAttribute("listNhaSanXuats", nhaSanXuatService.getAllNhaSanXuats());
        request.setAttribute("listMauSacs", mauSacService.getAllMauSacs());
        request.setAttribute("listDongSanPhams", dongSanPhamService.getAllDongSanPham());
        request.setAttribute("chiTietSP", chiTietSanPhamService.getOneChiTietSanPham(request.getParameter("id")));
        request.setAttribute("listCTSanPhams", chiTietSanPhamService.getAllChiTietSanPhams());
        if (check == 1) {
            request.getRequestDispatcher("/views/view-chi-tiet-san-pham/chi-tiet-san-pham.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-chi-tiet-san-pham/add-chi-tiet-san-pham.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-chi-tiet-san-pham/update-chi-tiet-san-pham.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-chi-tiet-san-pham/chi-tiet-san-pham.jsp").forward(request, response);
        }
    }

    private void hienThiCTSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG CHI TIẾT SẢN PHẨM");
        chuyenTrang(request, response, 1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = chiTietSanPhamService.addChiTietSanPham(getFormChiTietSP(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = chiTietSanPhamService.updateChiTietSanPham(getFormChiTietSP(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }

    private ChiTietSanPham getFormChiTietSP(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String sanPham = request.getParameter("sanPham");
        String nhaSanXuat = request.getParameter("nhaSanXuat");
        String mauSac = request.getParameter("mauSac");
        String dongSP = request.getParameter("dongSanPham");
        String moTa = request.getParameter("moTa");
        String namBaoHanh = request.getParameter("namBaoHanh");
        String soLuongTon = request.getParameter("soLuongTon");
        String giaNhap = request.getParameter("giaNhap");
        String giaBan = request.getParameter("giaBan");
        ChiTietSanPham chiTietSanPham = null;
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        SanPham sp = sanPhamService.getOneSanPham(sanPham);
        NhaSanXuat nsx = nhaSanXuatService.getOneNhaSanXuat(nhaSanXuat);
        MauSac ms = mauSacService.getOneMauSac(mauSac);
        DongSanPham dsp = dongSanPhamService.getOneDongSanPham(dongSP);
        String eror = "* TRỐNG *";
        if (check == true) {
            if (sanPham.equals("null")) {
                request.setAttribute("sanPhamTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (nhaSanXuat.equals("null")) {
                request.setAttribute("nhaSanXuatTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (mauSac.equals("null")) {
                request.setAttribute("mauSacTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (dongSP.equals("null")) {
                request.setAttribute("dongSanPhamTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (moTa.isBlank()) {
                request.setAttribute("moTaTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (namBaoHanh.isBlank() || Integer.parseInt(namBaoHanh) > 2050 || Integer.parseInt(namBaoHanh) < year) {
                request.setAttribute("namBaoHanhTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (soLuongTon.isBlank() || soLuongTon.length() >= 8) {
                request.setAttribute("soLuongTonTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (giaNhap.isBlank() || Integer.parseInt(giaNhap) > Integer.parseInt(giaBan)) {
                request.setAttribute("giaNhapTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (giaBan.isBlank() || Integer.parseInt(giaBan) < Integer.parseInt(giaNhap)) {
                request.setAttribute("giaBanTrong", eror);
                chuyenTrang(request, response, 2);
            } else {
                chiTietSanPham = new ChiTietSanPham(null, sp, nsx, ms, dsp, Integer.parseInt(namBaoHanh), moTa, Integer.parseInt(soLuongTon), BigDecimal.valueOf(Long.valueOf(giaNhap)), BigDecimal.valueOf(Long.valueOf(giaBan)));
            }
        } else {
            if (sanPham.equals("null")) {
                request.setAttribute("sanPhamTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (nhaSanXuat.equals("null")) {
                request.setAttribute("nhaSanXuatTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (mauSac.equals("null")) {
                request.setAttribute("mauSacTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (dongSP.equals("null")) {
                request.setAttribute("dongSanPhamTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (moTa.isBlank()) {
                request.setAttribute("moTaTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (namBaoHanh.isBlank() || Integer.parseInt(namBaoHanh) > 2050 || Integer.parseInt(namBaoHanh) < year) {
                request.setAttribute("namBaoHanhTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (soLuongTon.isBlank()) {
                request.setAttribute("soLuongTonTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (giaNhap.isBlank() || Integer.parseInt(giaNhap) > Integer.parseInt(giaBan)) {
                request.setAttribute("giaNhapTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (giaBan.isBlank() || Integer.parseInt(giaBan) < Integer.parseInt(giaNhap)) {
                request.setAttribute("giaBanTrong", eror);
                chuyenTrang(request, response, 3);
            } else {
                chiTietSanPham = new ChiTietSanPham(id, sp, nsx, ms, dsp, Integer.parseInt(namBaoHanh), moTa, Integer.parseInt(soLuongTon), BigDecimal.valueOf(Long.valueOf(giaNhap)), BigDecimal.valueOf(Long.valueOf(giaBan)));
            }
        }
        return chiTietSanPham;
    }
}
