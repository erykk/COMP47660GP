<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img id="logo" src="images/airplane.png" alt="Image" width="200px;">
        </a>
        <h2 class="">An Airline</h2>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
            &nbsp;&nbsp;&nbsp;
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
            &nbsp;&nbsp;&nbsp;
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Register as Executive</a></li>
            &nbsp;&nbsp;&nbsp;
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/flight">All Flights</a></li>
            &nbsp;&nbsp;&nbsp;
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/reservation">View Reservation</a></li>

            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/deleteAccount">Delete Executive Account</a></li>
        </ul>
    </div>
</nav>
