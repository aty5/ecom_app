package com.aty.ecom.order.application;

import com.aty.ecom.order.domain.user.aggregate.User;
import com.aty.ecom.order.domain.user.repository.UserRepository;
import com.aty.ecom.order.domain.user.service.UserReader;
import com.aty.ecom.order.domain.user.service.UserSynchronizer;
import com.aty.ecom.order.domain.user.vo.UserAddressToUpdate;
import com.aty.ecom.order.domain.user.vo.UserEmail;
import com.aty.ecom.order.infrastructure.secondary.service.kinde.KindeService;
import com.aty.ecom.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersApplicationService {

  private final UserSynchronizer userSynchronizer;
  private final UserReader userReader;

  public UsersApplicationService(UserRepository userRepository, KindeService kindeService) {
    this.userSynchronizer = new UserSynchronizer(userRepository, kindeService);
    this.userReader = new UserReader(userRepository);
  }

  @Transactional
  public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceResync) {
    userSynchronizer.syncWithIdp(jwtToken, forceResync);
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  public User getAuthenticatedUser() {
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
      .orElseThrow();
  }

  @Transactional
  public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
    userSynchronizer.updateAddress(userAddressToUpdate);
  }

}
