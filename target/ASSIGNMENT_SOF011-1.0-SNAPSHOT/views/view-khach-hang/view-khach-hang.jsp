<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31/3/2023
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KHÁCH HÀNG</title>
    <link rel="stylesheet" href="/css/Style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<header>
    <div class="tieuDe">
        <br>
        <h3>KHÁCH HÀNG</h3>
        <br>
    </div>
</header>
<section>
    <div class="container">
        <form action="">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="NHẬP VÀO TÊN !" aria-label="NHẬP VÀO TÊN !"
                       aria-describedby="button-addon2" name="search">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">SEARCH</button>
            </div>
        </form>
    </div>
    <br>
    <div class="container">
        <br>
        <hr>
        <br>
        <div class="row">
            <div class="col-4">
                <a href="/khach-hang/view-add" class="btn btn-warning">ADD KHÁCH HÀNG</a>&emsp;&emsp;
                <a href="/khach-hang/hien-thi" class="btn btn-warning">HIỂN THỊ KHÁCH HÀNG</a>&emsp;&emsp;
            </div>
            <div class="col-2">
                <a href="/index.jsp" class="btn btn-warning">TRANG CHỦ</a>&emsp;
            </div>
            <div class="col-6">
                <form action="">
                    <div class="row">
                        <div class="col-6">
                            <select class="form-select" name="sapXep">
                                <option value="1">SẮP XẾP TÊN</option>
                                <option value="2">SẮP XẾP TUỔI</option>
                            </select>
                        </div>
                        <div class="col-6">
                            <button class="btn btn-warning" type="submit">SẮP XẾP KHÁCH HÀNG</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <br>
        <hr>
        <br>
    </div>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">MÃ</th>
                <th scope="col">HỌ TÊN</th>
                <th scope="col">NGÀY SINH</th>
                <th scope="col">SĐT</th>
                <th scope="col">ĐỊA CHỈ</th>
                <th scope="col">THÀNH PHỐ</th>
                <th scope="col">QUỐC GIA</th>
                <th scope="col">MK</th>
                <th scope="col">ACTION</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listKhachHangs}" var="khachHang" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${khachHang.ma}</td>
                    <td>${khachHang.ho} ${khachHang.tenDem} ${khachHang.ten}</td>
                    <td>${khachHang.ngaySinh}</td>
                    <td>${khachHang.soDienThoai}</td>
                    <td>${khachHang.diaChi}</td>
                    <td>${khachHang.thanhPho}</td>
                    <td>${khachHang.quocGia}</td>
                    <td>${khachHang.matKhau}</td>
                    <td>
                        <a href="/khach-hang/view-update?id=${khachHang.id}" class="btn btn-success">UPDATE</a>&emsp;
                        <a href="/khach-hang/remove?id=${khachHang.id}" onclick="confirm('BẠN CHẮC XÓA KHÔNG !')"
                           class="btn btn-success">REMOVE</a>&emsp;
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<footer>
    <div class="noiDungFooter" style="text-align: center; font-weight: 400">
        <h4>TUANNQPH20022_SOF3011</h4>
    </div>
</footer>
<script>alert('${sessionScope.notify}');</script>
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



