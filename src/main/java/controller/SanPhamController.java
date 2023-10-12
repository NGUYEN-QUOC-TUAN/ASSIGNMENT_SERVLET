package controller;

import entity.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.SanPhamService;
import service.impl.SanPhamServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SanPhamController", value = {
        "/san-pham/hien-thi",
        "/san-pham/remove",
        "/san-pham/view-add",
        "/san-pham/view-update",
        "/san-pham/add",
        "/san-pham/update",
})
public class SanPhamController extends HttpServlet {
    private List<SanPham> listSanphams = new ArrayList<>();
    private SanPhamService sanPhamService = new SanPhamServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiSanPham(request, response);
        } else if (uri.contains("remove")) {
            this.removeSanPham(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddSanPham(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateSanPham(request, response);
        } else {
            this.hienthiSanPham(request, response);
        }
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void viewUpdateSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void removeSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = sanPhamService.deleteSanPham(sanPhamService.getOneSanPham(request.getParameter("id")));
        chuyenTrang(request, response, 1);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listSanphams", sanPhamService.getAllSanPhams());
        request.setAttribute("sanPham", sanPhamService.getOneSanPham(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-san-pham/view-san-pham.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-san-pham/view-add-san-pham.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-san-pham/view-update-san-pham.jsp").forward(request, response);
        }
    }

    private void hienthiSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRAG SẢN PHẨM");
        chuyenTrang(request, response, 1);
    }

    private SanPham getFormSanPham(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        SanPham sanPham = null;
        String eror = "* TRỐNG *";
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
                sanPham = new SanPham(null, ma, ten);
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
                sanPham = new SanPham(id, ma, ten);
            }
        }
        return sanPham;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = sanPhamService.addSanPham(getFormSanPham(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = sanPhamService.addSanPham(getFormSanPham(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
