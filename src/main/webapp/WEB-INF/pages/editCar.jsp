<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="EditCar">
    <h1>Edit Car</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="license_plate">LicensePlate:</label>
                <input class="form-control" type="text" id="license_plate" name="license_plate" placeholder="" value="${car.licensePlate}"><br><br>
                <div class="invalid-feedback">
                    License Plate is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="parking_spot">Parking spot:</label>
                <input class="form-control" type="text" id="parking_spot" name="parking_spot" placeholder="" value="${car.parkingSpot}"><br><br>
                <div class="invalid-feedback">
                    Parking spot is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="owner_id">Owner:</label>
                <select class="form-control" name="owner_id" id="owner_id">
                    <option name="owner_id" value="${user.id}">Choose...</option>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${car.ownerName eq user.username ? 'selected' : ''}>${user.username}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Owner is required.
                </div>
            </div>
        </div>
        <hr class="mb-4">
        <input type="hidden" name="car_id" value="${car.id}">
        <div class="row">
            <div class="col-md-6 mb-3">
                <input class="btn btn-primary btn-lg" type="submit" value="Submit" name="submit">
            </div>
        </div>
    </form>
</t:pageTemplate>