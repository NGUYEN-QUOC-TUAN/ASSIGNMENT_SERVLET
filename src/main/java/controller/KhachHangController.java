package controller;

import entity.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.KhachHangService;
import service.impl.KhachHangServiceImpl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "KhachHangController", value = {
        "/khach-hang/hien-thi",
        "/khach-hang/remove",
        "/khach-hang/view-add",
        "/khach-hang/view-update",
        "/khach-hang/add",
        "/khach-hang/update"
})
public class KhachHangController extends HttpServlet {
    private List<KhachHang> listKhachHangs = new ArrayList<>();
    private KhachHangService khachHangService = new KhachHangServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiKhachHang(request, response);
        } else if (uri.contains("remove")) {
            this.removeKhachHang(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddKhachHang(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateKhachHang(request, response);
        } else {
            this.hienthiKhachHang(request, response);
        }
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void viewUpdateKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void removeKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = khachHangService.deleteKhachHang(khachHangService.getOneKhachHang(request.getParameter("id")));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }

    private void hienthiKhachHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG KHÁCH HÀNG");
        chuyenTrang(request, response, 1);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listKhachHangs", khachHangService.getAllKhachHangs());
        request.setAttribute("khachHang", khachHangService.getOneKhachHang(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-khach-hang/view-khach-hang.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-khach-hang/view-add-khach-hang.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-khach-hang/view-update-khach-hang.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-khach-hang/view-khach-hang.jsp").forward(request, response);
        }
    }

    private KhachHang getFormKhachHang(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ho = request.getParameter("ho");
        String tenDem = request.getParameter("tenDem");
        String ten = request.getParameter("ten");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChi = request.getParameter("diaChi");
        String soDienThoai = request.getParameter("soDienThoai");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        String matKhau = request.getParameter("matKhau");
        KhachHang khachHang = null;
        LocalDate currentDate = LocalDate.now();
        Integer namSinh = Integer.valueOf(ngaySinh.substring(0, 4));
        int year = currentDate.getYear();
        String regex = "(\\+84|0)\\d{9,10}";
        String eror = "* TRỐNG *";
        if (check == true) {
            if (ho.isBlank()) {
                request.setAttribute("hoTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (tenDem.isBlank()) {
                request.setAttribute("tenDemTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ten.isBlank()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ma.isBlank()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (soDienThoai.isBlank() || !soDienThoai.matches(regex)) {
                request.setAttribute("soDienThoaiTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (matKhau.isBlank()) {
                request.setAttribute("matKhauTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ngaySinh.isBlank() || namSinh > year) {
                request.setAttribute("ngaySinhTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (diaChi.isBlank()) {
                request.setAttribute("diaChiTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (thanhPho.isBlank()) {
                request.setAttribute("thanhPhoTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (quocGia.isBlank()) {
                request.setAttribute("quocGiaTrong", eror);
                chuyenTrang(request, response, 2);
            } else {
                khachHang = new KhachHang(null, ma, ten, tenDem, ho, Date.valueOf(ngaySinh), soDienThoai, diaChi, thanhPho, quocGia, matKhau);
            }
        } else {
            if (ho.isBlank()) {
                request.setAttribute("hoTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (tenDem.isBlank()) {
                request.setAttribute("tenDemTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ten.isBlank()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ma.isBlank()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (soDienThoai.isBlank() || !soDienThoai.matches(regex)) {
                request.setAttribute("soDienThoaiTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (matKhau.isBlank()) {
                request.setAttribute("matKhauTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ngaySinh.isBlank() || namSinh > year) {
                request.setAttribute("ngaySinhTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (diaChi.isBlank()) {
                request.setAttribute("diaChiTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (thanhPho.isBlank()) {
                request.setAttribute("thanhPhoTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (quocGia.isBlank()) {
                request.setAttribute("quocGiaTrong", eror);
                chuyenTrang(request, response, 3);
            } else {
                khachHang = new KhachHang(id, ma, ten, tenDem, ho, Date.valueOf(ngaySinh), soDienThoai, diaChi, thanhPho, quocGia, matKhau);
            }
        }
        return khachHang;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = khachHangService.addKhachHang(getFormKhachHang(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = khachHangService.updateKhachHang(getFormKhachHang(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
