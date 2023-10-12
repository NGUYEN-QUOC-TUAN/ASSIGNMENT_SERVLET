package controller;

import entity.NhaSanXuat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.NhaSanXuatService;
import service.impl.NhaSanXuatServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NhaSanXuatController", value = {
        "/nha-san-xuat/hien-thi",
        "/nha-san-xuat/remove",
        "/nha-san-xuat/view-add",
        "/nha-san-xuat/view-update",
        "/nha-san-xuat/add",
        "/nha-san-xuat/update",
})
public class NhaSanXuatController extends HttpServlet {
    private List<NhaSanXuat> listNhaSanXuats = new ArrayList<>();
    private NhaSanXuatService nhaSanXuatService = new NhaSanXuatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiNhaSanXuat(request, response);
        } else if (uri.contains("remove")) {
            this.removeNhaSanXuat(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddNhaSanXuat(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateNhaSanXuat(request, response);
        } else {
            this.hienthiNhaSanXuat(request, response);
        }
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void viewUpdateNhaSanXuat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddNhaSanXuat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void removeNhaSanXuat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = nhaSanXuatService.deleteNhaSanXuat(nhaSanXuatService.getOneNhaSanXuat(request.getParameter("id")));
        chuyenTrang(request, response, 1);
    }

    private void hienthiNhaSanXuat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG NHÀ SẢN XUẤT");
        chuyenTrang(request, response, 1);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listNhaSanXuats", nhaSanXuatService.getAllNhaSanXuats());
        request.setAttribute("nhaSanXuat", nhaSanXuatService.getOneNhaSanXuat(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-nha-san-xuat/view-nha-san-xuat.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-nha-san-xuat/view-add-nha-san-xuat.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-nha-san-xuat/view-update-nha-san-xuat.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-nha-san-xuat/view-nha-san-xuat.jsp").forward(request, response);
        }
    }

    private NhaSanXuat getFormNhaSanXuat(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String eror = "* TRỐNG *";
        NhaSanXuat nhaSanXuat = null;
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
                nhaSanXuat = new NhaSanXuat(null, ma, ten);
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
                nhaSanXuat = new NhaSanXuat(id, ma, ten);
            }
        }
        return nhaSanXuat;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = nhaSanXuatService.addNhaSanXuat(getFormNhaSanXuat(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = nhaSanXuatService.updateNhaSanXuat(getFormNhaSanXuat(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
