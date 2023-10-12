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
    <title>UPDATE NHÀ SẢN XUẤT</title>
    <link rel="stylesheet" href="/css/Style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<header>
    <div class="tieuDe">
        <br>
        <h3>UPDATE NHÀ SẢN XUẤT</h3>
        <br>
    </div>
</header>
<section>
    <br>
    <div class="container">
        <form action="/nha-san-xuat/update?id=${nhaSanXuat.id}" method="post">
            <div class="row">
                <div class="col-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhaSanXuat.ma}" name="ma">
                        <label>MÃ NHÀ SẢN XUẤT <span style="color: red;">${maTrong}</span></label>
                    </div>
                </div>
                <div class="col-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${nhaSanXuat.ten}" name="ten">
                        <label>TÊN NHÀ SẢN XUẤT <span style="color: red;">${tenTrong}</span></label>
                    </div>
                </div>
            </div>
            <br>
            <hr>
            <a href="/nha-san-xuat/hien-thi" class="btn btn-warning">TRỞ VỀ NHÀ SẢN XUẤT</a>&emsp;
            <button class="btn btn-warning" type="submit">UPDATE NHÀ SẢN XUẤT</button>&emsp;
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