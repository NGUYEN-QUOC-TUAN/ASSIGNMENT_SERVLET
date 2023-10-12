package controller;

import entity.ChucVu;
import entity.CuaHang;
import entity.NhanVien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ChucVuService;
import service.CuaHangService;
import service.NhanVienService;
import service.impl.ChuVuServiceImpl;
import service.impl.CuaHangServiceImpl;
import service.impl.NhanVienServiceImpl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NhanVienController", value = {
        "/nhan-vien/hien-thi",
        "/nhan-vien/remove",
        "/nhan-vien/tim-kiem",
        "/nhan-vien/view-add",
        "/nhan-vien/view-update",
        "/nhan-vien/add",
        "/nhan-vien/update"
})
public class NhanVienController extends HttpServlet {
    private List<NhanVien> listNhanViens = new ArrayList<>();
    private List<CuaHang> listCuaHangs = new ArrayList<>();
    private List<ChucVu> listChucVus = new ArrayList<>();
    private NhanVienService nhanVienService = new NhanVienServiceImpl();
    private CuaHangService cuaHangService = new CuaHangServiceImpl();
    private ChucVuService chucVuService = new ChuVuServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiNhanVien(request, response);
        } else if (uri.contains("remove")) {
            this.removeNhanVien(request, response);
        } else if (uri.contains("tim-kiem")) {
            this.hienThiNhanVien(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddNhanVien(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateNhanVien(request, response);
        } else {
            this.hienThiNhanVien(request, response);
        }
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void removeNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = nhanVienService.deleteNhanVien(nhanVienService.getOneNhanVien(request.getParameter("id")));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }


    private void viewUpdateNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listNhanViens", nhanVienService.getAllNhanViens());
        request.setAttribute("listCuaHangs", cuaHangService.getAllCuaHangs());
        request.setAttribute("listChucVus", chucVuService.getAllChucVus());
        request.setAttribute("nhanVien", nhanVienService.getOneNhanVien(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-nhan-vien/view-nhan-vien.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-nhan-vien/view-add-nhan-vien.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-nhan-vien/view-update-nhan-vien.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-nhan-vien/view-nhan-vien.jsp").forward(request, response);
        }
    }

    private void hienThiNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG NHÂN VIÊN");
        chuyenTrang(request, response, 1);
    }

    private NhanVien getFormNhanVien(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String tenDem = request.getParameter("tenDem");
        String ho = request.getParameter("ho");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChi = request.getParameter("diaChi");
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");
        String cuaHang = request.getParameter("cuaHang");
        String chucVu = request.getParameter("chucVu");
        String trangThai = request.getParameter("trangThai");
        CuaHang cuaHangGetOne = cuaHangService.getOneCuaHang(cuaHang);
        ChucVu chucVuGetOne = chucVuService.getOneChucVu(chucVu);
        NhanVien nhanVien = null;
        LocalDate currentDate = LocalDate.now();
        Integer namSinh = Integer.valueOf(ngaySinh.substring(0, 4));
        int year = currentDate.getYear();
        String regex = "(\\+84|0)\\d{9,10}";
        String eror = "* TRỐNG *";
        if (check == true) {
            if (ma.isBlank()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ten.isBlank()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (tenDem.isBlank()) {
                request.setAttribute("tenDemTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ho.isBlank()) {
                request.setAttribute("hoTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (gioiTinh.isBlank()) {
                request.setAttribute("gioiTinhTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ngaySinh.isBlank() || namSinh > year) {
                request.setAttribute("ngaySinhTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (diaChi.isBlank()) {
                request.setAttribute("diaChiTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (soDienThoai.isBlank() || !soDienThoai.matches(regex)) {
                request.setAttribute("soDienThoaiTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (matKhau.isBlank()) {
                request.setAttribute("matKhauTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (trangThai.isBlank()) {
                request.setAttribute("trangThaiTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (cuaHang.equals("null")) {
                request.setAttribute("cuaHangTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (chucVu.equals("null")) {
                request.setAttribute("chucVuTrong", eror);
                chuyenTrang(request, response, 2);
            } else {
                nhanVien = new NhanVien(null, ma, ten, tenDem, ho, gioiTinh, Date.valueOf(ngaySinh), diaChi, soDienThoai, matKhau, cuaHangGetOne, chucVuGetOne, null, Integer.parseInt(trangThai));
            }
        } else {
            if (ma.isBlank()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ten.isBlank()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (tenDem.isBlank()) {
                request.setAttribute("tenDemTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ho.isBlank()) {
                request.setAttribute("hoTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (gioiTinh.isBlank()) {
                request.setAttribute("gioiTinhTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ngaySinh.isBlank() || namSinh > year) {
                request.setAttribute("ngaySinhTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (diaChi.isBlank()) {
                request.setAttribute("diaChiTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (soDienThoai.isBlank() || !soDienThoai.matches(regex)) {
                request.setAttribute("soDienThoaiTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (matKhau.isBlank()) {
                request.setAttribute("matKhauTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (trangThai.isBlank()) {
                request.setAttribute("trangThaiTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (cuaHang.equals("null")) {
                request.setAttribute("cuaHangTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (chucVu.equals("null")) {
                request.setAttribute("chucVuTrong", eror);
                chuyenTrang(request, response, 3);
            } else {
                nhanVien = new NhanVien(id, ma, ten, tenDem, ho, gioiTinh, Date.valueOf(ngaySinh), diaChi, soDienThoai, matKhau, cuaHangGetOne, chucVuGetOne, null, Integer.parseInt(trangThai));
            }
        }
        return nhanVien;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.addNhanVien(request, response);
        } else {
            this.updateNhanVien(request, response);
        }
    }

    private void updateNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = nhanVienService.updateNhanVien(getFormNhanVien(request, response, false));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }

    private void addNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = nhanVienService.addNhanVien(getFormNhanVien(request, response, true));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }
}
