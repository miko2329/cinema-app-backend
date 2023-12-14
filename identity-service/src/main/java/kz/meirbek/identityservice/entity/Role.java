package kz.meirbek.identityservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static kz.meirbek.identityservice.entity.Permission.*;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),

  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  MODERATOR_READ,
                  MODERATOR_UPDATE,
                  MODERATOR_DELETE,
                  MODERATOR_CREATE
          )
  ),
  MODERATOR(
          Set.of(
                  MODERATOR_READ,
                  MODERATOR_UPDATE,
                  MODERATOR_DELETE,
                  MODERATOR_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
