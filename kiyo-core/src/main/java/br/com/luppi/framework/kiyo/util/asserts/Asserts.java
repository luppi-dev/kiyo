package br.com.luppi.framework.kiyo.util.asserts;

import br.com.luppi.framework.kiyo.exception.BusinessException;
import br.com.luppi.framework.kiyo.util.MessageUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Asserts {
    public static <T> T assertNotNull(T object, String message, Object... args) {
        assertThat(Objects.nonNull(object), message, args);
        return object;
    }

    public static <T> T assertNotEmpty(T object, String message, Object... args) {
        assertThat(ObjectUtils.isNotEmpty(object), message, args);
        return object;
    }

    public static CharSequence assertNotBlank(CharSequence str, String message, Object... args) {
        assertThat(StringUtils.isNotBlank(str), message, args);
        return str;
    }

    public static <T> void assertNull(T object, String message, Object... args) {
        assertThat(Objects.isNull(object), message, args);
    }

    public static void assertTrue(boolean expression, String message) {
        assertThat(expression, message);
    }
    public static void assertFalse(boolean expression, String message) {
        assertThat(!expression, message);
    }

    public static void assertThat(Boolean expression, String message, Object... args) {
        if(!expression) {
            throw new BusinessException(MessageUtils.withArgs(message, args));
        }
    }
}
