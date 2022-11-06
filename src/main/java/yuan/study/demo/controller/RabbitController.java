package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yuan.study.demo.dto.RabbitDTO;
import yuan.study.demo.service.RabbitService;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class RabbitController {

    @Resource
    private RabbitService rabbitService;

    /**
     * 触发死信队列
     */
    @PostMapping(value = "/dead/letter/queue", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deadLetter(@RequestBody @Validated RabbitDTO.DeadLetterDTO dto) {
        return rabbitService.deadLetter(dto.getMsg());
    }

    /**
     * 触发延迟队列
     */
    @PostMapping(value = "/delay/queue", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String delay(@RequestBody @Validated RabbitDTO.DelayDTO dto) {
        return rabbitService.delay(dto.getMsg());
    }
}
