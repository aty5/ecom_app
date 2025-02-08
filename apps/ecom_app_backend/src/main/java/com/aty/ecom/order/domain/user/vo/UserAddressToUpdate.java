package com.aty.ecom.order.domain.user.vo;

import com.aty.ecom.shared.error.domain.Assert;
import com.aty.ecom.shared.error.domain.MissingMandatoryValueException;
import org.jilt.Builder;

@Builder
public record UserAddressToUpdate(UserPublicId userPublicId, UserAddress userAddress) {

  public UserAddressToUpdate {
    Assert.notNull("value", userPublicId);
    Assert.notNull("value", userAddress);
  }
}
