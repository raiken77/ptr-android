package com.mru.ptr;

/**
 * Created by Jonathan on 2019-10-20.
 */
public class Response<T> {
  public T data;
  public String errorMessage;
  public ResponseStatus status;


  public T getData() {
    return data;
  }

  public Response<T> setData(T data) {
    this.data = data;
    return this;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Response<T> setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public ResponseStatus getStatus() {
    return status;
  }

  public Response<T> setStatus(ResponseStatus status) {
    this.status = status;
    return this;
  }
}
