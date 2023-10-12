<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 4/2/2023
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UPDATE CHI TIẾT SẢN PHẨM</title>
    <link rel="stylesheet" href="/css/Style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<header>
    <div class="tieuDe">
        <br>
        <h3>UPDATE CHI TIẾT SẢN PHẨM</h3>
        <br>
    </div>
</header>
<section>
    <br>
    <div class="container">
        <form action="/chi-tiet-san-pham/update?id=${chiTietSP.id}" method="post">
            <div class="row">
                <div class="col-4">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="sanPham">
                            <option value="null" selected>-- SẢN PHẨM <span style="color: red;">${sanPhamTrong}</span>--
                            </option>
                            <c:forEach items="${listSanPhams}" var="sp" varStatus="i">
                                <option value="${sp.id}" ${chiTietSP.sanPham.id==sp.id?"selected":""}>${sp.ten}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <select class="form-select" name="nhaSanXuat">
                            <option value="null" selected>-- NHÀ SẢN XUẤT<span
                                    style="color: red;">${nhaSanXuatTrong}</span>--
                            </option>
                            <c:forEach items="${listNhaSanXuats}" var="nhaSX" varStatus="i">
                                <option value="${nhaSX.id}" ${chiTietSP.nhaSanXuat.id==nhaSX.id?"selected":""}>${nhaSX.ten}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${chiTietSP.moTa}" name="moTa">
                        <label>MÔ TẢ <span style="color: red;">${moTaTrong}</span></label>
                    </div>
                </div>
                <div class="col-4">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="mauSac">
                            <option value="null" selected>-- MÀU SẮC <span style="color: red;">${mauSacTrong}</span> --
                            </option>
                            <c:forEach items="${listMauSacs}" var="ms" varStatus="i">
                                <option value="${ms.id}" ${chiTietSP.mauSac.id==ms.id?"selected":""}>${ms.ten}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <select class="form-select" name="dongSanPham">
                            <option value="null" selected>-- DÒNG SẢN PHẨM <span
                                    style="color: red;">${dongSanPhamTrong}</span> --
                            </option>
                            <c:forEach items="${listDongSanPhams}" var="dongSP" varStatus="i">
                                <option value="${dongSP.id}" ${chiTietSP.dongSanPham.id==dongSP.id?"selected":""}>${dongSP.ten}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" value="${chiTietSP.namBaoHanh}" name="namBaoHanh">
                        <label>NĂM BẢO HÀNH <span style="color: red;">${namBaoHanhTrong}</span></label>
                    </div>
                </div>
                <div class="col-4">
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" value="${chiTietSP.soLuongTon}" name="soLuongTon">
                        <label>SỐ LƯỢNG TỒN <span style="color: red;">${soLuongTonTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" value="${chiTietSP.giaNhap}" name="giaNhap">
                        <label>GIÁ NHẬP <span style="color: red;">${giaNhapTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" value="${chiTietSP.giaBan}" name="giaBan">
                        <label>GIÁ BÁN <span style="color: red;">${giaBanTrong}</span></label>
                    </div>
                </div>
            </div>
            <br>
            <hr>
            <a href="/chi-tiet-san-pham/hien-thi" class="btn btn-warning">TRỞ VỀ CHI TIẾT SẢN PHẨM</a>&emsp;&emsp;
            <button class="btn btn-warning" type="submit">UPDATE CHI TIẾT SẢN PHẨM</button>
            <br>
            <hr>
        </form>
    </div>
</section>
<footer>
    <div class="noiDungFooter" style="text-align: center; font-weight: 400">
        <h4>TUANNQPH20022_SOF3011</h4>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.min.js"
        integrity="sha384-heAjqF+bCxXpCWLa6Zhcp4fu20XoNIA98ecBC1YkdXhszjoejr5y9Q77hIrv8R9i"
        crossorigin="anonymous"></script>
</body>
</html>
