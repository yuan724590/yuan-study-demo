package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.ImageService;

import javax.annotation.Resource;

@RequestMapping("/image")
@RestController
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 图片切割
     */
    @GetMapping(value = "/split")
    public String imageSplit() {
        return imageService.imageSplit();
    }

    /**
     * 处理不动的gif图
     */
    @GetMapping(value = "/processStaticGif")
    public String processStaticGif() {
        return imageService.processStaticGif();
    }

    /**
     * 处理普通的gif图
     */
    @GetMapping(value = "/processGif")
    public String processGif() {
        return imageService.processGif();
    }

}
