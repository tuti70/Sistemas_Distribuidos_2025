package com.otuti.api.services;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ValidaIdService {

    // Método para validar UUID
    public boolean validaUUID(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        try {
            UUID uuid = UUID.fromString(id.trim());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Método para converter String para UUID
    public UUID convertToUUID(String id) {
        return UUID.fromString(id.trim());
    }

    // Método mantido para compatibilidade (se ainda precisar validar Long em algum
    // lugar)
    public boolean validaId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        id = id.trim();

        if (!id.matches("\\d+")) {
            return false;
        }

        try {
            return (convertToLong(id) >= 1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Long convertToLong(String num) {
        return Long.parseLong(num);
    }
}