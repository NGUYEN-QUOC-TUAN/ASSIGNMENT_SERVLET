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
    <title>ADD NHÂN VIÊN</title>
    <link rel="stylesheet" href="/css/Style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<header>
    <div class="tieuDe">
        <br>
        <h3>ADD NHÂN VIÊN</h3>
        <br>
    </div>
</header>
<section>
    <br>
    <div class="container">
        <form action="/nhan-vien/add" method="post">
            <div class="row">
                <div class="col-4">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhanVien.ma}" name="ma">
                        <label>MÃ NHÂN VIÊN <span style="color: red;">${maTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhanVien.ten}" name="ten">
                        <label>TÊN <span style="color: red;">${tenTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhanVien.tenDem}" name="tenDem">
                        <label>TÊN ĐỆM <span style="color: red;">${tenDemTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhanVien.ho}" name="ho">
                        <label>HỌ <span style="color: red;">${hoTrong}</span></label>
                    </div>
                </div>
                <div class="col-4">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="cuaHang">
                            <option value="null" selected>-- CỬA HÀNG <span style="color: red;">${cuaHangTrong}</span>--
                            </option>
                            <c:forEach items="${listCuaHangs}" var="cuaHang" varStatus="i">
                                <option value="${cuaHang.id}" ${cuaHang.id == nhanVien.cuaHang.id?"selected":""}>${cuaHang.ten}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <select class="form-select" name="chucVu">
                            <option value="null" selected>-- CHỨC VỤ <span style="color: red;">${chucVuTrong}</span>--
                            </option>
                            <c:forEach items="${listChucVus}" var="chucVu" varStatus="i">
                                <option value="${chucVu.id}" ${chucVu.id == nhanVien.chucVu.id?"selected":""}>${chucVu.ten}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control" value="${nhanVien.ngaySinh}" name="ngaySinh">
                        <label>NGÀY SINH <span style="color: red;">${ngaySinhTrong}</span></label>
                    </div>
                    <p>GIỚI TÍNH</p>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" checked type="radio" name="gioiTinh" id="inlineRadio1"
                               value="0" ${nhanVien.gioiTinh == "0"?"checked":""}>
                        <label class="form-check-label" for="inlineRadio1">NAM</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gioiTinh" id="inlineRadio2"
                               value="1" ${nhanVien.gioiTinh == "1"?"checked":""}>
                        <label class="form-check-label" for="inlineRadio2">NỮ</label>
                    </div>
                </div>
                <div class="col-4">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhanVien.diaChi}" name="diaChi">
                        <label>ĐỊA CHỈ <span style="color: red;">${diaChiTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" value="${nhanVien.soDienThoai}" name="soDienThoai">
                        <label>SỐ ĐIỆN THOẠI <span style="color: red;">${soDienThoaiTrong}</span></label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhanVien.matKhau}" name="matKhau">
                        <label>MẬT KHẨU <span style="color: red;">${matKhauTrong}</span></label>
                    </div>
                    <p>TRẠNG THÁI</p>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" checked type="radio" name="trangThai"
                               value="0" ${nhanVien.trangThai == 0?"checked":""}>
                        <label class="form-check-label" for="inlineRadio1">ĐANG LÀM</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="trangThai"
                               value="1" ${nhanVien.trangThai == 1?"checked":""}>
                        <label class="form-check-label" for="inlineRadio2">ĐÃ NGHỈ</label>
                    </div>
                </div>
            </div>
            <br>
            <hr>
            <a href="/nhan-vien/hien-thi" class="btn btn-warning">TRỞ VỀ NHÂN VIÊN</a>&emsp;
            <button class="btn btn-warning" type="submit">ADD NHÂN VIÊN</button>&emsp;
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
