package com.warehouse.util;

import java.util.UUID;

public class MaskingUtil {

    public static String generateMaskedCode() {

        return "PRD-"

                + UUID.randomUUID()

                .toString()

                .substring(0, 8)

                .toUpperCase();

    }

}