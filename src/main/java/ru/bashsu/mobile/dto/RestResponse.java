package ru.bashsu.mobile.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
public class RestResponse<T> {

    @Getter
    private final T result;

    @Getter
    private final String msg;

    public RestResponse(T result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public static <T> ResponseEntity<RestResponse<T>> success(T result) {
        RestResponse<T> response = new RestResponse<>(result, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    public static <T> ResponseEntity<RestResponse<T>> error(HttpStatus status, String msg) {
        RestResponse response = new RestResponse<>(null, msg);
        return new ResponseEntity<>(response, status);
    }
}
