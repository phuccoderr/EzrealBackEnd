package com.ezreal.model.entity.account;

import java.time.LocalDateTime;

public record Account(Integer accountId, String email, String name, String avatarUrl, LocalDateTime createAt, Boolean isViolate) {
}
