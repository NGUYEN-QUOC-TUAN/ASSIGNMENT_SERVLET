package controller;

import entity.DongSanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DongSanPhamService;
import service.impl.DongSanPhamServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DongSanPhamController", value = {"/dong-san-pham/hien-thi", "/dong-san-pham/remove", "/dong-san-pham/view-add", "/dong-san-pham/view-update", "/dong-san-pham/add", "/dong-san-pham/update"})
public class DongSanPhamController extends HttpServlet {
    private List<DongSanPham> listDongSanPhams = new ArrayList<>();
    private DongSanPhamService dongSanPhamService = new DongSanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiDongSanPham(request, response);
        } else if (uri.contains("remove")) {
            this.removeDongSanPham(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddDongSanPham(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateDongSanPham(request, response);
        } else {
            this.hienthiDongSanPham(request, response);
        }
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void viewUpdateDongSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddDongSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void removeDongSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = dongSanPhamService.deleteDongSanPham(dongSanPhamService.getOneDongSanPham(request.getParameter("id")));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }

    private void hienthiDongSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG DÒNG SẢN PHẨM");
        chuyenTrang(request, response, 1);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listDongSanPhams", dongSanPhamService.getAllDongSanPham());
        request.setAttribute("dongSanPham", dongSanPhamService.getOneDongSanPham(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-dong-san-pham/view-dong-san-pham.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-dong-san-pham/view-add-dong-san-pham.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-dong-san-pham/view-update-dong-san-pham.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-dong-san-pham/view-dong-san-pham.jsp").forward(request, response);
        }
    }

    private DongSanPham getFormDongSanPham(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String eror = "* TRỐNG *";
        DongSanPham dongSanPham = null;
        if (check == true) {
            if (ma.isEmpty() && ten.isEmpty()) {
                request.setAttribute("maTrong", eror);
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ma.isEmpty()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ten.isEmpty()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 2);
            } else {
                dongSanPham = new DongSanPham(null, ma, ten);
            }
        } else {
            if (ma.isEmpty() && ten.isEmpty()) {
                request.setAttribute("maTrong", eror);
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ma.isEmpty()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ten.isEmpty()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 3);
            } else {
                dongSanPham = new DongSanPham(id, ma, ten);
            }
        }
        return dongSanPham;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = dongSanPhamService.addDongSanPham(getFormDongSanPham(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = dongSanPhamService.updateDongSanPham(getFormDongSanPham(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
