package pe4nik.auth;

import org.springframework.security.core.Authentication;

/**
 * Created by Pe4Nik on 07.05.2017.
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
