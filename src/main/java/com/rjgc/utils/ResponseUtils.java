package com.rjgc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjgc.exceptions.ResBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhaoyunjie
 * @date 2021-04-15 20:21
 */
public class ResponseUtils {
    public static <T> void out(HttpServletResponse response, ResBody<T> result) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter writer = response.getWriter()) {
            mapper.writeValue(writer, result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
