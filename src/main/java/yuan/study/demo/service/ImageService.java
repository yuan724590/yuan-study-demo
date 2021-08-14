package yuan.study.demo.service;


public interface ImageService {

    /**
     * 图片切割
     */
    String imageSplit();

    /**
     * 处理不动的gif图
     */
    String processStaticGif();

    /**
     * 处理普通的gif图
     */
    String processGif();
}
