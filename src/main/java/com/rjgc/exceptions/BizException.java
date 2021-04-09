package com.rjgc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:41
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class BizException extends RuntimeException {

    private ExceptionsEnum exp;
}
