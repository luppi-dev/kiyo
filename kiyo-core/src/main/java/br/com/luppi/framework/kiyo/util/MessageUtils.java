package br.com.luppi.framework.kiyo.util;

public class MessageUtils {
    public static String withArgs(String message, Object... args) {
        return args != null && args.length > 0
                ? String.format(message, args)
                : message;
    }
}
