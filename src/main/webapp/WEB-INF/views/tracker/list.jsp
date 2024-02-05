<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Expense Tracker</title>
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />
    <link href="/resources/css/dropdown.css" rel="stylesheet" />
</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container px-4">
        <a class="navbar-brand" href="#page-top">EXPENSE TRACKER</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#services">Services</a></li>
                <li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Header-->
<header class="bg-primary bg-gradient text-white">
    <div class="container px-4 text-center">
        <h1 class="fw-bolder">fw-bolder</h1>
        <p class="lead">p class</p>
        <%--    <a class="btn btn-lg btn-light" href="#about">start button</a>--%>
    </div>

    <!-- Bootstrap Modal -->
    <div class="modal fade" id="expenseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="expenseModalLabel"><span style="color: black;">지출</span></h4>
                </div>
                <div class="modal-body">
                    <!-- Input fields -->
                    <input type="date" id="expenseTrackerDate" >
                    <select id="expenseCategoryName">
                        <option value="none">===종류===</option>
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.name}">${category.name}</option>
                        </c:forEach>
                    </select>
                    <input type="text" id="expenseDescription" placeholder="내용">
                    <select id="expenseAssetName">
                        <option value="none">===자산===</option>
                        <c:forEach var="asset" items="${assetList}">
                            <option value="${asset.name}">${asset.name}</option>
                        </c:forEach>
                    </select>
                    <input type="number" id="expenseAmount" placeholder="금액">
                    <input type="hidden" id="expenseType" value="지출">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="expenseSubmit">Submit</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="incomeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="incomeModalLabel"><span style="color: black;">추가</span></h4>
                </div>
                <div class="modal-body">
                    <!-- Input fields -->
                    <input type="date" id="incomeTrackerDate">
                    <select id="incomeCategoryName">
                        <option value="none">===종류===</option>
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.name}">${category.name}</option>
                        </c:forEach>
                    </select>
                    <input type="text" id="incomeDescription" placeholder="내용">
                    <select id="incomeAssetName">
                        <option value="none">===자산===</option>
                        <c:forEach var="asset" items="${assetList}">
                            <option value="${asset.name}">${asset.name}</option>
                        </c:forEach>
                    </select>
                    <input type="number" id="incomeAmount" placeholder="금액">
                    <input type="hidden" id="incomeType" value="수입">
<%--                    <select id="type">--%>
<%--                        <option value="지출">지출</option>--%>
<%--                        <option value="수입">수입</option>--%>
<%--                    </select>--%>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="incomeSubmit">Submit</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap Modal -->
    <div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="regModalLabel">Register</h4>
                </div>
                <div class="modal-body">
                    <!-- Input fields -->
                    <input type="text" id="regId" placeholder="아이디"><br>
                    <input type="text" id="regPassword" placeholder="패스워드">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="regSubmit">Register</button>
                </div>
            </div>
        </div>
    </div>

    <%--  <!-- Bootstrap Modal -->--%>
    <%--  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
    <%--    <div class="modal-dialog" role="document">--%>
    <%--      <div class="modal-content">--%>
    <%--        <div class="modal-header">--%>
    <%--          <h4 class="modal-title" id="loginModalLabel">login</h4>--%>
    <%--        </div>--%>
    <%--        <div class="modal-body">--%>
    <%--          <!-- Input fields -->--%>
    <%--          <input type="text" id="userId" placeholder="아이디"><br>--%>
    <%--          <input type="text" id="userPassword" placeholder="패스워드">--%>
    <%--        </div>--%>
    <%--        <div class="modal-footer">--%>
    <%--          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
    <%--          <button type="button" class="btn btn-primary" id="loginForm">Login</button>--%>
    <%--        </div>--%>
    <%--      </div>--%>
    <%--    </div>--%>
    <%--  </div>--%>

    <!-- Bootstrap Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel2">Login</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="loginForm">
                        <div class="form-group">
                            <label for="loginUserId">User ID</label>
                            <input type="text" class="form-control" id="loginUserId" placeholder="Enter user ID">
                        </div>
                        <div class="form-group">
                            <label for="loginUserPassword">Password</label>
                            <input type="password" class="form-control" id="loginUserPassword" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-primary" id="loginSubmit">login</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Start Button -->
    <a class="btn btn-lg btn-light" id="incomeAdd">수입</a>
    <a class="btn btn-lg btn-light" id="expenseAdd">지출</a>
    <a class="btn btn-lg btn-light" id="logout">Logout</a>

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</header>
<!-- About section-->
<section id="about">
    <div class="container px-4">
        <div class="row gx-4 justify-content-center">

            <div class="col-lg-8">
                <div class="log-7" style="display: flex; align-items: center;">
                    <h2>전체 내역: <c:out value="${countList}" />건</h2>
                    <div>
                    <input type="checkbox" id="incomeCheckbox" name="type" value="2" checked>
                    <label for="incomeCheckbox">수입</label>
                    <input type="checkbox" id="expenseCheckbox" name="type" value="1" checked>
                    <label for="expenseCheckbox">지출</label>&nbsp
                    </div>
                </div>
                <p class="lead"></p>
                    <table id="trackerTable">
                        <c:forEach var="tracker" items="${trackerList}">
                            <c:if test="${empty lastDate or lastDate != tracker.trackerDate}">
                                <tr data-type="${tracker.typeId}">
                                    <!-- 날짜+요일 -->
                                    <th>
                                            ${tracker.trackerDate.year}-${tracker.trackerDate.monthValue}-${tracker.trackerDate.dayOfMonth} (${tracker.trackerDate.dayOfWeek})
                                    </th>
                                    <th>

                                    </th>
                                    <th>

                                    </th>
                                    <!-- Add other columns as needed -->
                                </tr>
                                <c:set var="lastDate" value="${tracker.trackerDate}" />
                            </c:if>
                            <tr data-type="${tracker.typeId}">
                                <td><c:out value="${tracker.categoryName}" /></td>
                                <td><c:out value="${tracker.assetName}" /></td>
                                <td><c:out value="${tracker.amount}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
