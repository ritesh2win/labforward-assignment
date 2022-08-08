package com.labforward.assignment.logging;

public class LogBuilder {

  private String step;
  private String event;
  private String detailMessage;

  public LogBuilder(String step, String event) {
    this.step = step;
    this.event = event;
  }

  public LogBuilder detailMessage(String detailMessage) {
    this.detailMessage = detailMessage;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(" event=").append(this.event);
    sb.append(" step=").append(this.step);
    sb.append(" detail message=").append(this.detailMessage);
    return sb.toString();
  }
}
