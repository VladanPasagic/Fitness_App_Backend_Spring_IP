package etf.unibl.org.ip.ipbackend.configurations.models;

import lombok.Data;

import java.util.List;

@Data
public class AuthorizationRules {
    List<Rule> rules;
}