<%--                <ul>--%>
<%--                    <li>.</li>--%>
<%--                </ul>--%>
            </div>
        </div>
    </div>
</section>
<!-- Services section-->
<section class="bg-light" id="services">
    <div class="container px-4">
        <div class="row gx-4 justify-content-center">
            <div class="col-lg-8">
                <h2>Services we offer</h2>
                <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut optio velit inventore, expedita quo laboriosam possimus ea consequatur vitae, doloribus consequuntur ex. Nemo assumenda laborum vel, labore ut velit dignissimos.</p>
            </div>
        </div>
    </div>
</section>
<!-- Contact section-->
<section id="contact">
    <div class="container px-4">
        <div class="row gx-4 justify-content-center">
            <div class="col-lg-8">
                <h2>Contact us</h2>
                <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero odio fugiat voluptatem dolor, provident officiis, id iusto! Obcaecati incidunt, qui nihil beatae magnam et repudiandae ipsa exercitationem, in, quo totam.</p>
            </div>
        </div>
    </div>
</section>
<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container px-4"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>

<script>
    function submitData(type) {
        var trackerDate = $('#' + type + 'TrackerDate').val();
        var categoryName = $('#' + type + 'CategoryName').val();
        var description = $('#' + type + 'Description').val();
        var assetName = $('#' + type + 'AssetName').val();
        var amount = $('#' + type + 'Amount').val();
        var typeName = $('#' + type + 'Type').val();

        $.ajax({
            url: '/tracker/add',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                trackerDate: trackerDate,
                categoryName: categoryName,
                description: description,
                assetName: assetName,
                amount: amount,
                typeName: typeName
            }),
            success: function(response) {
                // Handle success
                $('#' + type + 'Modal').modal('hide');
            },
            error: function(error) {
                // Handle error
                console.log(error);
            }
        });
    }

    $(document).ready(function() {
        // Show modal when button is clicked
        ['income', 'expense'].forEach(function(buttonId) {
            $('#' + buttonId + 'Add').click(function() {
                $('#' + buttonId + 'Modal').modal('show');


            });
        });

        // Send POST request when submit button is clicked
        ['income', 'expense'].forEach(function(buttonId) {
            $('#' + buttonId + 'Submit').click(function() {
                submitData(buttonId);
            });
        });
    });

    function setTodayDate(elementId) {
        document.getElementById(elementId).value = new Date().toISOString().slice(0, 10);
    }

    setTodayDate('expenseTrackerDate');
    setTodayDate('incomeTrackerDate');

    $('input[name="type"]').change(function() {
        var selectedTypes = [];
        $('input[name="type"]:checked').each(function() {
            selectedTypes.push($(this).val());
        });

        $('#trackerTable tr').each(function() {
            var type = $(this).data('type');
            if (selectedTypes.includes(type.toString())) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
</script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/resources/js/scripts.js"></script>
</body>
</html>