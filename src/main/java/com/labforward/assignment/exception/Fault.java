package com.labforward.assignment.exception;

public class Fault {
  private String code;
  private String message;
  private String status;
  private String datetime;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Fault code(String code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Fault message(String message) {
    this.message = message;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Fault status(String status) {
    this.status = status;
    return this;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public Fault datetime(String datetime) {
    this.datetime = datetime;
    return this;
  }
}
