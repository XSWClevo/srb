package com.xshiwu.common.exception;

import com.xshiwu.common.result.R;
import com.xshiwu.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 项目中的统一异常处理类
 * @author xsw
 * @version 1.0
 * @date 2021/4/11 20:20
 */
@Slf4j
// Spring容易自动管理
@Component
// 在controller层添加通知。如果使用@ControllerAdvice，则方法上需要添加@ResponseBody
@RestControllerAdvice
public class UnifiedExceptionHandler {
    /**
     * 未定义异常
     */
    @ExceptionHandler(value = Exception.class) //当controller中抛出Exception，则捕获
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error();
    }

    /**
     * 特定异常
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public R handleBadSqlGrammarException(BadSqlGrammarException e){
        log.error(e.getMessage(), e);
        return R.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }

}
