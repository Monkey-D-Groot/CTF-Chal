<%-- 
    Document   : index
    Created on : Oct 18, 2018, 9:51:53 AM
    Author     : khuyenn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String redirectURL = "/SVATTT/Index";
    if (request.getAttribute("datalst") == null) {
        response.sendRedirect(redirectURL);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SVATTT 2018</title>
        <link href="/SVATTT/css/font.css" rel="stylesheet">
        <link rel="stylesheet" href="/SVATTT/css/materialize.min.css">
        <script type="text/javascript" src="/SVATTT/js/jquery-2.1.1.min.js"></script>
        <script src="/SVATTT/js/materialize.min.js"></script>
    </head>
    <body>
        <style>
            #info{
                display: none;
            }
        </style>
        <script>
            function verifyDate() {
                var value = document.getElementById("date").value;
                var d = Date.parse(value);
                if (!isNaN(d) || value.length === 0) {
                    document.getElementById("search").submit();
                } else {
                    alert("Input invalid!")
                }
            }
            function showInfo() {
                var x = document.getElementById("info");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }
        </script>
        <!--<h1>Hello World!</h1>-->
        <div class="row">
            <center><h4><p onclick="showInfo()">Are you master Data Analysis?</p></h4></center>
            <div class="row center" id="info">
                <p>A collection of crime data from the Atlanta Police Department's open data portal.
                    <br>Their link to download the total dataset has been down forawhile. 
                    <br>This is an aggregation of city-wide data for every month between January 2009 and February 2017: http://opendata.atlantapd.org/
                    <br>The dataset includes 1/1/2009 - 2/28/2017. I have 100 rows to analyze.</p>
            </div>
            <form class="col s12" action="/SVATTT/Index" method="POST" id="search">
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Crime" id="crime" type="text" name="crime" value="${crime}">
                        <label for="crime">Crime</label>
                    </div>
                    <div class="input-field col s6">
                        <input placeholder="number contain:" id="number" type="text" name="number" value="${number}">
                        <label for="number">Number</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Date. Support JS date format. Eg: October 30, 1969" id="date" type="text" name="date" value="${date}">
                        <label for="date">Date</label>
                    </div>
                    <div class="input-field col s6">
                        <input placeholder="Location" id="location" type="text" name="location" value="${location}">
                        <label for="location">Location</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="Neighborhood" id="neighborhood" type="text" name="neighborhood" value="${neighborhood}">
                        <label for="neighborhood">Neighborhood</label>
                    </div>
                    <div class="input-field col s6">
                        <input placeholder="Latitude or Longtitude " id="ltd" type="text" name="ltd" value="${ltd}" >
                        <label for="ltd">Latitude or Longtitude</label>
                    </div>
                </div>
                <div class="row center">
                    <button class="center btn waves-effect waves-light" type="button" name="action" onclick="verifyDate()">Search</button>
                </div>
            </form>
        </div>

        <div class="row">
            <table class="bordered striped highlight centered responsive-table">
                <thead>
                    <tr>
                        <th data-field="Prev"><a href="/SVATTT/Index?page=${page - 1}">Prev</a></th>
                        <th data-field="Next"><a href="/SVATTT/Index?page=${page + 1}">Next</a></th>
                    </tr>
                </thead>
            </table>
        </div>
        <div class="row">
            <table class="bordered striped highlight centered responsive-table">
                <thead>
                    <tr>
                        <th data-field="ID">ID</th>
                        <th data-field="Crime">Crime</th>
                        <th data-field="Number">Number</th>
                        <th data-field="Date">Date</th>
                        <th data-field="Location">Location</th>
                        <th data-field="Beat">Beat</th>
                        <th data-field="Neighborhood">Neighborhood</th>
                        <th data-field="Npu">Npu</th>
                        <th data-field="Latitude">Latitude</th>
                        <th data-field="Longtitude">Longtitude</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datalst}" var="l">
                        <tr>
                            <td>${l.id}</td>
                            <td>${l.crime}</td>
                            <td>${l.number}</td>
                            <td>${l.date}</td>
                            <td>${l.location}</td>
                            <td>${l.beat}</td>
                            <td>${l.neighborhood}</td>
                            <td> ${l.npu}</td>
                            <td> ${l.lat}</td>
                            <td> ${l.longtitude}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
