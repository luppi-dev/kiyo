package br.com.luppi.framework.kiyo.service;

import br.com.luppi.framework.kiyo.util.asserts.Asserts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DefaultService extends Asserts {
    protected void info(final String message) {
        log.info(message);
    }
    protected void warn(final String message) {
        log.warn(message);
    }
    protected void error(final String message) {
        log.error(message);
    }
}
