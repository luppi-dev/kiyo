package br.com.luppi.framework.kiyo.controller;

import br.com.luppi.framework.kiyo.domain.EnumValue;
import br.com.luppi.framework.kiyo.domain.Item;
import br.com.luppi.framework.kiyo.exception.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultController {

    @Autowired
    private HttpServletResponse response;
    private static final String HEADER_TOTAL_COUNT = "x-meta-total-count";
    private static final String HEADER_TOTAL_PAGES = "x-meta-total-pages";
    private static final String HEADER_CURRENT_PAGE = "x-meta-current-page";
    private static final String HEADER_CURRENT_SIZE = "x-meta-current-size";


    public ResponseEntity<List<Item>> query(final EnumValue[] enums) {
        return ResponseEntity.ok(
                Stream.of(enums)
                        .map(Item::fromEnumValue)
                        .sorted(Comparator.comparing(Item::getDescription))
                        .collect(Collectors.toList())
        );
    }

    public <T>ResponseEntity<List<T>> query(final Page<T> page) {
        final HttpServletResponse httpResponse = this.response;
        httpResponse.setHeader(HEADER_TOTAL_COUNT, String.valueOf(page.getTotalElements()));
        httpResponse.setHeader(HEADER_TOTAL_PAGES, String.valueOf(page.getTotalPages()));
        httpResponse.setHeader(HEADER_CURRENT_PAGE, String.valueOf(page.getNumber() + 1));
        httpResponse.setHeader(HEADER_CURRENT_SIZE, String.valueOf(page.getSize()));
        return ResponseEntity.ok(page.getContent());
    }

    public ResponseEntity<Void> ok() {
        return ResponseEntity.ok().build();
    }

    public <T> ResponseEntity<T> ok(T body) {
        if(body == null) {
            throw new NotFoundException("Not found");
        }
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Void> created() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public <T> ResponseEntity<T> created(T body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public ResponseEntity<Void> accepted() {
        return ResponseEntity.accepted().build();
    }
    public <T> ResponseEntity<T> accepted(T body) {
        return ResponseEntity.accepted().body(body);
    }

    public ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }
}
