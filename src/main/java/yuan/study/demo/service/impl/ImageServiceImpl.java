package yuan.study.demo.service.impl;


import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import yuan.study.demo.enums.ImageFormatsEnum;
import yuan.study.demo.service.ImageService;
import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String imageSplit() {
        //图片切割 - 方案一
        imageSplit1();
        return "success";
    }

    /**
     * 图片切割 - 方案一
     */
    private void imageSplit1(){
        BufferedImage bufferedImage = getImageInfo("http://img.haohaozhu.cn/App-imageShow/o_phone/2df/2fbb222m63qi2am00qqsduj2nabn");
        if (bufferedImage != null) {
            BufferedImage newBufferedImage = bufferedImage.getSubimage(0, 0, bufferedImage.getWidth(), (int) (bufferedImage.getHeight() * 0.9));
            try {
                File file = new File("E:\\迅雷下载\\这里\\image.jpg");
                ImageIO.write(newBufferedImage, "jpg", file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取图片信息
     */
    private BufferedImage getImageInfo(String url){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        try{
            URL urlObj = new URL(url);
            URLConnection urlConnection = urlObj.openConnection();
            return ImageIO.read(urlConnection.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String processStaticGif(){
        String url = "http://img.haohaozhu.cn/App-imageShow/o_phone/123/9557b20rs0dg2GR00qpy7yr3gbzs";
        //处理不动的gif图 - 方案一
        processStaticGif1(url);
        //处理不动的gif图 - 方案二
        processStaticGif2(url);
        //处理不动的gif图 - 方案三
        processStaticGif3(url);
        return "success";
    }

    /**
     * 处理不动的gif图 - 方案一
     */
    private void processStaticGif1(String url){
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.19 Safari/537.36");
            connection.setConnectTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            byte[] tmp = new byte[1024];
            int length;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((length = inputStream.read(tmp)) != -1) {
                outputStream.write(tmp, 0, length);
            }
            outputStream.flush();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            BufferedImage bufferedImage = ImageIO.read(ImageIO.createImageInputStream(byteArrayInputStream));
            BufferedImage subImage = bufferedImage.getSubimage(0, 0, bufferedImage.getWidth(), (int) (bufferedImage.getHeight() * 0.85));
            BufferedImage image = new BufferedImage(subImage.getWidth(), subImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.drawImage(subImage,0,0, subImage.getWidth(), subImage.getHeight(), null);
            g.dispose();
            ImageIO.write(image, "GIF", new File("E:\\迅雷下载\\这里\\image2.gif"));
            outputStream.close();
            byteArrayInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理不动的gif图 - 方案二
     */
    private void processStaticGif2(String url){
        try {
            boolean flag = GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadlessInstance();
            Class<?> clazz = Class.forName("java.awt.GraphicsEnvironment");
            Field field = clazz.getDeclaredField("headless");
            field.setAccessible(true);
            field.set("headless", false);
            Image image = new ImageIcon(new URL(url), "aaa").getImage();
            ImageIO.write(toBufferedImage(image), "gif", new File("E:\\迅雷下载\\这里\\a.gif"));
            field.set("headless", flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理不动的gif图 - 方案三
     */
    private void processStaticGif3(String url){
        try {
            Image src = Toolkit.getDefaultToolkit().getImage(new URL(url));
            BufferedImage bufferedImage = toBufferedImage(src);
            if(bufferedImage == null){
                return ;
            }
            BufferedImage subImage = bufferedImage.getSubimage(
                    0, 0, bufferedImage.getWidth(), (int) (bufferedImage.getHeight() * 0.85));
            BufferedImage image = new BufferedImage(subImage.getWidth(), subImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.drawImage(subImage,0,0, subImage.getWidth(), subImage.getHeight(), null);
            g.dispose();
            File file = new File("E:\\迅雷下载\\这里\\image5.gif");
            ImageIO.write(image, "GIF", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    @Override
    public String processGif(){
        //通过文件流处理gif图 - 方案一
        processGifByFile1();
        //通过文件流处理gif图 - 方案二
        processGifByFile2();
        //处理gif图 - 方案三
        processGif3();
        return "success";
    }

    /**
     * 处理gif图 - 方案三
     */
    private void processGif3(){
        File file = new File("E:\\迅雷下载\\这里\\3cacef0rs0dg23u00qpup0e3gbzs.gif");
        BufferedImage image = getImageInfo("http://img.haohaozhu.cn/App-imageShow/o_phone/820/a4a6f115o0rv7Ld00qsdafc57ic");
        int destWidth = image.getWidth();
        int destHeight = image.getHeight();

        cutGifImage(0,0, destWidth, destHeight, bufferedImageToInputStream(image));
        /************************ 基于三方包解决方案 *************************/
        String formatName = getImageFormatName(file);
        String pathSuffix = "." + formatName;
        String pathPrefix = getFilePrefixPath(file);
        String targetPath = pathPrefix  + System.currentTimeMillis() + pathSuffix;
        targetPath = cutImage(file.getPath(), targetPath, 0, 0, destWidth, (int)(destHeight * 0.9));

        String bigTargetPath = pathPrefix  + System.currentTimeMillis() + pathSuffix;
        zoom(targetPath, bigTargetPath, 500, 500);

        String smallTargetPath = pathPrefix  + System.currentTimeMillis() + pathSuffix;
        zoom(targetPath, smallTargetPath, 500, 500);
    }

    /**
     * 获取某个文件的前缀路径
     *
     * 不包含文件名的路径
     *
     * @param file   当前文件对象
     */
    private String getFilePrefixPath(File file){
        String path = null;
        if(!file.exists()) {
            throw new RuntimeException("not found the file !" );
        }
        String fileName = file.getName();
        path = file.getPath().replace(fileName, "");
        return path;
    }

    /**
     * 压缩图片
     * @param sourcePath       待压缩的图片路径
     * @param targetPath    压缩后图片路径（默认为初始路径）
     * @param width            压缩宽度
     * @param height        压缩高度
     * @returns                   裁剪后保存路径（图片后缀根据图片本身类型生成）
     */
    private String zoom(String sourcePath , String targetPath, int width , int height){
        File file = new File(sourcePath);
        if(!file.exists()) {
            throw new RuntimeException("not found the image ：" + sourcePath);
        }
        if(null == targetPath || targetPath.isEmpty()) targetPath = sourcePath;

        String formatName = getImageFormatName(file);
        if(null == formatName) return targetPath;
        formatName = formatName.toLowerCase();

        // 防止图片后缀与图片本身类型不一致的情况
        String pathPrefix = getPathWithoutSuffix(targetPath);
        targetPath = pathPrefix + formatName;

        // GIF需要特殊处理
        if(ImageFormatsEnum.GIF.getValue() == formatName){
            GifDecoder decoder = new GifDecoder();
            int status = decoder.read(sourcePath);
            if (status != GifDecoder.STATUS_OK) {
                throw new RuntimeException("read image " + sourcePath + " error!");
            }

            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            encoder.start(targetPath);
            encoder.setRepeat(decoder.getLoopCount());
            for (int i = 0; i < decoder.getFrameCount(); i ++) {
                encoder.setDelay(decoder.getDelay(i));
                BufferedImage image = zoom(decoder.getFrame(i), width , height);
                encoder.addFrame(image);
            }
            encoder.finish();
        }else{
            try {
                BufferedImage image = ImageIO.read(file);
                BufferedImage zoomImage = zoom(image , width , height);
                ImageIO.write(zoomImage, formatName, new File(targetPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetPath;
    }

    /**
     * 压缩图片
     * @param sourceImage    待压缩图片
     * @param width          压缩图片高度
     * @param height          压缩图片宽度
     */
    private BufferedImage zoom(BufferedImage sourceImage , int width , int height){
        BufferedImage zoomImage = new BufferedImage(width, height, sourceImage.getType());
        Image image = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Graphics gc = zoomImage.getGraphics();
        gc.setColor(Color.WHITE);
        gc.drawImage( image , 0, 0, null);
        return zoomImage;
    }

    private InputStream bufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "gif", os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取图片格式
     */
    private String getImageFormatName(File file){
        String formatName = null;
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> imageReader =  ImageIO.getImageReaders(iis);
            if(imageReader.hasNext()){
                ImageReader reader = imageReader.next();
                formatName = reader.getFormatName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(iis != null){
                try {
                    iis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return formatName;
    }

    /**
     * 通过文件流处理gif图 - 方案二
     */
    private Image processGifByFile2() {
        BufferedImage source = null;
        try {
            source = ImageIO.read(new File("E:\\迅雷下载\\这里\\3cacef0rs0dg23u00qpup0e3gbzs.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean flag = false ;
        int upperBorder = -1 ;
        do{
            upperBorder ++ ;
            for (int c1 =0 ; c1 < source.getWidth() ; c1++){
                if(source.getRGB(c1, upperBorder) != Color.white.getRGB() ){
                    flag = true;
                    break ;
                }
            }

            if (upperBorder >= source.getHeight())
                flag = true ;
        }while(!flag) ;

        BufferedImage destination = new BufferedImage(source.getWidth(), source.getHeight() - upperBorder, BufferedImage.TYPE_INT_ARGB) ;
        destination.getGraphics().drawImage(source, 0, upperBorder*-1, null) ;

        try {
            File file = new File("E:\\迅雷下载\\这里\\image.gif");
            ImageIO.write(destination, "gif", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 切割图片
     */
    private String cutImage(String sourcePath , String targetPath , int x , int y , int width , int height) {
        File file = new File(sourcePath);
        if(!file.exists()) {
            throw new RuntimeException("not found the image：" + sourcePath);
        }
        if(null == targetPath || targetPath.isEmpty()) targetPath = sourcePath;

        String formatName = getImageFormatName(file);
        if(null == formatName) return targetPath;
        formatName = formatName.toLowerCase();

        // 防止图片后缀与图片本身类型不一致的情况
        String pathPrefix = getPathWithoutSuffix(targetPath);
        targetPath = pathPrefix + formatName;

        // GIF需要特殊处理
        if(ImageFormatsEnum.GIF.getValue().equals(formatName)){
            GifDecoder decoder = new GifDecoder();
            int status = decoder.read(sourcePath);
            if (status != GifDecoder.STATUS_OK) {
                throw new RuntimeException("read image " + sourcePath + " error!");
            }
            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            encoder.start(targetPath);
            encoder.setRepeat(decoder.getLoopCount());
            for (int i = 0; i < decoder.getFrameCount(); i ++) {
                encoder.setDelay(decoder.getDelay(i));
                BufferedImage childImage = decoder.getFrame(i);
                BufferedImage image = childImage.getSubimage(x, y, width, height);
                encoder.addFrame(image);
            }
            encoder.finish();
        }else{
            try {
                BufferedImage image = ImageIO.read(file);
                image = image.getSubimage(x, y, width, height);
                ImageIO.write(image, formatName, new File(targetPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetPath;
    }

    /**
     * 获取不包含后缀的文件路径
     */
    private String getPathWithoutSuffix(String src){
        String path = src;
        int index = path.lastIndexOf(".");
        if(index > 0){
            path = path.substring(0, index + 1);
        }
        return path;
    }

    /**
     * 切割gif图
     */
    private OutputStream cutGifImage(int x , int y , int width , int height, InputStream inputStream) {
        try {
            URL url = new URL("http://img.haohaozhu.cn/App-imageShow/o_phone/123/9557b20rs0dg2GR00qpy7yr3gbzs");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.19 Safari/537.36");
            connection.setConnectTimeout(5000);
            inputStream = connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(inputStream);
        if (status != GifDecoder.STATUS_OK) {
            throw new RuntimeException("read image error!");
        }
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encoder.start(byteArrayOutputStream);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = 0; i < decoder.getFrameCount(); i ++) {
            encoder.setDelay(decoder.getDelay(i) * 10);
            BufferedImage childImage = decoder.getFrame(i);
            BufferedImage image = childImage.getSubimage(x, y, width, (int) (height * 0.9));
            encoder.addFrame(image);

            BufferedImage frame = decoder.getFrame(i);
            int delay = decoder.getDelay(i);
            System.out.println("延迟时间: "+delay);
            OutputStream out = null;
            try {
                out = new FileOutputStream("E:\\迅雷下载\\这里\\bb" + i + ".gif");
                //将frame 按jpeg格式  写入out中
                ImageIO.write(frame, "gif", out);
                JPEGImageEncoder encoder1 = JPEGCodec.createJPEGEncoder(out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(out != null){
                    try {
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        encoder.finish();
        //写文件
        writeToFile(byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    /**
     * 写文件
     */
    private void writeToFile(ByteArrayOutputStream byteArrayOutputStream){
        byte buffer[] = byteArrayOutputStream.toByteArray();
        OutputStream out=null;
        try {
            out = new FileOutputStream(new File("E:\\迅雷下载\\这里\\aa.gif"));
            out.write(buffer, 0, buffer.length);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }finally{
            try {
                if(out != null){
                    out.close();
                }
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过文件流处理gif图
     */
    private BufferedImage processGifByFile1() {
        BufferedImage source = null;
        try {
            source = ImageIO.read(new File("E:\\迅雷下载\\这里\\9557b20rs0dg2GR00qpy7yr3gbzs.jfif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        double tolerance = 0;
        // Get our top-left pixel color as our "baseline" for cropping
        int baseColor = source.getRGB(0, 0);

        int width = source.getWidth();
        int height = (int) (source.getHeight() * 0.9);

        int topY = Integer.MAX_VALUE, topX = Integer.MAX_VALUE;
        int bottomY = -1, bottomX = -1;
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                if (colorWithinTolerance(baseColor, source.getRGB(x, y), tolerance)) {
                    if (x < topX) topX = x;
                    if (y < topY) topY = y;
                    if (x > bottomX) bottomX = x;
                    if (y > bottomY) bottomY = y;
                }
            }
        }

        BufferedImage destination = new BufferedImage( (bottomX-topX+1),
                (bottomY-topY+1), BufferedImage.TYPE_INT_ARGB);

        destination.getGraphics().drawImage(source, 0, 0,
                destination.getWidth(), (int) (destination.getHeight() * 0.9),
                topX, topY, bottomX, bottomY, null);

        try {
            File file = new File("E:\\迅雷下载\\这里\\image.gif");
            ImageIO.write(destination, "gif", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destination;
    }

    private boolean colorWithinTolerance(int a, int b, double tolerance) {
        int aAlpha  = (int)((a & 0xFF000000) >>> 24);
        int aRed    = (int)((a & 0x00FF0000) >>> 16);
        int aGreen  = (int)((a & 0x0000FF00) >>> 8);
        int aBlue   = (int)(a & 0x000000FF);

        int bAlpha  = (int)((b & 0xFF000000) >>> 24);
        int bRed    = (int)((b & 0x00FF0000) >>> 16);
        int bGreen  = (int)((b & 0x0000FF00) >>> 8);
        int bBlue   = (int)(b & 0x000000FF);

        double distance = Math.sqrt((aAlpha-bAlpha)*(aAlpha-bAlpha) +
                (aRed-bRed)*(aRed-bRed) +
                (aGreen-bGreen)*(aGreen-bGreen) +
                (aBlue-bBlue)*(aBlue-bBlue));
        double percentAway = distance / 510.0d;

        return (percentAway > tolerance);
    }
}
