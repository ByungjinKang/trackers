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
<script>
    function setTodayDate(elementId) {
        document.getElementById(elementId).value = new Date().toISOString().slice(0, 10);
    }

    function submitData(type) {
        console.log('submitData:', type)
        var trackerDate = $('#' + type + 'TrackerDate').val();
        var categoryName = $('#' + type + 'CategoryName').val();
        var description = $('#' + type + 'Description').val();
        var assetName = $('#' + type + 'AssetName').val();
        var amount = $('#' + type + 'Amount').val();
        var typeName = $('#' + type + 'Type').val();

        console.log('Form data:', { // Add this line
            trackerDate,
            categoryName,
            description,
            assetName,
            amount,
            typeName
        });

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
                console.log(response);
            },
            error: function(error) {
                // Handle error
                console.log(error);
            }
        });
    }
</script>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container px-4">
        <a class="navbar-brand" href="#page-top">EXPENSE TRACKER</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="#about">목록</a></li>
                <li class="nav-item"><a class="nav-link" href="#services">달력</a></li>
                <li class="nav-item"><a class="nav-link" href="#contact">차트</a></li>
                <li class="nav-item" id="logout"><a class="nav-link" href="#">로그아웃</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Header-->
<header class="bg-primary bg-gradient text-white">
    <div class="container px-4 text-center">
<%--        <p class="lead" id="currentDateYear"></p>--%>
        <h5 class="fw-bolder" id="currentDateYear"></h5>
        <div>
            <button id="prevMonth">&lt;</button>
            <h1 class="fw-bolder" id="currentDateMonth"></h1>
            <button id="nextMonth">&gt;</button>
        </div>
        <%--    <a class="btn btn-lg btn-light" href="#about">start button</a>--%>
    </div>

    <!-- Start Button -->
    <a class="btn btn-lg btn-light" id="incomeAdd">수입</a>
    <a class="btn btn-lg btn-light" id="expenseAdd">지출</a>

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
    var incomeFormDiv = document.createElement('div');
    var expenseFormDiv = document.createElement('div');
    incomeFormDiv.id = 'incomeFormDiv';
    expenseFormDiv.id = 'expenseFormDiv';

    var expenseAddButton = document.getElementById('expenseAdd');
    expenseAddButton.parentNode.insertBefore(incomeFormDiv, expenseAddButton.nextSibling);
    expenseAddButton.parentNode.insertBefore(expenseFormDiv, expenseAddButton.nextSibling);

    $(document).ready(function() {
        generateIncomeFormHTML();
        generateExpenseFormHTML();

        var incomeAddButton = $('#incomeAdd');
        var expenseAddButton = $('#expenseAdd');

        setTodayDate('expenseTrackerDate');
        expenseAddButton.addClass('button-clicked');

        // 처음에는 수입 form을 숨깁니다.
        $('#incomeForm').hide();


        // "수입" 버튼에 클릭 이벤트 리스너를 추가합니다.
        incomeAddButton.click(function() {
            // "지출" form을 숨기고 "수입" form을 표시합니다.
            $('#expenseForm').hide();
            $('#incomeForm').show();

            setTodayDate('incomeTrackerDate');

            // Change button colors
            incomeAddButton.removeClass('button-default');
            incomeAddButton.addClass('button-clicked');
            expenseAddButton.removeClass('button-clicked');
            expenseAddButton.addClass('button-default');
        });

        // "지출" 버튼에 클릭 이벤트 리스너를 추가합니다.
        expenseAddButton.click(function() {
            // "수입" form을 숨기고 "지출" form을 표시합니다.
            $('#incomeForm').hide();
            $('#expenseForm').show();

            setTodayDate('expenseTrackerDate');

            // Change button colors
            expenseAddButton.removeClass('button-default');
            expenseAddButton.addClass('button-clicked');
            incomeAddButton.removeClass('button-clicked');
            incomeAddButton.addClass('button-default');
        });

        function generateIncomeFormHTML() {
            var incomeFormHTML = `
    <form id="incomeForm">
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
        <button type="submit" id="incomeSubmit">Submit</button>
    </form>
    `;
            incomeFormDiv.innerHTML += incomeFormHTML;

            $('#incomeSubmit').click(function(event) {
                console.log('incomeSubmit button clicked');
                event.preventDefault();
                submitData('income');
            });
        }

        function generateExpenseFormHTML() {
            var expenseFormHTML = `
    <form id="expenseForm">
        <input type="date" id="expenseTrackerDate">
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
        <button type="submit" id="expenseSubmit">Submit</button>
    </form>
    `;
            expenseFormDiv.innerHTML += expenseFormHTML;

            $('#expenseSubmit').click(function(event) {
                event.preventDefault()
                submitData('expense');
            });
        }

        var date = new Date();

        // 년도와 월을 가져옵니다.
        var year = date.getFullYear();
        var month = date.getMonth() + 1;

        // 원하는 형식으로 날짜를 변환합니다.
        var formattedDateYear = year + '년 '
        var formattedDateMonth = month + '월';

        // h1 태그에 날짜를 설정합니다.
        $('#currentDateYear').text(formattedDateYear);
        $('#currentDateMonth').text(formattedDateMonth);

        // "이전 달" 버튼에 클릭 이벤트 리스너를 추가합니다.
        $('#prevMonth').click(function() {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }
            $('#currentDateYear').text(year + '년 ');
            $('#currentDateMonth').text(month + '월');
        });

        // "다음 달" 버튼에 클릭 이벤트 리스너를 추가합니다.
        $('#nextMonth').click(function() {
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
            $('#currentDateYear').text(year + '년 ');
            $('#currentDateMonth').text(month + '월');
        });

        // "로그아웃" 버튼에 클릭 이벤트 리스너를 추가합니다.
        function
    });
</script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/resources/js/scripts.js"></script>
</body>
</html>