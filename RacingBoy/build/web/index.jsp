<%-- 
    Document   : index
    Created on : Oct 19, 2018, 9:53:15 AM
    Author     : khuyenn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String redirectURL = "/Lunch/Index";
    if (request.getSession().getAttribute("money") == null) {
        response.sendRedirect(redirectURL);
    }
%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lunch</title>
        <link href="/Lunch/css/font.css" rel="stylesheet">
        <link rel="stylesheet" href="/Lunch/css/materialize.min.css">
        <script type="text/javascript" src="/Lunch/js/query-2.1.1.min.js.download"></script>
        <script src="/Lunch/css/materialize.min.js.download#/Lunch/src.txt"></script>
    </head>
    <body>
        <nav class="#64b5f6 blue lighten-2">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo">Lunch</a>
                <ul class="right hide-on-med-and-down">
                    <a>Your money: ${money}</a>
                </ul>
            </div>
        </nav>
        <script type="text/javascript">
            function clicked(iid) {
                var val = prompt("Mã giảm giá", "");
                var code = document.getElementById("code");
                code.value = val;
                var id = document.getElementById("id");
                id.value = iid;
                document.getElementById("book").submit();
            }
        </script>
        <style>
            .item-pic {
                max-width: 250px !important;
            }
        </style>

        <%
            if (request.getAttribute("mess") != null) {%>
                <script type="text/javascript">
                    alert("${mess}");
                </script>
        <% } 
        %>

        <div class="container">
            
            <div class="card horizontal">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="item-pic" src="/Lunch/images/ba_ch__rang_ch_y_c_nh_large.jpg">
                </div>
                <div class="card-content">
                    <span class="card-title activator grey-text text-darken-4">  Ba Chỉ Rang Cháy Cạnh  Giá 35000đ </span><br>
                    <button id="1" onclick="clicked(this.id);">Đặt</button>
                </div>
            </div>

            <div class="card horizontal">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="item-pic" src="/Lunch/images/t_m_rang_mu_i_1_large.png">
                </div>
                <div class="card-content">
                    <span class="card-title activator grey-text text-darken-4">  Tôm Rang Muối  Giá 35000đ </span><br>
                    <button id="2" onclick="clicked(this.id);">Đặt</button>
                </div>
            </div>

            <div class="card horizontal">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="item-pic" src="/Lunch/images/c__phile_chi_n_m_m_t_i_1_large.png">
                </div>
                <div class="card-content">
                    <span class="card-title activator grey-text text-darken-4">  Cá Phile Chiên Mắm Tỏi  Giá 35000đ </span><br>
                    <button id="3" onclick="clicked(this.id);">Đặt</button>
                </div>
            </div>

            <div class="card horizontal">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="item-pic" src="/Lunch/images/s__n_s_n_x_o_chua_ng_t_1_large.png">
                </div>
                <div class="card-content">
                    <span class="card-title activator grey-text text-darken-4">  Sườn Sụn Xào Chua Ngọt  Giá 35000đ </span><br>
                    <button id="4" onclick="clicked(this.id);">Đặt</button>
                </div>
            </div>

            <form action="/Lunch/Index" method="POST" id="book" name="book">
                <input type="hidden" name="id" id="id">
                <input type="hidden" name="code" id="code">
            </form>
        </div>
    </body>
</html>