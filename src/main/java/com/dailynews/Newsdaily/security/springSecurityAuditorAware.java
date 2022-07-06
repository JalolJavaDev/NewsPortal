package com.dailynews.Newsdaily.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class springSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor(){
        return Optional.of(SecurityUtils.getCurrentUserName().orElse("Bunday user mavjud emas"));
    }
}
