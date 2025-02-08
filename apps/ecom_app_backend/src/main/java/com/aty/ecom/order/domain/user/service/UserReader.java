package com.aty.ecom.order.domain.user.service;

import com.aty.ecom.order.domain.user.aggregate.User;
import com.aty.ecom.order.domain.user.repository.UserRepository;
import com.aty.ecom.order.domain.user.vo.UserEmail;
import com.aty.ecom.order.domain.user.vo.UserPublicId;
import com.aty.ecom.order.infrastructure.secondary.entity.UserEntity;

import java.util.Optional;

public class UserReader {

  private final UserRepository userRepository;

  public UserReader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getByEmail(UserEmail userEmail) {
    return userRepository.getOneByEmail(userEmail);
  }

  public Optional<User> getByPublicId(UserPublicId userPublicId) {
    return userRepository.get(userPublicId);
  }
}
