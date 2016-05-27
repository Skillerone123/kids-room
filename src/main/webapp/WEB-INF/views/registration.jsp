
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="ua.softserveinc.tc.constants.ModelConstants.UsersConst" %>

<div class="col-sm-offset-4 col-sm-3">
    <form:form id="userform" action="registration" method="post" modelAttribute="<%=UsersConst.USER %>">
      <form:input path="role" type="hidden" value="USER" />

      <div class="form-group">
              <label for="firstname" class="required"><spring:message code="user.firstname" /></label>
              <form:input path="firstName"   class="form-control" style="text-transform: capitalize"/>
              <form:errors path="firstName" cssClass="error"  />
      </div>
      <div class="form-group">
              <label for="lastname" class="required"><spring:message code="user.lastname" /></label>
              <form:input path="lastName"   class="form-control" style="text-transform: capitalize"  />
              <form:errors path="lastName" cssClass="error" />
      </div>
      <div class="form-group">
                  <label for="email" class="required"><spring:message code="user.email" /></label>
                  <form:input path="email" type="email" id="userEmail" class="form-control"
                                style="text-transform: lowercase"  />
                  <form:errors path="email" cssClass="error"  />
      </div>

      <div class="form-group" >
                <label for="password" class="required"><spring:message code="user.password" /></label>
                <form:password path="password" id="userPassword" class="form-control" />
                <form:errors path="password" cssClass="error" />
      </div>
      <div class="form-group">
              <label for="confirm" class="required"><spring:message code="user.confirm" /></label>
              <form:password path="confirm"  class="form-control" />
              <form:errors path="confirm" cssClass="error" />
      </div>
      <div class="form-group">
              <label for="phonenumber" class="required"><spring:message code="user.phone" /></label>
              <form:input path="phoneNumber"  id="phonenumber" type="text" class="form-control"/>
              <form:errors path="phoneNumber" cssClass="error"  />
      </div>
         <div class="form-group">
              <spring:message code="user.prerules"/> <a data-toggle="modal" data-target="#rules"> <spring:message code="user.rules" /></a>
              <div class="col-sm-offset-3 col-sm-8">
                <button type="submit" id="button" class="btn btn-primary btn-lg"><spring:message code="user.register" /></button>
              </div>
      </div>
    </form:form>
</div>

<div class="modal fade" id="rules" tabindex="-1" role="dialog"  aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><spring:message code="user.rule"/></h4>
                </div>
                <div class="modal-body">
                    <p><spring:message code="rules"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="user.close"/></button>
                </div>
            </div>
        </div>
    </div>

