package com.persou.store.transportlayers.dto;

import java.math.BigDecimal;

public record OrderDTO(
    String id,
    String name,
    BigDecimal price) {
}
