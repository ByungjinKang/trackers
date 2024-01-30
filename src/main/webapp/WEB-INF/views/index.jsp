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
  <link rel="icon" type="image/x-icon" href="./resources/assets/favicon.ico" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="./resources/css/styles.css" rel="stylesheet" />
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
    <a class="btn btn-lg btn-light" href="#about">start button</a>
  </div>

  <!-- Bootstrap Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="myModalLabel">Add Data</h4>
        </div>
        <div class="modal-body">
          <!-- Input fields -->
          <input type="date" id="trackerDate">
          <input type="text" id="categoryName" placeholder="종류">
          <input type="text" id="description" placeholder="내용">
          <input type="text" id="assetName" placeholder="자산">
          <input type="number" id="amount" placeholder="금액">
          <input type="text" id="type" placeholder="타입">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" id="submit">Submit</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Start Button -->
  <a class="btn btn-lg btn-light" id="addButton">Add</a>

  <!-- jQuery and Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  <script>
    $(document).ready(function() {
      // Show modal when start button is clicked
      $('#addButton').click(function() {
        $('#myModal').modal('show');
      });

      // Send POST request when submit button is clicked
      $('#submit').click(function() {
        var trackerDate = $('#trackerDate').val();
        var categoryName = $('#categoryName').val();
        var description = $('#description').val();
        var assetName = $('#assetName').val();
        var amount = $('#amount').val();
        var type = $('#type').val();

        $.ajax({
          url: '/index/add',
          method: 'POST',
          contentType: 'application/json',
          data: JSON.stringify({
            trackerDate: trackerDate,
            categoryName: categoryName,
            description: description,
            assetName: assetName,
            amount: amount,
            type: type
          }),
          success: function(response) {
            // Handle success
            $('#myModal').modal('hide');
          },
          error: function(error) {
            // Handle error
            console.log(error);
          }
        });
      });
    });
  </script>
</header>
<!-- About section-->
<section id="about">
  <div class="container px-4">
    <div class="row gx-4 justify-content-center">
      <div class="col-lg-8">
        <h2>.</h2>
        <p class="lead">.</p>
        <table>
          <thead>
          <tr>
            <th>Date</th>
            <th></th>
            <th></th>
            <!-- Add other fields as needed -->
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list}" var="tracker">
            <tr>
              <td><c:out value="${tracker.categoryName}" /></td>
              <td><c:out value="${tracker.assetName}" /></td>
              <td><c:out value="${tracker.amount}" /></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <ul>
          <li>.</li>
        </ul>
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
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="./resources/js/scripts.js"></script>
</body>
</html>