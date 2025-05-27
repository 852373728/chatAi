package com.qilin.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class MathTool {

    @Tool("计算两个数的和")
    public BigDecimal sum(BigDecimal a, BigDecimal b){
        return a.add(b);
    }

    @Tool("计算一个数字的平方根")
    public BigDecimal sqrt(BigDecimal a){
        return a.sqrt(MathContext.DECIMAL32);
    }
}
