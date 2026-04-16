package com.quantitymeasurement.quantity_service.dto;

import lombok.Data;

@Data
public class QuantityInputDTO {

    private QuantityDTO thisQuantityDTO;
    private QuantityDTO thatQuantityDTO;

    // Gateway se X-User-Id header ke through aayega, controller inject karega
    private Long userId;

    public QuantityDTO getThisQuantityDTO() {
        return thisQuantityDTO;
    }
    public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
        this.thisQuantityDTO = thisQuantityDTO;
    }
    public QuantityDTO getThatQuantityDTO() {
        return thatQuantityDTO;
    }
    public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
        this.thatQuantityDTO = thatQuantityDTO;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}