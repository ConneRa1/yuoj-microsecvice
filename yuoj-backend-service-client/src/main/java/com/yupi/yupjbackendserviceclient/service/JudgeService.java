/**
 * copyright (C), 2015-2024
 * fileName: JudgeService
 *
 * @author: mlt
 * date:    2024/11/22 下午5:18
 * description:
 * history:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * adpost    2024/11/22 下午5:18           V1.0
 */
package com.yupi.yupjbackendserviceclient.service;

import com.yupi.yuojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题系统
 *
 * @author mlt
 * @version 1.0.0
 * @date 2024/11/22
 */
public interface JudgeService {
    QuestionSubmit judge(Long questionSubmitId);
}
