package com.guilhermeDias.StockFlow.dto.Login;

import com.guilhermeDias.StockFlow.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
