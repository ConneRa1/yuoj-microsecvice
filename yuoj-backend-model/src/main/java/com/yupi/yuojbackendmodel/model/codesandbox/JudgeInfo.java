package com.yupi.yuojbackendmodel.model.codesandbox;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 判题信息
 */
@Data
@SuperBuilder
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间（KB）
     */
    private Long time;
}