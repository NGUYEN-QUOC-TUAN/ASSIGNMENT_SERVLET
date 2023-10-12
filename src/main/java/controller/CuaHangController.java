package controller;

import entity.CuaHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CuaHangService;
import service.impl.CuaHangServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CuaHangController", value = {
        "/cua-hang/hien-thi",
        "/cua-hang/remove",
        "/cua-hang/view-add",
        "/cua-hang/view-update",
        "/cua-hang/add",
        "/cua-hang/update"
})
public class CuaHangController extends HttpServlet {
    private List<CuaHang> listCuaHangs = new ArrayList<>();
    private CuaHangService cuaHangService = new CuaHangServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienthiCuaHang(request, response);
        } else if (uri.contains("remove")) {
            this.removeCuaHang(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAddCuaHang(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdateCuaHang(request, response);
        } else {
            this.hienthiCuaHang(request, response);
        }
    }

    private void thongBao(HttpServletRequest request, String notify) {
        HttpSession session = request.getSession();
        session.setAttribute("notify", notify);
    }

    private void viewUpdateCuaHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 3);
    }

    private void viewAddCuaHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chuyenTrang(request, response, 2);
    }

    private void removeCuaHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notify = cuaHangService.deleteCuaHang(cuaHangService.getOneCuaHang(request.getParameter("id")));
        thongBao(request, notify);
        chuyenTrang(request, response, 1);
    }

    private void hienthiCuaHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        thongBao(request, "BẠN ĐANG VÀO TRANG CỬA HÀNG");
        chuyenTrang(request, response, 1);
    }

    private void chuyenTrang(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException {
        request.setAttribute("listCuaHangs", cuaHangService.getAllCuaHangs());
        request.setAttribute("cuaHang", cuaHangService.getOneCuaHang(request.getParameter("id")));
        if (check == 1) {
            request.getRequestDispatcher("/views/view-cua-hang/view-cua-hang.jsp").forward(request, response);
        } else if (check == 2) {
            request.getRequestDispatcher("/views/view-cua-hang/view-add-cua-hang.jsp").forward(request, response);
        } else if (check == 3) {
            request.getRequestDispatcher("/views/view-cua-hang/view-update-cua-hang.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/view-cua-hang/view-cua-hang.jsp").forward(request, response);
        }
    }

    private CuaHang getFormCuaHang(HttpServletRequest request, HttpServletResponse response, boolean check) throws ServletException, IOException {
        String id = request.getParameter("id");
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        CuaHang cuaHang = null;
        String eror = "* TRỐNG *";
        if (check == true) {
            if (ma.isEmpty()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (ten.isEmpty()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (diaChi.isEmpty()) {
                request.setAttribute("diaChiTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (thanhPho.isEmpty()) {
                request.setAttribute("thanhPhoTrong", eror);
                chuyenTrang(request, response, 2);
            } else if (quocGia.isEmpty()) {
                request.setAttribute("quocGiaTrong", eror);
                chuyenTrang(request, response, 2);
            } else {
                cuaHang = new CuaHang(null, ma, ten, diaChi, thanhPho, quocGia);
            }
        } else {
            if (ma.isEmpty()) {
                request.setAttribute("maTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (ten.isEmpty()) {
                request.setAttribute("tenTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (diaChi.isEmpty()) {
                request.setAttribute("diaChiTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (thanhPho.isEmpty()) {
                request.setAttribute("thanhPhoTrong", eror);
                chuyenTrang(request, response, 3);
            } else if (quocGia.isEmpty()) {
                request.setAttribute("quocGiaTrong", eror);
                chuyenTrang(request, response, 3);
            } else {
                cuaHang = new CuaHang(id, ma, ten, diaChi, thanhPho, quocGia);
            }
        }
        return cuaHang;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            String notify = cuaHangService.addCuaHang(getFormCuaHang(request, response, true));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        } else {
            String notify = cuaHangService.updateCuaHang(getFormCuaHang(request, response, false));
            thongBao(request, notify);
            chuyenTrang(request, response, 1);
        }
    }
}
