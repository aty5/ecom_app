package com.aty.ecom.order.domain.user.vo;

import com.aty.ecom.shared.error.domain.Assert;

public record AuthorityName(String name) {
  public AuthorityName {
    Assert.field("name", name).notNull();
  }
}
