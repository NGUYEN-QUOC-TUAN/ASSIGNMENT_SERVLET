package controller;

import entity.ChucVu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ChucVuService;
import service.impl.ChuVuServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChucVuController", value = {
        "/chuc-vu/hien-thi",
        "/chuc-vu/remove",
        "/chuc-vu/view-add",
        "/chuc-vu/view-update",
        "/chuc-vu/add",
        "/chuc-vu/update"
})
public class ChucVuController extends HttpServlet {
    private List<ChucVu> listChucVus = new ArrayList<>();
    private ChucVuService chucVuService = new ChuVuServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiChucVu(request, response);
        } else if (uri.contains("remove")) {
            this.removeChucVu(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddChucVu(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateChucVu(request, response);
        } else {
            this.hienthiChucVu(request, response);
        }
    }

    private void viewUpdateChucVu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddChucVu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void removeChucVu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = chucVuService.deleteChucVu(chucVuService.getOneChucVu(request.getParameter("id")));
        thongBao(request, check);
        chuyenTrang(request, response, 1);
    }

    private void hienthiChucVu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "HIỂN THỊ CHỨC VỤ");
        chuyenTrang(request, response, 1);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listChucVus", chucVuService.getAllChucVus());
        request.setAttribute("chucVu", chucVuService.getOneChucVu(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-chuc-vu/view-chuc-vu.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-chuc-vu/view-add-chuc-vu.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-chuc-vu/view-update-chuc-vu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-chuc-vu/view-chuc-vu.jsp").forward(request, response);
        }
    }

    private ChucVu getFormChucVu(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        ChucVu chucVu = null;
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
                chucVu = new ChucVu(null, ma, ten);
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
                chucVu = new ChucVu(id, ma, ten);
            }
        }
        return chucVu;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = chucVuService.addChucVu(getFormChucVu(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = chucVuService.updateChucVu(getFormChucVu(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
