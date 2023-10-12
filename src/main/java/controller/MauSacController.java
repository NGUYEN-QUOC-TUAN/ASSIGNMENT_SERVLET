package controller;

import entity.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MauSacService;
import service.impl.MauSacServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MauSacController", value = {
        "/mau-sac/hien-thi",
        "/mau-sac/remove",
        "/mau-sac/view-add",
        "/mau-sac/view-update",
        "/mau-sac/add",
        "/mau-sac/update"
})
public class MauSacController extends HttpServlet {
    private List<MauSac> listMauSacs = new ArrayList<>();
    private MauSacService mauSacService = new MauSacServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiMauSac(request, response);
        } else if (uri.contains("remove")) {
            this.removeMauSac(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddMauSac(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateMauSac(request, response);
        } else {
            this.hienthiMauSac(request, response);
        }
    }
    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }
    private void viewUpdateMauSac(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddMauSac(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void removeMauSac(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify =  mauSacService.deleteMauSac(mauSacService.getOneMauSac(request.getParameter("id")));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }

    private void hienthiMauSac(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG MÀU SẮC");
        chuyenTrang(request, response, 1);
    }

    private MauSac getFormMauSac(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        MauSac mauSac = null;
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
                mauSac = new MauSac(null, ma, ten);
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
                mauSac = new MauSac(id, ma, ten);
            }
        }
        return mauSac;
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listMauSacs", mauSacService.getAllMauSacs());
        request.setAttribute("mauSac", mauSacService.getOneMauSac(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-mau-sac/view-mau-sac.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-mau-sac/view-add-mau-sac.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-mau-sac/view-update-mau-sac.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-mau-sac/view-mau-sac.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = mauSacService.addMauSac(getFormMauSac(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = mauSacService.updateMauSac(getFormMauSac(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
