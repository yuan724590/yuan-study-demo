package yuan.study.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageFormatsEnum {

    BMP("bmp"),

    JPG("jpg"),

    WBMP("wbmp"),

    JPEG("jpeg"),

    PNG("png"),

    GIF("gif");

    private String value;
}
