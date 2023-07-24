package yuan.study.demo.controller.subject.nowcoder;

import org.springframework.web.bind.annotation.GetMapping;
import yuan.study.demo.service.subjectService.NowCoderHJService;

import javax.annotation.Resource;

/**
 * 牛客hj题目
 * https://www.nowcoder.com/exam/oj/ta?page=1&tpId=37&type=37
 */
public class HJ {

    @Resource
    private NowCoderHJService hjService;

    /**
     * HJ3 明明的随机数
     */
    @GetMapping(value = "/hj/mingming/randomNumber")
    public String randomNumber() throws Exception{
        hjService.randomNumber();
        return "success";
    }

    /**
     * HJ5 进制转换
     */
    @GetMapping(value = "/hj/decimal/conversion")
    public String decimalConversion() throws Exception {
        hjService.decimalConversion();
        return "success";
    }

    /**
     * HJ18 识别有效的IP地址和掩码并进行分类统计
     */
    @GetMapping(value = "/hj/classification/statistics")
    public String classificationStatistics() throws Exception {
        hjService.classificationStatistics();
        return "success";
    }

    /**
     * HJ22 汽水瓶
     */
    @GetMapping(value = "/hj/soda/bottle")
    public String sodaBottle() throws Exception {
        hjService.sodaBottle();
        return "success";
    }
}
