package yuan.study.demo.utils;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.*;
import com.google.common.base.Throwables;

public class KeyAndMouseListen implements NativeKeyListener, NativeMouseListener, NativeMouseMotionListener, NativeMouseWheelListener {

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyAndMouseListen());
            GlobalScreen.addNativeMouseListener(new KeyAndMouseListen());
            GlobalScreen.addNativeMouseMotionListener(new KeyAndMouseListen());
            GlobalScreen.addNativeMouseWheelListener(new KeyAndMouseListen());
        } catch (Exception e) {
            Throwables.getStackTraceAsString(e);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("nativeKeyTyped: " + e.getKeyChar());
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        System.out.println("Mouse Clicked: (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        System.out.println("Mouse Pressed: (" + e.getX() + ", " + e.getY() + ") - ");
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        System.out.println("Mouse Released: (" + e.getX() + ", " + e.getY() + ") - ");
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        System.out.println(System.currentTimeMillis() + "Mouse Moved: (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        System.out.println("Mouse Dragged: (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        // 获取滚轮滚动的量
        int scrollAmount = e.getWheelRotation();
        // 获取滚轮滚动的类型（可能是垂直或水平滚动）
        int scrollType = e.getScrollType();
        // 获取事件发生的X坐标
        int x = e.getX();
        // 获取事件发生的Y坐标
        int y = e.getY();
        // 打印滚轮事件的信息
        System.out.println("Mouse Wheel Moved: Scroll Amount = " + scrollAmount + ", Scroll Type = " + scrollType + ", X = " + x + ", Y = " + y);
    }
}