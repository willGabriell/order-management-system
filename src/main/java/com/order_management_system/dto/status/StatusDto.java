package com.order_management_system.dto.status;

import com.order_management_system.enums.StatusPedido;
import lombok.Data;

@Data
public class StatusDto {
    StatusPedido status;
}
